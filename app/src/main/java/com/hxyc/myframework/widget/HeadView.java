package com.hxyc.myframework.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.hxyc.myframework.R;


/**
 * 标题栏
 * <p>可以通过 R.layout.head_view 覆盖标题栏的样式</p>
 */
public class HeadView extends FrameLayout {

    private ConstraintLayout mParentLayout;
    private TextView mTitle;
    private ImageView mLeft;
    private ImageView mRight;
    private TextView mRightTxt;
    private ImageView finishBtnForWeb;
    private View mDivider;
    private OnRightClickListener mRightClickListener;
    private OnLeftClickListener mLeftClickListener;

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.head_view, this);
        TypedArray ta = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.HeadView, defStyleAttr, R.style.HeadViewDefStyle);

        mParentLayout = findViewById(R.id.layout_parent);
        mTitle = findViewById(R.id.tx_head_title);
        mLeft = findViewById(R.id.img_head_left);
        mRight = findViewById(R.id.img_head_right);
        mRightTxt = findViewById(R.id.tx_head_right);
        mDivider = findViewById(R.id.divider);
        finishBtnForWeb = findViewById(R.id.finishBtnForWeb);
        String title = ta.getString(R.styleable.HeadView_head_title);
        mTitle.setText(title);

        int leftImg = ta.getResourceId(R.styleable.HeadView_left_image, 0);
        if (leftImg != 0) {
            mLeft.setImageResource(leftImg);
            mLeft.setVisibility(VISIBLE);
        }

        int rightImg = ta.getResourceId(R.styleable.HeadView_right_image, 0);
        if (rightImg != 0) {
            mRight.setImageResource(rightImg);
            mRight.setVisibility(VISIBLE);
            mRightTxt.setVisibility(GONE);
        }

        String rightTxt = ta.getString(R.styleable.HeadView_right_text);
        if (!TextUtils.isEmpty(rightTxt)) {
            mRightTxt.setText(rightTxt);
            mRightTxt.setVisibility(VISIBLE);
            mRight.setVisibility(GONE);
        }

        boolean dividerVisibility = ta.getBoolean(R.styleable.HeadView_dividerVisibility, true);
        mDivider.setVisibility(dividerVisibility ? View.VISIBLE : View.GONE);
        int color = ta.getColor(R.styleable.HeadView_right_text_color
                , ActivityCompat.getColor(getContext(), R.color.tool_bar_right_text_color));
        mRightTxt.setTextColor(color);
        // 默认结束 Activity
        mLeft.setOnClickListener(view -> {
            if (mLeftClickListener == null) {
                ((AppCompatActivity) context).finish();
            } else mLeftClickListener.onLeftClick();
        });
        mRight.setOnClickListener(v -> {
            if (mRightClickListener == null) return;
            mRightClickListener.onRightClick();
        });
        mRightTxt.setOnClickListener(v -> {
            if (mRightClickListener == null) return;
            mRightClickListener.onRightClick();
        });

        ta.recycle();
    }

    public void setHeadViewBackgroundColor(@ColorInt int color) {
        mParentLayout.setBackgroundColor(color);
    }

    public interface OnLeftClickListener {
        void onLeftClick();
    }

    public void setOnLeftClickListener(OnLeftClickListener listener) {
        mLeftClickListener = listener;
    }

    public interface OnRightClickListener {
        void onRightClick();
    }

    public void setOnRightClickListener(OnRightClickListener listener) {
        this.mRightClickListener = listener;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitle(@StringRes int title) {
        mTitle.setText(title);
    }

    public void setTitleVisibility(boolean visible) {
        mTitle.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setLeftVisibility(boolean visible) {
        mLeft.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setDividerVisibility(boolean visible) {
        mDivider.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setLeftImageRes(int resId) {
        mLeft.setImageResource(resId);
    }

    public void setRightImageVisibility(boolean visible) {
        mRight.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setRightImageRes(int resId) {
        mRight.setImageResource(resId);
    }

    public void initFinishBtnForWeb(boolean show) {
        if (show) {
            finishBtnForWeb.setVisibility(View.VISIBLE);
            finishBtnForWeb.setOnClickListener(v -> {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            });
        } else {
            finishBtnForWeb.setVisibility(View.GONE);
        }
    }

    public void setRightTxtVisibility(boolean visible) {
        mRightTxt.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setRightTxt(String txt) {
        mRightTxt.setText(txt);
    }

    public void setRightTextColor(@ColorInt int color) {
        mRightTxt.setTextColor(color);
    }

    @ColorInt
    public int getRightTextColor() {
        return mRightTxt.getCurrentTextColor();
    }


    public void setRightTxtDrawable(int res) {
        Drawable drawable = ActivityCompat.getDrawable(getContext(), res);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mRightTxt.setCompoundDrawables(drawable, null, null, null);
    }

}
