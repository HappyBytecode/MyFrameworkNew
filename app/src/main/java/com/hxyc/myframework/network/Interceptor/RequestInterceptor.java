package com.hxyc.myframework.network.Interceptor;

import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.network.RetrofitRequestTool;
import com.hxyc.myframework.network.entity.LogRecordEntity;
import com.hxyc.myframework.util.Logger;
import com.hxyc.myframework.util.NetUtil;
import com.hxyc.myframework.util.security.EncryptionUtil;

import org.jetbrains.annotations.NotNull;
import org.litepal.BuildConfig;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import static android.os.Build.VERSION.SDK_INT;

public class RequestInterceptor implements Interceptor {
    private static final String KEY_SIGN = "sign";
    private static final String KEY_APPID = "appid";
    private static final String KEY_NONCESTR = "noncestr";
    private static final String KEY_TOKEN = "token";
    private static final String DVICE_TYPE = "deviceType";
    private static final String DEVICE_VERSION = "deviceVersion";
    private static final String APP_VERSION = "appVersion";
    private static final String COOKIE = "cookie";
    private static final String PACKAGENAME = "packageName";

    private final SP mSp;
    private String mAppVersion, mDeviceType, mDeviceVersion, mCookie, mPackageName;

    public RequestInterceptor(SP sp, String packageName) {
        mSp = sp;
        mAppVersion = BuildConfig.VERSION_NAME;
        mDeviceType = "1";
        mDeviceVersion = Build.MODEL + " - " + SDK_INT;
        mPackageName = packageName;
        if (!TextUtils.isEmpty(mAppVersion)) {
            try {
                JSONObject object = new JSONObject();
                object.put("appVersion", mAppVersion);
                mCookie = JSONObject.toJSONString(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        // 添加 签名等
        Request original = chain.request();
        SortMap sortMap = new SortMap();

        // 参数的排序、加密

        // FormBody
        FormBody.Builder newFormBody = new FormBody.Builder();
        if (original.body() instanceof FormBody) {
            FormBody oidFormBody = (FormBody) original.body();
            for (int i = 0; i < oidFormBody.size(); i++) {
                String key = oidFormBody.encodedName(i);
                String value = oidFormBody.encodedValue(i);
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    newFormBody.addEncoded(key, value);
                    sortMap.put(URLDecoder.decode(key), URLDecoder.decode(value));
                }
            }
        }
        String requestUrl = original.url().toString();

        // MultipartBody
        MultipartBody.Builder newMultipartBody = null;
        if (original.body() instanceof MultipartBody) {
            MultipartBody oldMultipartBody = (MultipartBody) original.body();
            newMultipartBody = new MultipartBody.Builder();

            for (int i = 0; i < oldMultipartBody.size(); i++) {
                MultipartBody.Part part = oldMultipartBody.parts().get(i);
                MediaType mediaType = part.body().contentType();
                if (mediaType.equals(MediaType.parse("multipart/form-data; charset=utf-8"))) {
                    try {
                        Headers contentHeaders = part.headers();
                        if (contentHeaders != null) {
                            for (String name : contentHeaders.names()) {
                                String headerContent = contentHeaders.get(name);
                                if (!TextUtils.isEmpty(headerContent)) {
                                    String replaceValue = "form-data; name=";//这段在MultipartBody.Part源码中看到
                                    if (headerContent.contains(replaceValue)) {
                                        String key = headerContent.replace(replaceValue, "").replaceAll("\"", "");
                                        String data = getParamContent(oldMultipartBody.part(i).body());
                                        if (!TextUtils.isEmpty(data)) {
                                            sortMap.put(key, data);
                                            newMultipartBody.addPart(part);
                                        }
                                        break;
                                    }

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    newMultipartBody.addPart(part);

                    try {
                        Field[] f = part.getClass().getDeclaredFields();
                        for (Field field : f) {
                            field.setAccessible(true);
                            String content = "" + field.get(part);

                            int keyIndex = content.indexOf("name=");
                            int valueIndex = content.toLowerCase().indexOf("filename=");
                            if (keyIndex == -1 || valueIndex == -1) {
                                continue;
                            }
                            keyIndex += 6;
                            valueIndex += 10;
                            String key = content.substring(keyIndex, content.indexOf("\"", keyIndex));
                            String value = content.substring(valueIndex, content.indexOf("\"", valueIndex)).replaceAll(".txt", "");
                            if ("logFile".equals(key)) {
                                if (requestUrl.contains("log/local/upload")) {
                                    sortMap.put("fileName", URLDecoder.decode(value));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        //
        Map<String, String> params = NetUtil.getUrlParams(chain.request().url().query());
        if (params != null) {
            for (Map.Entry<String, String> enty : params.entrySet()) {
                String key = enty.getKey();
                String value = enty.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    sortMap.put(URLDecoder.decode(key), URLDecoder.decode(value));
                }
            }
        }

        // mAppVersion
        if (mAppVersion != null) {
            sortMap.put(APP_VERSION, mAppVersion);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(APP_VERSION, mAppVersion);
            else {
                newFormBody.addEncoded(APP_VERSION, mAppVersion);
            }
        }
        // imei

        // mDeviceType
        if (mDeviceType != null) {
            sortMap.put(DVICE_TYPE, mDeviceType);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(DVICE_TYPE, mDeviceType);
            else {
                newFormBody.addEncoded(DVICE_TYPE, mDeviceType);
            }
        }
        // mDeviceVersion
        if (mDeviceVersion != null) {
            sortMap.put(DEVICE_VERSION, mDeviceVersion);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(DEVICE_VERSION, mDeviceVersion);
            else {
                newFormBody.addEncoded(DEVICE_VERSION, mDeviceVersion);
            }
        }
        // mCookie
        if (mCookie != null) {
            sortMap.put(COOKIE, mCookie);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(COOKIE, mCookie);
            else {
                newFormBody.addEncoded(COOKIE, mCookie);
            }
        }
        // mPackageName
        if (mPackageName != null) {
            sortMap.put(PACKAGENAME, mPackageName);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(PACKAGENAME, mPackageName);
            else {
                newFormBody.addEncoded(PACKAGENAME, mPackageName);
            }
        }
        // appid
        String appId = RetrofitRequestTool.getAppid();
        if (appId != null) {
            sortMap.put(KEY_APPID, appId);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(KEY_APPID, appId);
            else {
                newFormBody.addEncoded(KEY_APPID, appId);
            }
        }
        // nocestr
        String noncestr = System.currentTimeMillis() + "";
        sortMap.put(KEY_NONCESTR, noncestr);
        if (newMultipartBody != null)
            newMultipartBody.addFormDataPart(KEY_NONCESTR, noncestr);
        else {
            newFormBody.addEncoded(KEY_NONCESTR, noncestr);
        }
        // token
        String token = RetrofitRequestTool.getToken(mSp);
        if (token != null) {
            sortMap.put(KEY_TOKEN, token);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(KEY_TOKEN, token);
            else {
                newFormBody.addEncoded(KEY_TOKEN, token);
            }
        }
        // sign
        if (newMultipartBody != null)
            newMultipartBody.addFormDataPart(KEY_SIGN, getClientSign(sortMap));
        else {
            newFormBody.addEncoded(KEY_SIGN, getClientSign(sortMap));
        }


        // 记录日志
        StringBuilder str = new StringBuilder();
        str.append(requestUrl);
        str.append(" ");
        Logger.e("url=" + requestUrl);
        if (sortMap.size() > 0) {
            for (String key : sortMap.keySet()) {
                String param = key + "=" + sortMap.get(key);
                str.append(param);
                str.append(" ");
                Logger.e(param);
            }
        }
        boolean isSave = new LogRecordEntity(
                System.currentTimeMillis(),
                "网络请求",
                str.toString()
        ).save();
        Logger.e("日志是否保存成功：" + isSave);

        final Request.Builder builder = chain.request().newBuilder();
        builder.method(original.method(), newFormBody.build());
        if (newMultipartBody != null)
            builder.method(original.method(), newMultipartBody.build());

//        for (int i = 0; i < newFormBody.build().size(); i++) {
//            KLog.e(newFormBody.build().encodedName(i) + " = " + URLDecoder.decode(newFormBody.build().encodedValue(i), "utf-8"));
//        }
//        KLog.e(URLDecoder.decode(original.url().toString(), "utf-8"));
        return chain.proceed(builder.build());
    }


    private String getParamContent(RequestBody body) throws IOException {
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        return buffer.readUtf8();
    }

    private static class SortMap extends TreeMap<String, String> {
        public SortMap() {
            super(new Comparator<String>() {
                @Override
                public int compare(String obj1, String obj2) {
                    return -obj2.compareTo(obj1);
                }
            }); // 升序排序
        }
    }

    private static String getClientSign(Map<String, String> map) {
        StringBuilder params = new StringBuilder();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String val = map.get(key);
            params.append(key);
            params.append("=");
            params.append(val);
            params.append("&");
        }
        params.append("key=").append(RetrofitRequestTool.getKey());
        return EncryptionUtil.md5Encode(params.toString()).toUpperCase();
    }
}
