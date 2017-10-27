package vip.frendy.extension.monitor.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vip.frendy.extension.monitor.interfaces.ICrash;

/**
 * Created by frendy on 2016/4/7.
 * 在Application中统一捕获异常，保存到文件中下次再打开时上传
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static String TAG = "CrashHandler";
    private static File FILE_DIR = Environment.getExternalStorageDirectory();

    /** 系统默认的UncaughtException处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /** CrashHandler实例 */
    private static CrashHandler INSTANCE;
    /** 程序的Context对象 */
    private Context mContext;
    /** 监听 */
    private ICrash mCrashListener;

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {}
    /** 获取CrashHandler实例 ,单例模式*/
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     */
    public void init(Context context, ICrash crashListener) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        mCrashListener = crashListener;
        Thread.setDefaultUncaughtExceptionHandler(this);

        FILE_DIR = mContext.getFilesDir();
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     * @return
     * true代表处理该异常，不再向上抛异常，
     * false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
     * 简单来说就是true不会弹出那个错误提示框，false就会弹出
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
//        final String msg = ex.getLocalizedMessage();
        final StackTraceElement[] stack = ex.getStackTrace();
        final String message = ex.getMessage();
        //监听
        if(mCrashListener != null) {
            mCrashListener.onCrash(message);
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //可以只创建一个文件，以后全部往里面append然后发送，这样就会有重复的信息，个人不推荐
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
                String fileName = "crash_" + mContext.getPackageName() + "_" + formatter.format(System.currentTimeMillis()) + ".mlog";
                File file = new File(FILE_DIR, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file,true);
                    // devices info
                    fos.write((DevicesCollector.getBuildInfo() + "\n\n").getBytes());
                    // crash info
                    fos.write((message + "\n").getBytes());
                    for (int i = 0; i < stack.length; i++) {
                        fos.write((stack[i].toString() + "\n").getBytes());
                    }
                    // logcat info
                    fos.write(("\n\n" + LogcatCollector.collectLogcat(null)).getBytes());
                    // mem info
                    fos.write(("\n\n" + DumpSysCollector.collectMemInfo()).getBytes());
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                }
                Looper.loop();
            }
        }.start();
        return false;
    }

    public void crashFileUpload() {
        //获取日志文件列表
        getFiles(FILE_DIR.getPath(), "mlog", true);

        if(lstFile != null && lstFile.size() > 0) {
            for(int i=0; i<lstFile.size(); i++) {
                if(mCrashListener != null) {
                    mCrashListener.onCrashFileUpload(lstFile.get(i), lstFileName.get(i));
                }
            }
        }
    }

    //search and get log file
    private static List<String> lstFile =new ArrayList<String>();
    private static List<String> lstFileName =new ArrayList<String>();

    //搜索目录，扩展名，是否进入子文件夹
    private static void getFiles(String Path, String Extension, boolean IsIterative) {
        File[] files =new File(Path).listFiles();
        if(files == null) return;

        for (int i =0; i < files.length; i++) {
            File f = files[i];
            if (f.isFile()) {
                //判断扩展名
                if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension)) {
                    lstFile.add(f.getPath());
                    lstFileName.add(f.getName());
                }

                if (!IsIterative)
                    break;
            } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) //忽略点文件（隐藏文件/文件夹）
                getFiles(f.getPath(), Extension, IsIterative);
        }
    }
}
