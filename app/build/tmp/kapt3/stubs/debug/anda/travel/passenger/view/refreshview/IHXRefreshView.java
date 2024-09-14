package anda.travel.passenger.view.refreshview;

import java.lang.System;

/**
 * @desc   用于规范下拉刷新和上拉加载的
 *
 * @author Chendroid
 * @e-mail chendroidc@gmail.com
 * @since  2020/11/19
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J8\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005H&\u00a8\u0006\u000e"}, d2 = {"Landa/travel/passenger/view/refreshview/IHXRefreshView;", "", "onLoadFinished", "", "success", "", "noMoreData", "followText", "", "isEmpty", "needShowFollowFooter", "onLoadMore", "onRefresh", "onRefreshFinished", "app_debug"})
public abstract interface IHXRefreshView {
    
    /**
     * 下拉刷新接口
     */
    public abstract void onRefresh();
    
    /**
     * 刷新结束
     * 如果needShowFollowFooter为false，那么followText则可以忽略。
     */
    public abstract void onRefreshFinished(boolean success, boolean noMoreData, @org.jetbrains.annotations.NotNull()
    java.lang.String followText, boolean isEmpty, boolean needShowFollowFooter);
    
    /**
     * 上拉加载更多
     */
    public abstract void onLoadMore();
    
    /**
     * 加载结束
     * @param success 是否成功
     */
    public abstract void onLoadFinished(boolean success, boolean noMoreData, @org.jetbrains.annotations.NotNull()
    java.lang.String followText, boolean isEmpty, boolean needShowFollowFooter);
    
    /**
     * @desc   用于规范下拉刷新和上拉加载的
     *
     * @author Chendroid
     * @e-mail chendroidc@gmail.com
     * @since  2020/11/19
     */
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}