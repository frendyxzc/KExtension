package vip.frendy.extension.monitor.crash;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.internal.util.Predicate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import vip.frendy.extension.utils.BoundedLinkedList;
import vip.frendy.extension.utils.IOUtils;

/**
 * Created by frendy on 2016/4/27.
 */
public class LogcatCollector {
    private static String TAG = "LogcatCollector";

    protected static final int DEFAULT_LOGCAT_LINES = 100;
    protected static final String[] logcatArguments = { "-t", "" + DEFAULT_LOGCAT_LINES, "-v", "time" };

    /**
     * Default number of latest lines kept from the logcat output.
     */
    private static final int DEFAULT_TAIL_COUNT = 100;

    /**
     * @param bufferName    The name of the buffer to be read: "main" (default), "radio" or "events".
     * @return A {@link String} containing the latest lines of the output.
     *         Default is 100 lines, use "-t", "300" in
     *         if you want 300 lines.
     *         You should be aware that increasing this value causes a longer
     *         report generation time and a bigger footprint on the device model
     *         plan consumption.
     */
    public static String collectLogcat(@Nullable String bufferName) {
        final int myPid = android.os.Process.myPid();
        String myPidStr = null;
        if (myPid > 0) {
            myPidStr = Integer.toString(myPid) +"):";
        }

        final List<String> commandLine = new ArrayList<String>();
        commandLine.add("logcat");
        if (bufferName != null) {
            commandLine.add("-b");
            commandLine.add(bufferName);
        }

        // "-t n" argument has been introduced in FroYo (API level 8). For
        // devices with lower API level, we will have to emulate its job.
        final int tailCount;
        final List<String> logcatArgumentsList = new ArrayList<String>(Arrays.asList(logcatArguments));

        final int tailIndex = logcatArgumentsList.indexOf("-t");
        if (tailIndex > -1 && tailIndex < logcatArgumentsList.size()) {
            tailCount = Integer.parseInt(logcatArgumentsList.get(tailIndex + 1));
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
                logcatArgumentsList.remove(tailIndex + 1);
                logcatArgumentsList.remove(tailIndex);
                logcatArgumentsList.add("-d");
            }
        } else {
            tailCount = -1;
        }

        final LinkedList<String> logcatBuf = new BoundedLinkedList<String>(tailCount > 0 ?
                tailCount : DEFAULT_TAIL_COUNT);
        commandLine.addAll(logcatArgumentsList);

        try {
            final Process process = Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));

            Log.d(TAG, "Retrieving logcat output...");

            // Dump stderr to null
            new Thread(new Runnable() {
                public void run() {
                    try {
                        IOUtils.streamToString(process.getErrorStream());
                    } catch (IOException ignored) {
                    }
                }
            }).start();

            final String finalMyPidStr = myPidStr;
            logcatBuf.add(IOUtils.streamToString(process.getInputStream(), new Predicate<String>() {
                @Override
                public boolean apply(String s) {
                    return finalMyPidStr == null || s.contains(finalMyPidStr);
                }
           }));

        } catch (IOException e) {
            Log.e(TAG, "LogCatCollector.collectLogcat could not retrieve model.", e);
        }

        return logcatBuf.toString();
    }
}