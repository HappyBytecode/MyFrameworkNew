package com.hxyc.myframework;

import android.os.Bundle;

import com.hxyc.myframework.base.LibBaseActivity;
import com.hxyc.myframework.module.actcen.PromoteValueActivity;

/**
 * Created by liuwenwu on 2023/6/29.
 * Des :
 */
public class MainActivity extends LibBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_title).setOnClickListener(v -> PromoteValueActivity.startIntent(MainActivity.this));
    }
}

