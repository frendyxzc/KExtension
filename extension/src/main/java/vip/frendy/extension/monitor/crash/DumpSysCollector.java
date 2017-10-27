package vip.frendy.extension.monitor.crash;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vip.frendy.extension.utils.IOUtils;

/**
 * Created by frendy on 2016/8/24.
 */

public final class DumpSysCollector {
    private static String TAG = "DumpSysCollector";

    private DumpSysCollector(){}

    /**
     * Collect results of the <code>dumpsys meminfo</code> command restricted to
     * this application process.
     *
     * @return The execution result.
     */
    @NonNull
    public static String collectMemInfo() {

        final StringBuilder meminfo = new StringBuilder();
        try {
            final List<String> commandLine = new ArrayList<String>();
            commandLine.add("dumpsys");
            commandLine.add("meminfo");
            commandLine.add(Integer.toString(android.os.Process.myPid()));

            final Process process = Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
            meminfo.append(IOUtils.streamToString(process.getInputStream()));

        } catch (IOException e) {
            Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
        }

        return meminfo.toString();
    }
}
