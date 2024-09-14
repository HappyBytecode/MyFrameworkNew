package com.hxyc.myframework.base;

import android.text.TextUtils;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hxyc.myframework.R;
import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.network.RequestError;
import com.hxyc.myframework.network.RetrofitRequestTool;
import com.hxyc.myframework.network.entity.LogRecordEntity;
import com.hxyc.myframework.util.Logger;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;


public abstract class BasePresenter implements IBasePresenter {

    protected CompositeSubscription mSubscriptions = new CompositeSubscription();
    protected boolean mFirstSubscribe = true;
    @Inject
    SP mSP;

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mFirstSubscribe = false;
        mSubscriptions.clear();
    }

    protected void showNetworkError(Throwable e, String defErrorMsg, IBaseView view) {
        if (!showNetworkError(e, view)) {
            view.toast(defErrorMsg);
        }
    }

    protected void showNetworkError(Throwable e, @StringRes int defErrorMsg, IBaseView view) {
        e.printStackTrace();
        if (!showNetworkError(e, view)) {
            view.toast(defErrorMsg);
        }
        new LogRecordEntity(System.currentTimeMillis(), "请求报错", e.toString()).save();
    }

    private boolean showNetworkError(Throwable e, IBaseView view) {
        Throwable throwable = e;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        if (throwable instanceof RequestError) {
            String msg = ((RequestError) throwable).getMsg();
            if (!TextUtils.isEmpty(msg)) {
                view.toast(msg);
            } else {
                view.toast(R.string.server_error);
            }
            Logger.e("BasePresenter-->", "BasePresenter#showNetworkErrorTip(): (from RequestBean) " + e.getMessage());
//            if (((RequestError) throwable).getReturnCode() == 10012) {
            AppCompatActivity activity = null;
            if (view instanceof AppCompatActivity) {
                activity = (AppCompatActivity) view;
            } else if (view instanceof Fragment) {
                activity = (AppCompatActivity) ((Fragment) view).getActivity();
            }
            if (activity == null) return false;
            if (((RequestError) throwable).getReturnCode() == 91002) {
                RetrofitRequestTool.saveToken(mSP, null);
                RetrofitRequestTool.saveUuid(mSP, null);
                RetrofitRequestTool.savePhone(mSP, null);
                RetrofitRequestTool.saveAvator(mSP, null);
                RetrofitRequestTool.saveNiekName(mSP, null);
            }
            return true;
        }
        if ("timeout".equals(e.getMessage())) {
            view.toast(R.string.network_timeout);
            Logger.e("bin-->", "BasePresenter#showNetworkErrorTip(): timeout ");
            return true;
        }
        return false;
    }
}
