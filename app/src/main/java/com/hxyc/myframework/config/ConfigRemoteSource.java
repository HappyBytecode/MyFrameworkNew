package com.hxyc.myframework.config;

import android.text.TextUtils;

import com.hxyc.myframework.api.CommonApi;
import com.hxyc.myframework.bean.PeelingEntity;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ConfigRemoteSource implements ConfigSource {

    private final CommonApi mCommonApi;

    @Inject
    public ConfigRemoteSource(CommonApi commonApi) {
        this.mCommonApi = commonApi;
    }


    @Override
    public Observable<PeelingEntity> getPeelingConfig() {
        return mCommonApi.getPeelingConfig();
    }

}
