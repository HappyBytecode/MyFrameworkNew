package com.hxyc.myframework.adapter.internal;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.FloatRange;
import androidx.recyclerview.widget.RecyclerView;


interface ChainSetter<VH> {

    VH setText(int viewId, CharSequence text);

    VH setText(int viewId, int strRes);

    VH setTextColor(int viewId, int textColor);

    VH setTextColor(int viewId, ColorStateList colorStateList);

    VH setMovementMethod(int viewId, MovementMethod method);

    VH setImageResource(int viewId, int imgResId);

    VH setImageDrawable(int viewId, Drawable drawable);

    VH setImageBitmap(int viewId, Bitmap bitmap);

    VH setImageUri(int viewId, Uri imageUri);

    VH setScaleType(int viewId, ImageView.ScaleType type);

    VH setBackgroundColor(int viewId, int bgColor);

    VH setBackgroundBitmap(int viewId, Bitmap bitmap);

    VH setBackgroundResource(int viewId, int bgRes);

    VH setColorFilter(int viewId, ColorFilter colorFilter);

    VH setColorFilter(int viewId, int colorFilter);

    VH setAlpha(int viewId, @FloatRange(from = 0.0, to = 1.0) float value);

    VH setVisibility(int viewId, int visibility);

    VH setMax(int viewId, int max);

    VH setProgress(int viewId, int progress);

    VH setRating(int viewId, float rating);

    VH setTag(int viewId, Object tag);

    VH setTag(int viewId, int key, Object tag);

    VH setEnabled(int viewId, boolean enabled);

    VH setAdapter(int viewId, Adapter adapter);

    VH setAdapter(int viewId, RecyclerView.Adapter adapter);

    VH setChecked(int viewId, boolean checked);

    VH setOnClickListener(int viewId, View.OnClickListener listener);

    VH setOnLongClickListener(int viewId, View.OnLongClickListener listener);

    VH setOnTouchListener(int viewId, View.OnTouchListener listener);

    VH setTypeface(int viewId, Typeface typeface);
}
