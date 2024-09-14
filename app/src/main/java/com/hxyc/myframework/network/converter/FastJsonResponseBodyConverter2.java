package com.hxyc.myframework.network.converter;

import com.alibaba.fastjson.JSON;
import com.hxyc.myframework.network.RequestError;
import com.hxyc.myframework.util.Logger;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 此处应该后台改接口返回码的，但是后台不愿意改 所以只能单写一个来适配后台的接口
 * * @param <T>
 */
public class FastJsonResponseBodyConverter2<T> implements Converter<ResponseBody, T> {

    private final Type mType;

    FastJsonResponseBodyConverter2(Type type) {
        this.mType = type;

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        Logger.e("response", string);
        RequestBean bean = JSON.parseObject(string, RequestBean.class);

        if (bean.getResultCode() == 200) {
            if (mType == String.class)
                return (T) bean.getData();
            return JSON.parseObject(bean.getData(), mType);
        }
        throw new RequestError(bean.resultCode, bean.message);
    }

    private static class RequestBean {
        private Long returnTime; // 时间戳

        private Integer resultCode; // 错误码

        private String message; // 提示语

        private String data;

        public Long getReturnTime() {
            return returnTime;
        }

        public void setReturnTime(Long returnTime) {
            this.returnTime = returnTime;
        }

        public Integer getResultCode() {
            if (resultCode == null) return -1;
            return resultCode;
        }

        public void setResultCode(Integer returnCode) {
            this.resultCode = returnCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String msg) {
            this.message = msg;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "RequestBean{" +
                    "returnTime=" + returnTime +
                    ", resultCode=" + resultCode +
                    ", message='" + message + '\'' +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
