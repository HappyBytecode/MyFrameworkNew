// Generated code from Butter Knife. Do not modify!
package com.hxyc.myframework.module.actcen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hxyc.myframework.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PromoteValueFragment_ViewBinding implements Unbinder {
  private PromoteValueFragment target;

  private View view7f080117;

  @UiThread
  public PromoteValueFragment_ViewBinding(final PromoteValueFragment target, View source) {
    this.target = target;

    View view;
    target.mSmartRefreshLayout = Utils.findRequiredViewAsType(source, R.id.smartRefreshLayout, "field 'mSmartRefreshLayout'", SmartRefreshLayout.class);
    target.mRecyclerViewBasic = Utils.findRequiredViewAsType(source, R.id.recycler_view_basic, "field 'mRecyclerViewBasic'", RecyclerView.class);
    target.basicView = Utils.findRequiredView(source, R.id.cons_basic, "field 'basicView'");
    target.tvTest = Utils.findRequiredViewAsType(source, R.id.tv_test, "field 'tvTest'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_event_update, "field 'tvEventUpdate' and method 'onClick'");
    target.tvEventUpdate = Utils.castView(view, R.id.tv_event_update, "field 'tvEventUpdate'", TextView.class);
    view7f080117 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.imageView = Utils.findRequiredViewAsType(source, R.id.iv_pic, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PromoteValueFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSmartRefreshLayout = null;
    target.mRecyclerViewBasic = null;
    target.basicView = null;
    target.tvTest = null;
    target.tvEventUpdate = null;
    target.imageView = null;

    view7f080117.setOnClickListener(null);
    view7f080117 = null;
  }
}
