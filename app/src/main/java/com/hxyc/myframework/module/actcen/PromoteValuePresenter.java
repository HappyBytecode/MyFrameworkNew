package com.hxyc.myframework.module.actcen;


import com.hxyc.myframework.R;
import com.hxyc.myframework.base.BasePresenter;
import com.hxyc.myframework.bean.PeelingEntity;
import com.hxyc.myframework.config.ConfigRepository;
import com.hxyc.myframework.event.TestEvent;
import com.hxyc.myframework.util.RxUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.functions.Action1;

public class PromoteValuePresenter extends BasePresenter implements PromoteValueContract.Presenter {

    private PromoteValueContract.View mView;
    private ConfigRepository mUserRepository;

    @Inject
    public PromoteValuePresenter(PromoteValueContract.View view,
                                 ConfigRepository userRepository) {
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void subscribe() {
        mSubscriptions.add(mUserRepository.getPeelingConfig()
                .compose(RxUtil.applySchedulers())
                .subscribe(new Action1<PeelingEntity>() {
                    @Override
                    public void call(PeelingEntity entity) {
//                        mView.showBasic(entity.getBasic());
//                        mView.showConsume(entity.getConsume());
//                        mView.showActive(entity.getActive());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable ex) {
                        showNetworkError(ex, R.string.network_error, mView);
                        EventBus.getDefault().post(new TestEvent(0));
                    }
                }));
    }

    @Override
    public void unSubscribe() {
        super.unSubscribe();
    }

}
