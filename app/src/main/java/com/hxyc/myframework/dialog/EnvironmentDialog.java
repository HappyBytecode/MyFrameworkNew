package com.hxyc.myframework.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Process;

import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.util.ToastUtil;

public class EnvironmentDialog {
    public static String KEY_ENVIRONMENT = "key_environment";
    private static String[] items= {"测试", "预生产", "华为云外网", "正式环境", "外网测试", "正式环境-单节点"};
    private static volatile EnvironmentDialog instance;

    private EnvironmentDialog(){}

    public static EnvironmentDialog getInstance(){
        if(instance==null){
            synchronized (EnvironmentDialog.class){
                if(instance == null){
                    instance = new EnvironmentDialog();
                    return instance;
                }
            }
        }
        return instance;
    }

    public void showDialog(Context context){
        SP sp = new SP(context);
        Dialog dialog = new AlertDialog.Builder(context).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sp.putInt(KEY_ENVIRONMENT, which);
                dialog.dismiss();
                ToastUtil.getInstance().toast("退出应用，杀死进程后生效");
                Process.killProcess(Process.myPid());
            }
        }).create();
    }
}
