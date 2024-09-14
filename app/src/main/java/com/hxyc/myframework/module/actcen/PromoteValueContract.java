package com.hxyc.myframework.module.actcen;


import com.hxyc.myframework.base.IBasePresenter;
import com.hxyc.myframework.base.IBaseView;
import com.hxyc.myframework.bean.PromoteItemEntity;

import java.util.List;


public interface PromoteValueContract {
    interface View extends IBaseView<Presenter> {
        void showBasic(List<PromoteItemEntity.BasicBean> basicBeans);
    }

    interface Presenter extends IBasePresenter {

    }
}
