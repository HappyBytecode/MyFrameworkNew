package com.hxyc.myframework.common;

import android.app.Application;

import com.hxyc.myframework.base.LibBaseActivity;
import com.hxyc.myframework.config.ConfigRepository;
import com.hxyc.myframework.db.SP;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {


    ConfigRepository configRepository();


    SP sp();


    void inject(Application application);
    void inject(LibBaseActivity baseActivity);

//    void inject(MainActivity mainActivity);


//    void inject(BaseActivity baseActivity);

}
