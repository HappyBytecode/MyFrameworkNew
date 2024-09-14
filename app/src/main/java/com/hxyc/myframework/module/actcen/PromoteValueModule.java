package com.hxyc.myframework.module.actcen;

import dagger.Module;
import dagger.Provides;

@Module
public class PromoteValueModule {

    private PromoteValueContract.View mView;

    public PromoteValueModule(PromoteValueContract.View view) {
        mView = view;
    }

    @Provides
    PromoteValueContract.View providePromoteValueContractView() {
        return mView;
    }
}
