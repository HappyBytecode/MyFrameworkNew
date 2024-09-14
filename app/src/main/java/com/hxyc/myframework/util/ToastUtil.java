package com.hxyc.myframework.util;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;

import com.hxyc.myframework.R;

/**
 * 自定义Toast
 */
public class ToastUtil {

    private static String mLastToast = null;

    private ToastUtil() {
    }

    private static ToastUtil instance;

    private static Context mContext;

    public static ToastUtil getInstance() {
        if (instance == null) {
            synchronized (ToastUtil.class) {
                if (instance == null) {
                    instance = new ToastUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 在Application中调用，省去每次toast需要传的context,
     */
    public static void init(Context mContext) {
        ToastUtil.mContext = mContext;
    }

    // Toast对象
    private android.widget.Toast mToast;
    private TextView mTextView;

    /**
     * 显示toast
     */
    public void toast(String content) {
        mLastToast = content;
        mToast = new android.widget.Toast(mContext);
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.base_lay_toast, null);
        mTextView = view.findViewById(R.id.tvToast);
        mToast.setView(view);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(android.widget.Toast.LENGTH_SHORT);
        mTextView.setText(content);
        mToast.show();
    }
    public void toastLong(String content) {
        mLastToast = content;
        mToast = new android.widget.Toast(mContext);
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.base_lay_toast, null);
        mTextView = view.findViewById(R.id.tvToast);
        mToast.setView(view);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(android.widget.Toast.LENGTH_LONG);
        mTextView.setText(content);
        mToast.show();
    }
    /**
     * 显示toast
     */
    public void toast(int resId) {
        String content = mContext.getString(resId);
        toast(content);
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    @VisibleForTesting
    public String getLastToast() {
        return mLastToast;
    }

}
