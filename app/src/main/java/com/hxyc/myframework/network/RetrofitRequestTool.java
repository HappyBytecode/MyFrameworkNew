package com.hxyc.myframework.network;

import com.hxyc.myframework.db.SP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class RetrofitRequestTool {

    private static Map<String, String> mHeaders;
    private static final String REQUEST_HEADERS = "REQUEST_HEADERS";
    private static final String SPLITER = "--ankai--";
    private static final String KEY_TOKEN = "RetrofitRequestTool#KEY_TOKEN";
    private static final String KEY_UUID = "RetrofitRequestTool#KEY_UUID";
    private static final String KEY_PHONE = "RetrofitRequestTool#KEY_PHONE";
    private static final String KEY_BALANCE = "RetrofitRequestTool#KEY_BALANCE";
    private static final String KEY_NIKENAME = "KEY_NIKENAME";
    private static final String KEY_AVATOR = "KEY_AVATOR";
    private static final String KEY_REGISTER_DATE = "key_register_date";

    private static String mAppid;
    private static String key;
    private static String token;
    private static String nikeName;
    private static String avator;


    public static String getBalance(SP sp) {
        return sp.getString(KEY_BALANCE);
    }

    public static void saveBalance(SP sp, String balance) {
        sp.putString(KEY_BALANCE, balance);
    }

    public static String getAppid() {
        return mAppid;
    }

    public static void setAppid(String appid) {
        mAppid = appid;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        RetrofitRequestTool.key = key;
    }

    public static void saveToken(SP sp, String token) {
        RetrofitRequestTool.token = token;
        sp.putString(KEY_TOKEN, token);
    }

    public static void saveUuid(SP sp, String uuid) {
        sp.putString(KEY_UUID, uuid);
    }

    public static void savePhone(SP sp, String phone) {
        sp.putString(KEY_PHONE, phone);
    }

    public static String getUuid(SP sp) {
        return sp.getString(KEY_UUID);
    }

    public static String getPhone(SP sp) {
        return sp.getString(KEY_PHONE);
    }

    public static String getToken(SP sp) {
        if (token != null) {
            return token;
        }
        token = sp.getString(KEY_TOKEN, null);
        return token;
    }

    public static Map<String, String> getHeaders(SP sp) {
        initHeaders(sp);
        return mHeaders;
    }

    public static void addHeader(SP sp, String key, String value) {
        initHeaders(sp);
        mHeaders.put(key, value);
        save(sp, mHeaders);
    }

    public static String getHeader(SP sp, String key) {
        initHeaders(sp);
        return mHeaders.get(key);
    }

    public static void saveAvator(SP sp, String avator) {
        RetrofitRequestTool.avator = avator;
        sp.putString(KEY_AVATOR, avator);
    }

    public static void saveNiekName(SP sp, String nikeName) {
        RetrofitRequestTool.nikeName = nikeName;
        sp.putString(KEY_NIKENAME, nikeName);
    }

    public static String getAvator(SP sp) {
        if (RetrofitRequestTool.avator != null) {
            return RetrofitRequestTool.avator;
        }
        return sp.getString(KEY_AVATOR);
    }

    public static String getNikeName(SP sp) {
        if (RetrofitRequestTool.nikeName != null) {
            return RetrofitRequestTool.nikeName;
        }
        return sp.getString(KEY_NIKENAME);
    }

    public static void setHeaders(SP sp, Map<String, String> headers) {
        initHeaders(sp);
        mHeaders.clear();
        for (String key : headers.keySet()) {
            mHeaders.put(key, headers.get(key));
        }
        save(sp, mHeaders);
    }

    public static void remove(SP sp, String key) {
        initHeaders(sp);
        if (mHeaders.containsKey(key)) {
            mHeaders.remove(key);
            save(sp, mHeaders);
        }
    }

    public static void removeAll(SP sp) {
        initHeaders(sp);
        mHeaders.clear();
        save(sp, mHeaders);
    }

    private static void save(SP sp, Map<String, String> headers) {
        Set<String> strings = new HashSet<>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            strings.add(entry.getKey() + SPLITER + entry.getValue());
        }
        sp.putStringSet(REQUEST_HEADERS, strings);
    }

    public static void clearLoginInfo(SP sp) {
        RetrofitRequestTool.saveToken(sp, null);
        RetrofitRequestTool.saveUuid(sp, null);
        RetrofitRequestTool.savePhone(sp, null);
        RetrofitRequestTool.saveAvator(sp, null);
        RetrofitRequestTool.saveNiekName(sp, null);
    }

    private static void initHeaders(SP sp) {
        if (mHeaders == null) {
            mHeaders = new HashMap<>();
            Set<String> strings = sp.getStringSet(REQUEST_HEADERS);
            for (String string : strings) {
                String[] sts = string.split(SPLITER);
                if (sts.length > 1) {
                    mHeaders.put(sts[0], sts[1]);
                }
            }
        }
    }

    public static void setRegisterDate(SP sp, String registerDate) {
        sp.putString(KEY_REGISTER_DATE, registerDate);
    }

    public static String getRegisterDate(SP sp) {
        return sp.getString(KEY_REGISTER_DATE, "");
    }
}
