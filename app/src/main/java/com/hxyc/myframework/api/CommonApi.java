package com.hxyc.myframework.api;

import com.hxyc.myframework.bean.PeelingEntity;

import retrofit2.http.POST;
import rx.Observable;

public interface CommonApi {


    /**
     * 换肤配置
     *
     * @return
     */
    @POST("common/home/skin")
    Observable<PeelingEntity> getPeelingConfig();

}
