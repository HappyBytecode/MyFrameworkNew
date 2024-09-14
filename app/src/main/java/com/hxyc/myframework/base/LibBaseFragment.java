package com.hxyc.myframework.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.hxyc.myframework.util.ToastUtil;


public class LibBaseFragment extends Fragment {
    private static final String BUNDLE_FRAGMENTS_KEY = "android:support:fragments";
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //重建时清除 fragment的状态
            savedInstanceState.remove(BUNDLE_FRAGMENTS_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            //销毁时不保存fragment的状态
            outState.remove(BUNDLE_FRAGMENTS_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mView;
    }

//    public View getView() {
//        return mView;
//    }


    public void showLoadingView(boolean fullScreen) {
        if (!isAdded()) return;
        if (requireActivity() instanceof LibBaseActivity) {
            ((LibBaseActivity) requireActivity()).showLoadingView(fullScreen);
        }
    }

    public void hideLoadingView() {
        if (requireActivity() instanceof LibBaseActivity) {
            ((LibBaseActivity) requireActivity()).hideLoadingView();
        }
    }

    public void toast(String msg) {
        ToastUtil.getInstance().toast(msg);
    }


    public void toast(@StringRes int msgId) {
        ToastUtil.getInstance().toast(msgId);
    }

}
