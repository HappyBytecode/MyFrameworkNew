package anda.travel.passenger.view.refreshview;

import java.lang.System;

/**
 * @desc
 *
 * @author Chendroid
 * @e-mail chendroidc@gmail.com
 * @since  2020/11/20
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u000b"}, d2 = {"Landa/travel/passenger/view/refreshview/IFollowFooterInterface;", "", "setNoMoreData", "", "text", "", "setTouchLoadMoreListener", "listener", "Landa/travel/passenger/view/refreshview/IFollowFooterInterface$OnLoadMoreListener;", "setTouchLoadMoreText", "OnLoadMoreListener", "app_debug"})
public abstract interface IFollowFooterInterface {
    
    public abstract void setTouchLoadMoreText(@org.jetbrains.annotations.NotNull()
    java.lang.String text);
    
    public abstract void setNoMoreData(@org.jetbrains.annotations.NotNull()
    java.lang.String text);
    
    public abstract void setTouchLoadMoreListener(@org.jetbrains.annotations.NotNull()
    anda.travel.passenger.view.refreshview.IFollowFooterInterface.OnLoadMoreListener listener);
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Landa/travel/passenger/view/refreshview/IFollowFooterInterface$OnLoadMoreListener;", "", "onLoadMore", "", "app_debug"})
    public static abstract interface OnLoadMoreListener {
        
        public abstract void onLoadMore();
    }
}