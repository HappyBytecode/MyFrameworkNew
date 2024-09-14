package anda.travel.passenger.view.refreshview;

import java.lang.System;

/**
 * PackageName : com.ziwenl.library.widgets
 * Author : Ziwen Lan
 * Date : 2020/5/13
 * Time : 11:23
 * Introduction :仿小红书登陆页面背景图无限滚动 FrameLayout
 * 功能特点：
 * 1.将选择的图片按比例缩放填满当前 View 高度
 * 2.背景图片缩放后宽/高度小于当前 View 宽/高度时自动复制黏贴直到占满当前 View 宽/高度，以此来达到无限滚动效果
 * 3.可通过自定义属性 speed 调整滚动速度，提供 slow、ordinary 和 fast 选项，也可自行填入 int 值，值越大滚动速度越快，建议 1 ≤ speed ≤ 50
 * 4.可通过自定义属性 maskLayerColor 设置遮罩层颜色，建议带透明度
 * 5.提供 startScroll 和 stopScroll 方法控制开始/停止滚动
 * 6.可通过自定义属性 scrollOrientation 设置滚动方向为上移或左移,默认滚动方向为上移
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 ,2\u00020\u0001:\u0001,B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J(\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0014J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001aH\u0002J\b\u0010\'\u001a\u00020\u000fH\u0002J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001aJ\u0006\u0010*\u001a\u00020\u001cJ\u0006\u0010+\u001a\u00020\u001cR\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Landa/travel/passenger/view/refreshview/LoopScrollView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mBitmapCount", "mDrawable", "Landroid/graphics/drawable/Drawable;", "mIntervalIncreaseDistance", "", "mIsScroll", "", "mMaskLayerColor", "mMatrix", "Landroid/graphics/Matrix;", "mPaint", "Landroid/graphics/Paint;", "mPanDistance", "mRedrawRunnable", "Ljava/lang/Runnable;", "mScrollOrientation", "mSrcBitmap", "Landroid/graphics/Bitmap;", "changeScrollOrientation", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "scaleBitmap", "originBitmap", "scrollOrientationIsVertical", "setSrcBitmap", "srcBitmap", "startScroll", "stopScroll", "Companion", "app_debug"})
public final class LoopScrollView extends android.widget.FrameLayout {
    
    /**
     * 间隔时间内平移距离
     */
    private float mPanDistance = 0.0F;
    
    /**
     * 间隔时间内平移增距
     */
    private float mIntervalIncreaseDistance = 0.5F;
    
    /**
     * 填满当前view所需bitmap个数
     */
    private int mBitmapCount = 0;
    
    /**
     * 是否开始滚动
     */
    private boolean mIsScroll;
    
    /**
     * 滚动方向：上移/左移，默认上移
     */
    private int mScrollOrientation;
    
    /**
     * 遮罩层颜色
     */
    @androidx.annotation.ColorInt()
    private int mMaskLayerColor;
    private final android.graphics.drawable.Drawable mDrawable = null;
    private android.graphics.Bitmap mSrcBitmap;
    private final android.graphics.Paint mPaint = null;
    private final android.graphics.Matrix mMatrix = null;
    
    /**
     * 重绘
     */
    private final java.lang.Runnable mRedrawRunnable = null;
    
    /**
     * 重绘间隔时间
     */
    private static final long DEFAULT_DRAW_INTERVALS_TIME = 5L;
    @org.jetbrains.annotations.NotNull()
    public static final anda.travel.passenger.view.refreshview.LoopScrollView.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    /**
     * 开始滚动
     */
    public final void startScroll() {
    }
    
    /**
     * 停止滚动
     */
    public final void stopScroll() {
    }
    
    /**
     * 设置背景图 bitmap
     * 通过该方法设置的背景图，当 屏幕翻转/暗黑模式切换 等涉及到 activity 重构的情况出现时，需要在 activity 重构后重新设置背景图
     */
    public final void setSrcBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap srcBitmap) {
    }
    
    private final boolean scrollOrientationIsVertical() {
        return false;
    }
    
    public final void changeScrollOrientation() {
    }
    
    /**
     * 缩放Bitmap
     */
    private final android.graphics.Bitmap scaleBitmap(android.graphics.Bitmap originBitmap) {
        return null;
    }
    
    public LoopScrollView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    public LoopScrollView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public LoopScrollView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Landa/travel/passenger/view/refreshview/LoopScrollView$Companion;", "", "()V", "DEFAULT_DRAW_INTERVALS_TIME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}