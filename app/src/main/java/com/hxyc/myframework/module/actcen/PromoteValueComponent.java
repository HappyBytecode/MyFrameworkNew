package com.hxyc.myframework.module.actcen;

import com.hxyc.myframework.annotation.FragmentScope;
import com.hxyc.myframework.common.AppComponent;


import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
        modules = PromoteValueModule.class)
public interface PromoteValueComponent {
    void inject(PromoteValueFragment promoteValueFragment);
}
