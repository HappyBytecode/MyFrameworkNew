package com.hxyc.myframework.network;

public class RequestError extends RuntimeException {

    public RequestError(RequestBean bean) {
        mMsg = bean.getMsg();
        mReturnCode = bean.getReturnCode();
        mData = bean.getData();
    }

    public RequestError(int returnCode, String msg) {
        this.mReturnCode = returnCode;
        this.mMsg = msg;
    }

    private int mReturnCode; // 错误码

    private String mMsg; // 提示语

    private String mData;

    public int getReturnCode() {
        return mReturnCode;
    }

    public void setReturnCode(int returnCode) {
        this.mReturnCode = returnCode;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        this.mData = data;
    }

    @Override
    public String toString() {
        return "RequestError{" +
                "mReturnCode=" + mReturnCode +
                ", mMsg='" + mMsg + '\'' +
                ", mData='" + mData + '\'' +
                '}';
    }

}
