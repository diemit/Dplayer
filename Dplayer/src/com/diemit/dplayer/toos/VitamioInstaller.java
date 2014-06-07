package com.diemit.dplayer.toos;

import android.content.Context;

/**
 * Created by Diemit on 14-2-28.
 */
public class VitamioInstaller {
    //Vitamio版本编号
    public static final int VITAMIO_VERSION_CODE = 42;

    //Vitamio版本名称
    public static final String VITAMIO_VERSION_NAME = null;

    //Vitamio包名
    public static final String VITAMIO_PACKAGE = null;

    //Vitamio解码库类型——不支持。
    public static final int VITAMIO_NOT_SUPPORTED = 0;

    //Vitamio解码库类型——针对ARMv6。
    public static final int VITAMIO_ARMV6 = 0;

    //Vitamio解码库类型——针对ARMv6 VFP。
    public static final int VITAMIO_ARMV6_VFP = 0;

    //Vitamio解码库类型——针对ARMv7 VFPV3。
    public static final int VITAMIO_ARMV7_VFPV3 = 0;

    //Vitamio解码库类型——针对ARMv7 NEON。
    public static final int VITAMIO_ARMV7_NEON = 0;


    //返回兼容包名称。
    public static String getCompatiblePackage() {
        return null;
    }

    //获取Vitamio解码库的类型。返回值参见常量。
    public static int getVitamioType() {
        return 0;
    }

    //检测Vitamio插件是否已经安装。
    public static String checkVitamioInstallation(Context ctx) {
        return null;
    }


    /*
    检测Vitamio插件是否已经安装，并且检测是否已经过期。
    参数
    ctx	Context
    desireVersion	目标版本号
    返回值
            返回兼容包名
    异常
    VitamioNotCompatibleException	不支持解码库时引发
    VitamioNotFoundException	找不到Vitamio插件时引发
    VitamioOutdateException	 已安装的Vitamio插件过期了
*/
    public static String checkVitamioInstallation(Context ctx, int desireVersion) {
        return null;
    }


    //获取Vitamio的安装信息。例如版本名称、安装路径等。
    public static String getVitamioInfo(Context ctx) {
        return null;
    }

    //获取Vitamio解码库安装路径。
    public static final String getLibraryPath() {
        return null;
    }

    //是否已经初始化解码包。（解压并初始化解码包）
    public static boolean isNativeLibsInited(Context context) {
      return false;
    }


}
