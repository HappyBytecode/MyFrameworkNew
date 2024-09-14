package com.hxyc.myframework.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.network.Interceptor.ReceivedInterceptor;
import com.hxyc.myframework.network.Interceptor.RequestInterceptor;
import com.hxyc.myframework.network.converter.FastJsonConverterFactory;

import org.jetbrains.annotations.NotNull;
import org.litepal.BuildConfig;

import java.io.File;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class RetrofitUtil {

    private RetrofitUtil() {

    }

    /**
     * @param tClass retrofitAPI 类
     * @param host   服务器地址（必须以 / 结尾）
     * @param sp     SharePreference 用于保存token、header等
     * @return
     */
    public static <T> T getService(Context context, Class<T> tClass, String host, SP sp) {

        SSLSocketFactory sslSocketFactory = setCertificates();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NotNull String message) {
                        Log.i("OkHttp", message);
                    }
                }
        );

        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .proxy(BuildConfig.DEBUG ? null : Proxy.NO_PROXY)
                .addInterceptor(new ReceivedInterceptor(sp))
                .addInterceptor(new RequestInterceptor(sp, context == null ? "" : context.getPackageName()))
                .addInterceptor(logging)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        //                        所有hostname都可以验证通过
                        return true;
                    }
                })
                .sslSocketFactory(sslSocketFactory)
                .build();
        if (TextUtils.isEmpty(host)) {
            host = "http://localhost/";
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())  // 添加 fastJson 解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加 RxJava 适配器
                .build();
        return retrofit.create(tClass);
    }

    //设置证书
    public static SSLSocketFactory setCertificates() {
        try {
//            //创建一个使用我们信任管理器的SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");

            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
            sslContext.init(null, trustManagers, new SecureRandom());    //全部信任
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Retrofit 使用 OkHttp 上传时的 RequestBody
     *
     * @param value 参数
     * @return
     */
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    /**
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part getRequestPartFile(String key, File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), fileBody);
    }

    /**
     * Retrofit 使用 OkHttp 上传时的 file 包装
     *
     * @param key  参数
     * @param file 文件名
     * @return
     */
    public static MultipartBody.Part getRequestPart(String key, File file) {
        RequestBody fileBody = MultipartBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), fileBody);
    }

    /**
     * @param key   参数
     * @param files 文件名列表
     * @return
     */
    public static MultipartBody.Part[] getRequestParts(String key, File... files) {
        MultipartBody.Part[] parts = new MultipartBody.Part[files.length];
        for (int i = 0; i < files.length; i++) {
            parts[i] = getRequestPart(key, files[i]);
        }
        return parts;
    }
}
