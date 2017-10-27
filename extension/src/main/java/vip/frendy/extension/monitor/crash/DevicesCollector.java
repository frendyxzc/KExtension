package vip.frendy.extension.monitor.crash;

import android.os.Build;

/**
 * Created by frendy on 2016/8/24.
 */

public final class DevicesCollector {

    public static String getBuildInfo() {
        return "\n\n============\nBuild info\n============\n"
                + "SDK_VERSION= " + Build.VERSION.SDK_INT
                + "\nBOARD = " + Build.BOARD
                + "\nBRAND = " + Build.BRAND
                + "\nCPU_ABI = " + Build.CPU_ABI
                + "\nDEVICE = " + Build.DEVICE
                + "\nDISPLAY = " + Build.DISPLAY
                + "\nHOST = " + Build.HOST
                + "\nID = " + Build.ID
                + "\nMANUFACTURER = " + Build.MANUFACTURER
                + "\nMODEL = " + Build.MODEL
                + "\nPRODUCT = " + Build.PRODUCT
                + "\nTAGS = " + Build.TAGS
                + "\nTYPE = " + Build.TYPE
                + "\nUSER = " + Build.USER;
    }
}
