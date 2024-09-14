package com.hxyc.myframework;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.request.target.ViewTarget;
import com.hxyc.myframework.api.ApiConfig;
import com.hxyc.myframework.base.BaseApplication;
import com.hxyc.myframework.common.AppComponent;
import com.hxyc.myframework.common.AppModule;
import com.hxyc.myframework.common.DaggerAppComponent;
import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.dialog.EnvironmentDialog;
import com.hxyc.myframework.util.CrashHandler;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

public class Application extends BaseApplication {
    private ArrayList<Activity> list = new ArrayList<Activity>();

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    private static AppComponent sAppComponent;
//    @Inject
//    ConfigRepository mConfigRepository;
    @Inject
    SP mSP;

    static {
        RxJavaHooks.setOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    if (throwable != null && throwable.getMessage() != null) {
                        Log.i("Error", throwable.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        CrashHandler.getInstance().init(this);
        ViewTarget.setTagId(R.id.glide_tag);
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        sAppComponent.inject(this);
        boolean isMainProcess = getApplicationContext().getPackageName().equals
                (getCurrentProcessName());
        //防止多次初始化 影响性能
        if (isMainProcess) {
            try {
                lazyLoad();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        requestConfig();
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    static {

    }

    private void initConfig() {
//        RetrofitRequestTool.setKey(AppConfig.REQUEST_PAIM_KEY);
//        RetrofitRequestTool.setAppid(AppConfig.REQUEST_PAIM_APPID);
        if (BuildConfig.DEBUG) {
            int select = new SP(this).getInt(EnvironmentDialog.KEY_ENVIRONMENT, 0);
            if (select == 0) {
                ApiConfig.setHost(false, getResources().
                        getString(R.string.app_config_debug_host));
            } else if (select == 1) {
                ApiConfig.setHost(false, getResources().
                        getString(R.string.app_config_debug_host_huaweiyun));
            } else if (select == 2) {
                ApiConfig.setHost(true, getResources().
                        getString(R.string.app_config_host_huaweiyun));
            } else if (select == 3) {
                ApiConfig.setHost(true, getResources().
                        getString(R.string.app_config_host));
            } else if (select == 4) {
                ApiConfig.setHost(false, getResources().
                        getString(R.string.app_config_debug_out_host));
            } else if (select == 5) {
                ApiConfig.setHost(false, getResources().
                        getString(R.string.app_config_host_one));
            }
        } else {
            ApiConfig.setHost(true, getResources().
                    getString(R.string.app_config_host));
        }
    }

    /**
     * 采用懒加载的方式减少主线程阻塞
     */
    public void lazyLoad() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }



    /**
     * 获取当前进程名
     */
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    CompositeSubscription mSubscriptions = new CompositeSubscription();

    /**
     * 获取系统配置
     */
    /*private void requestConfig() {
        mSubscriptions.add(mConfigRepository.getPeelingConfig()
                .compose(RxUtil.applySchedulers())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object entity) {
                        if (entity != null) {
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }));
    }*/

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * Activity关闭时，删除Activity列表中的Activity对象*/
    public void removeActivity(Activity a){
        list.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象*/
    public void addActivity(Activity a){
        list.add(a);
    }

    /**
     * 关闭Activity列表中的所有Activity*/
    public void finishActivity(){
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
