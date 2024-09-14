package com.hxyc.myframework.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;


import com.hxyc.myframework.util.Logger;
import com.hxyc.myframework.util.ToastUtil;

import org.litepal.LitePalApplication;

import java.lang.reflect.Field;


public class BaseApplication extends LitePalApplication {

    public static Application baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        initBaseConfig();
        ToastUtil.init(this);
        if (BaseConfig.DEBUG) {
        }
        baseApplication = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 初始化 {@link BaseConfig} 用于 Tinker 热修复等
     */
    private void initBaseConfig() {
        Logger.setDevelopMode(BaseConfig.DEBUG);
        Log.d("bin-->", "MyConfig.DEBUG=" + BaseConfig.DEBUG);
    }

    private <T> T getConfigField(Class buildConfig, String field) throws NoSuchFieldException, IllegalAccessException {
        Field field1 = buildConfig.getField(field);
        boolean access = field1.isAccessible();
        if (!access) field1.setAccessible(true);
        T value = (T) field1.get(buildConfig);
        if (!access) field1.setAccessible(false);
        return value;
    }

}
