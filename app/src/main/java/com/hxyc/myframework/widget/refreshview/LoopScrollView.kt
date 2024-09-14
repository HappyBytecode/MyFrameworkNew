package anda.travel.passenger.view.refreshview


import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.hxyc.myframework.R
import com.hxyc.myframework.util.DisplayUtil.dp2px
import com.hxyc.myframework.util.Logger


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
class LoopScrollView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    /**
     * 间隔时间内平移距离
     */
    private var mPanDistance = 0f

    /**
     * 间隔时间内平移增距
     */
    private var mIntervalIncreaseDistance = 0.5f

    /**
     * 填满当前view所需bitmap个数
     */
    private var mBitmapCount = 0

    /**
     * 是否开始滚动
     */
    private var mIsScroll: Boolean

    /**
     * 滚动方向：上移/左移，默认上移
     */
    private var mScrollOrientation: Int

    /**
     * 遮罩层颜色
     */
    @ColorInt
    private var mMaskLayerColor: Int
    private val mDrawable: Drawable?
    private var mSrcBitmap: Bitmap? = null
    private val mPaint: Paint
    private val mMatrix: Matrix
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mDrawable == null || mDrawable !is BitmapDrawable) {
            return
        }
        if (visibility == View.GONE) {
            return
        }
        if (w == 0 || h == 0) {
            return
        }
        if (mSrcBitmap == null) {
            val bitmap = mDrawable.bitmap
            //调整色彩模式进行质量压缩
            val compressBitmap = bitmap.copy(Bitmap.Config.RGB_565, true)
            //缩放 Bitmap
            mSrcBitmap = scaleBitmap(compressBitmap)
            //计算至少需要几个 bitmap 才能填满当前 view
            mBitmapCount = if (scrollOrientationIsVertical()) measuredHeight / mSrcBitmap!!.height + 1 else measuredWidth / mSrcBitmap!!.width + 1
            if (!compressBitmap.isRecycled) {
                compressBitmap.isRecycled
                System.gc()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        //绘制遮罩层
        if (mMaskLayerColor != Color.TRANSPARENT) {
            canvas.drawColor(mMaskLayerColor)
        }
        super.onDraw(canvas)
        if (mSrcBitmap == null) {
            return
        }
        val length = if (scrollOrientationIsVertical()) mSrcBitmap!!.height else mSrcBitmap!!.width
        if (length + mPanDistance != 0f) {
            //第一张图片未完全滚出屏幕
            mMatrix.reset()
            if (scrollOrientationIsVertical()) {
                mMatrix.postTranslate(0f, mPanDistance)
            } else {
                mMatrix.postTranslate(mPanDistance, 0f)
            }
            canvas.drawBitmap(mSrcBitmap, mMatrix, mPaint)
        }
        if (length + mPanDistance < (if (scrollOrientationIsVertical()) measuredHeight else measuredWidth)) {
            //用于补充留白的图片出现在屏幕
            for (i in 0 until mBitmapCount) {
                mMatrix.reset()
                if (scrollOrientationIsVertical()) {
                    mMatrix.postTranslate(0f, (i + 1) * mSrcBitmap!!.height + mPanDistance)
                } else {
                    mMatrix.postTranslate((i + 1) * mSrcBitmap!!.width + mPanDistance, 0f)
                }
                canvas.drawBitmap(mSrcBitmap, mMatrix, mPaint)
            }
        }

        //延时重绘实现滚动效果
        if (mIsScroll && handler != null) {
            handler.postDelayed(mRedrawRunnable, DEFAULT_DRAW_INTERVALS_TIME)
        }
        Logger.i("LoopScrollView", "onDraw()")

    }

    /**
     * 重绘
     */
    private val mRedrawRunnable = Runnable {
        val length = if (scrollOrientationIsVertical()) mSrcBitmap!!.height else mSrcBitmap!!.width
        if (length + mPanDistance <= 0) {
            //第一张已完全滚出屏幕，重置平移距离
            mPanDistance = 0f
        }
        mPanDistance = mPanDistance - mIntervalIncreaseDistance
        invalidate()
    }

    /**
     * 开始滚动
     */
    fun startScroll() {
        if (mIsScroll) {
            return
        }
        mIsScroll = true
        if (handler != null) {
            handler.postDelayed(mRedrawRunnable, DEFAULT_DRAW_INTERVALS_TIME)
        }
    }

    /**
     * 停止滚动
     */
    fun stopScroll() {
        if (!mIsScroll) {
            return
        }
        mIsScroll = false
        if (handler != null) {
            handler.removeCallbacks(mRedrawRunnable)
        }
    }

    /**
     * 设置背景图 bitmap
     * 通过该方法设置的背景图，当 屏幕翻转/暗黑模式切换 等涉及到 activity 重构的情况出现时，需要在 activity 重构后重新设置背景图
     */
    fun setSrcBitmap(srcBitmap: Bitmap) {
        val oldScrollStatus = mIsScroll
        if (oldScrollStatus) {
            stopScroll()
        }
        val compressBitmap: Bitmap
//        if (srcBitmap.config != Bitmap.Config.RGB_565) {
//            if (srcBitmap.isMutable) {
//                srcBitmap.config = Bitmap.Config.RGB_565
//                compressBitmap = srcBitmap
//            } else {
//                compressBitmap = srcBitmap.copy(Bitmap.Config.RGB_565, true)
//            }
//        } else {
            compressBitmap = srcBitmap
//        }
        //按当前View宽度比例缩放 Bitmap
        mSrcBitmap = scaleBitmap(compressBitmap)
        //计算至少需要几个 bitmap 才能填满当前 view
        mBitmapCount = if (scrollOrientationIsVertical()) measuredHeight / mSrcBitmap!!.height + 1 else measuredWidth / mSrcBitmap!!.width + 1
        if (!srcBitmap.isRecycled) {
            srcBitmap.isRecycled
            System.gc()
        }
        if (!compressBitmap.isRecycled) {
            compressBitmap.isRecycled
            System.gc()
        }
        if (oldScrollStatus) {
            startScroll()
        }
        mMaskLayerColor = Color.parseColor("#40A6A6B9")
    }

    private fun scrollOrientationIsVertical(): Boolean {
        return mScrollOrientation == 0
    }

    fun changeScrollOrientation() {
        mPanDistance = 0f
        mScrollOrientation = if (scrollOrientationIsVertical()) 1 else 0
        if (mSrcBitmap != null) {
            if (mDrawable != null && mDrawable is BitmapDrawable) {
                val bitmap = mDrawable.bitmap
                if (!bitmap.isRecycled) {
                    setSrcBitmap(bitmap)
                    return
                }
            }
            setSrcBitmap(mSrcBitmap!!)
        }
    }

    /**
     * 缩放Bitmap
     */
    private fun scaleBitmap(originBitmap: Bitmap): Bitmap {
        val width = originBitmap.width
        val height = originBitmap.height
        val newHeight: Int
        val newWidth: Int
        if (scrollOrientationIsVertical()) {
            newWidth = measuredWidth
            newHeight = newWidth * height / width
        } else {
            newHeight = dp2px(context,34f)
            newWidth = newHeight * width / height
        }
        return Bitmap.createScaledBitmap(originBitmap, newWidth, newHeight, true)
    }

    companion object {
        /**
         * 重绘间隔时间
         */
        private const val DEFAULT_DRAW_INTERVALS_TIME = 5L
    }

    init {
        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.LoopScrollView, defStyleAttr, 0)
        val speed = array.getInteger(R.styleable.LoopScrollView_speed, 3)
        mScrollOrientation = array.getInteger(R.styleable.LoopScrollView_scrollOrientation, 0)
        mIntervalIncreaseDistance = speed * mIntervalIncreaseDistance
        mDrawable = array.getDrawable(R.styleable.LoopScrollView_src)
        mIsScroll = array.getBoolean(R.styleable.LoopScrollView_isScroll, true)
        mMaskLayerColor = array.getColor(R.styleable.LoopScrollView_maskLayerColor, Color.TRANSPARENT)
        array.recycle()
        setWillNotDraw(false)
        mPaint = Paint()
        mMatrix = Matrix()
    }
}