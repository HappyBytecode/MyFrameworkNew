package com.hxyc.myframework.module.actcen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hxyc.myframework.R;
import com.hxyc.myframework.base.LibBaseActivity;


public class PromoteValueActivity extends LibBaseActivity {

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, PromoteValueActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        findViewById(R.id.tv_title).setOnClickListener(v -> {

        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

}
