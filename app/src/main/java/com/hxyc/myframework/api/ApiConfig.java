package com.hxyc.myframework.api;

public final class ApiConfig {

    private static String mHttp = "http://";
    private static String sHost = "";

    public static void setHost(boolean isHttps, String host) {
        if (isHttps) {
            mHttp = "https://";
        }
        sHost = host;
    }

    private static String getHost() {
        return sHost;
    }

    public static String getCommonApi() {
        return mHttp + getHost() + "/api/pass/";
    }

}
