package anda.travel.passenger.view.refreshview

/**
 *
 *   @desc
 *
 *   @author Chendroid
 *   @e-mail chendroidc@gmail.com
 *   @since  2020/11/20
 */
interface IFollowFooterInterface{
    fun setTouchLoadMoreText(text: String)

    fun setNoMoreData(text: String)

    fun setTouchLoadMoreListener(listener: OnLoadMoreListener)


    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}