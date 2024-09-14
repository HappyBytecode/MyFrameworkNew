package com.hxyc.myframework.network.converter;


import com.alibaba.fastjson.serializer.SerializeConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public final class FastJsonConverterFactory2 extends Converter.Factory {

    public static FastJsonConverterFactory2 create() {
        return new FastJsonConverterFactory2(SerializeConfig.getGlobalInstance());
    }

    /**
     * Create an instance using {@code mapper} for conversion.
     */
    public static FastJsonConverterFactory2 create(SerializeConfig serializeConfig) {
        return new FastJsonConverterFactory2(serializeConfig);
    }

    private final SerializeConfig mSerializeConfig;

    private FastJsonConverterFactory2(SerializeConfig serializeConfig) {
        if (serializeConfig == null) throw new NullPointerException("mapper == null");
        this.mSerializeConfig = serializeConfig;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter2<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>(mSerializeConfig);
    }
}