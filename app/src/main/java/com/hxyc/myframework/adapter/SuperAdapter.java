package com.hxyc.myframework.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxyc.myframework.adapter.internal.BaseSuperAdapter;
import com.hxyc.myframework.adapter.internal.CRUD;
import com.hxyc.myframework.adapter.internal.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 注意 ViewType 不能是负数
 */
public abstract class SuperAdapter<D> extends BaseSuperAdapter<D> implements CRUD<D> {

    private static final String TAG = "SuperAdapter";
    private LayoutInflater mLayoutInflater;
    private List<View> mEmptyList = new ArrayList<>();

    public void setEmptyView(View view) {
        if (mEmptyList.contains(view)) return;
        mEmptyList.add(view);
    }

    public void removeEmptyView(View view) {
        if (mEmptyList.contains(view)) {
            mEmptyList.remove(view);
        }
    }

    private void refreshEmptyView(boolean isEmpty) {
        int vis = isEmpty ? View.VISIBLE : View.GONE;
        for (int i = 0; i < mEmptyList.size(); i++) {
            mEmptyList.get(i).setVisibility(vis);
        }
    }

    /**
     * 一种类型的 View
     *
     * @param context
     * @param list
     * @param layoutId
     */
    public SuperAdapter(Context context, List<D> list, int layoutId) {
        super(context, list, layoutId);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 多重类型的 View
     * <p>
     * 如果 mulItemViewType 为空时, 请重写 offerMultiItemViewType() 方法
     *
     * @param context
     * @param list
     * @param mulItemViewType
     */
    public SuperAdapter(Context context, List<D> list, IMulItemViewType<D> mulItemViewType) {
        super(context, list, mulItemViewType);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SuperViewHolder onCreate(ViewGroup parent, int viewType) {
        int resource = mMulItemViewType.getLayoutId(viewType);
        return SuperViewHolder.get(mLayoutInflater.inflate(resource, parent, false));
    }

    /*
     * ------------------------------------ CRUD ------------------------------------
     */

    @Override
    public void add(D item) {
        add(mList.size(), item);
    }

    @Override
    public void add(int location, D item) {
        mList.add(location, item);
        location += headerCount();
        notifyItemInserted(location);
    }

    @Override
    public void insert(int location, D item) {
        add(location, item);
    }

    @Override
    public void addAll(List<D> items) {
        int start = mList.size();
        mList.addAll(items);
        start += headerCount();
        notifyItemRangeInserted(start, items.size());
    }

    @Override
    public void addAll(int location, List<D> items) {
        if (items == null || items.isEmpty()) {
            Log.w(TAG, "addAll: The list you passed contains no elements.");
            return;
        }
        mList.addAll(location, items);
        location += headerCount();
        notifyItemRangeInserted(location, items.size());
    }

    @Override
    public void remove(D item) {
        if (contains(item)) {
            remove(mList.indexOf(item));
        }
    }

    @Override
    public void remove(int location) {
        mList.remove(location);
        location += headerCount();
        notifyItemRemoved(location);
    }

    @Override
    public void removeAll(List<D> items) {
        mList.removeAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void retainAll(List<D> items) {
        mList.retainAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void set(D oldItem, D newItem) {
        set(mList.indexOf(oldItem), newItem);
    }

    @Override
    public void set(int location, D item) {
        mList.set(location, item);
        location += headerCount();
        notifyItemChanged(location);
    }

    @Override
    public void setAll(List<D> items) {
        mList.clear();
        mList.addAll(items);
        notifyDataSetChanged();
        refreshEmptyView(mList == null || mList.isEmpty());
    }

    @Override
    public void replaceAll(List<D> items) {
        if (items == null || items.isEmpty()) {
            Log.w(TAG, "replaceAll: The list you passed contains no elements.");
            return;
        }
        if (mList.isEmpty()) {
            addAll(items);
        } else {
            mList.clear();
            mList.addAll(items);
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean contains(D item) {
        return mList.contains(item);
    }

    @Override
    public boolean containsAll(List<D> items) {
        return mList.containsAll(items);
    }

    @Override
    public void clear() {
        if (!mList.isEmpty()) {
            mList.clear();
            notifyDataSetChanged();
        }
    }
}
