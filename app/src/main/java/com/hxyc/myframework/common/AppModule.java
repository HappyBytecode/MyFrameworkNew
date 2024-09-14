package com.hxyc.myframework.common;

import android.content.Context;

import com.hxyc.myframework.api.ApiConfig;
import com.hxyc.myframework.api.CommonApi;
import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.network.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public SP provideSP(Context context) {
        return new SP(context);
    }

    @Provides
    @Singleton
    public CommonApi provideCommonApi(SP sp) {
        return RetrofitUtil.getService(mContext, CommonApi.class, ApiConfig.getCommonApi(), sp);
    }
}
