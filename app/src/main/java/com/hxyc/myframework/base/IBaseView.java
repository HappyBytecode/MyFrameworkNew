package com.hxyc.myframework.base;

import androidx.annotation.StringRes;

public interface IBaseView<T> {

    /**
     * 是否已经 add 到 Activity 中
     *
     * @return
     */
    void showLoadingView(boolean fullScreen);

    void hideLoadingView();

    void toast(String msg);

    void toast(@StringRes int msgId);

}
