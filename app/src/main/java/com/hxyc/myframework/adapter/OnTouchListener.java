package com.hxyc.myframework.adapter;

import android.view.MotionEvent;
import android.view.View;


public interface OnTouchListener<T> {
    boolean onTouch(int position, View view, T item, MotionEvent event);
}
