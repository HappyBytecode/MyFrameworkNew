package anda.travel.passenger.view.refreshview

/**
 *
 *   @desc   用于规范下拉刷新和上拉加载的
 *
 *   @author Chendroid
 *   @e-mail chendroidc@gmail.com
 *   @since  2020/11/19
 */
interface IHXRefreshView {

    /**
     * 下拉刷新接口
     */
    fun onRefresh()

    /**
     * 刷新结束
     * 如果needShowFollowFooter为false，那么followText则可以忽略。
     */
    fun onRefreshFinished(success: Boolean, noMoreData: Boolean = true, followText: String = "", isEmpty: Boolean = false, needShowFollowFooter: Boolean = false)

    /**
     * 上拉加载更多
     */
    fun onLoadMore()

    /**
     * 加载结束
     * @param success 是否成功
     */
    fun onLoadFinished(success: Boolean, noMoreData: Boolean = true, followText: String = "", isEmpty: Boolean = false, needShowFollowFooter: Boolean = false)


}