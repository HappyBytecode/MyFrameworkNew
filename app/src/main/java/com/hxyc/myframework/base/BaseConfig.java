package com.hxyc.myframework.base;


public class BaseConfig {
    /**
     * 平台名称
     */
    public static String PLATFORM = null;
    /**
     * 运用包名
     */
    public static String PACKAGE_NAME = null;
    /**
     * 补丁之后的 Tinker ID，未使用补丁时是 基础包的 Tinker ID
     */
    public static int CLIENT_TINKER_ID = 0;
    /**
     * 是否是测试包
     */
    public static boolean DEBUG = false;
}
