package com.hxyc.myframework.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.hxyc.myframework.R;
import com.hxyc.myframework.Application;
import com.hxyc.myframework.common.AppComponent;
import com.hxyc.myframework.util.AppManager;
import com.hxyc.myframework.util.Logger;
import com.hxyc.myframework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class LibBaseActivity extends AppCompatActivity {

    private ProgressBar mLoadingView = null;


    private LinearLayout mLlLoadingView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        AppManager.getAppManager().addActivity(this);
        Application.getAppComponent().inject(this);
    }

    public AppComponent getAppComponent() {
        return Application.getAppComponent();
    }

    public void toast(int res) {
        ToastUtil.getInstance().toast(res);
    }

    public void toast(String msg) {
        ToastUtil.getInstance().toast(msg);
    }

    public void showLoadingView(boolean fullScreen) {
        if (mLoadingView == null) {
            mLoadingView = findViewById(R.id.loading_view);
        }
        if (mLlLoadingView == null) {
            mLlLoadingView = findViewById(R.id.ll_loading_view);
        }
        if (mLoadingView == null) {
            Logger.e("LibBaseActivity-->", "LibBaseActivity#showLoadingView(): LoadingView 不存在");
            return;
        }
        mLlLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        if (mLoadingView != null) {
            mLlLoadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 添加 fragment
     *
     * @param idRes    容器ID
     * @param fragment 待添加的 fragment
     */
    protected void addFragment(@IdRes int idRes, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(idRes, fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 替换 fragment
     *
     * @param idRes    容器ID
     * @param fragment 待替换的 fragment
     */
    protected void replaceFragment(@IdRes int idRes, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(idRes, fragment);
        ft.commitAllowingStateLoss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        AppManager.getAppManager().removeActivity(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(Object event) {
    }
}
