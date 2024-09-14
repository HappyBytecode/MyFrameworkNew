package com.hxyc.myframework.network.converter;

import com.alibaba.fastjson.JSON;
import com.hxyc.myframework.network.RequestBean;
import com.hxyc.myframework.network.RequestError;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type mType;

    FastJsonResponseBodyConverter(Type type) {
        this.mType = type;

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
//        KLog.e("response", string);
        RequestBean bean = JSON.parseObject(string, RequestBean.class);

        if (bean.getReturnCode() == 10000) {
            if (mType == String.class)
                return (T) bean.getData();
            return JSON.parseObject(bean.getData(), mType);
        }
        throw new RequestError(bean);
    }
}
