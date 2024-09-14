package com.hxyc.myframework.config;

import com.hxyc.myframework.bean.PeelingEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ConfigRepository implements ConfigSource {

    private final ConfigSource mRemoteConfigSource;

    @Inject
    public ConfigRepository(ConfigRemoteSource mRemoteConfigSource) {
        this.mRemoteConfigSource = mRemoteConfigSource;
    }


    @Override
    public Observable<PeelingEntity> getPeelingConfig() {
        return mRemoteConfigSource.getPeelingConfig();
    }
}
