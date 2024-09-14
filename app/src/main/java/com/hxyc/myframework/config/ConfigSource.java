package com.hxyc.myframework.config;

import com.hxyc.myframework.bean.PeelingEntity;

import rx.Observable;

public interface ConfigSource {


    Observable<PeelingEntity> getPeelingConfig();

}
