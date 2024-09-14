package com.hxyc.myframework.network.Interceptor;

import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.network.RetrofitRequestTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class ReceivedInterceptor implements Interceptor {

    private static final String TAG = "ReceivedInterceptor";
    private final SP mSp;

    public ReceivedInterceptor(SP sp) {
        mSp = sp;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            Observable.from(originalResponse.headers("Set-Cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";"); // JSESSIONID=aaaQElHouiqmGh-oaQCtv; path=/
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            RetrofitRequestTool.addHeader(mSp, "Cookie", cookie);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
        }
        return originalResponse;
    }
}
