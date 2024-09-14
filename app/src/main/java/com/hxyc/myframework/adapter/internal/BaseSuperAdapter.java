package com.hxyc.myframework.adapter.internal;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hxyc.myframework.adapter.IMulItemViewType;
import com.hxyc.myframework.adapter.OnClickListener;
import com.hxyc.myframework.adapter.OnLongClickListener;
import com.hxyc.myframework.adapter.OnTouchListener;
import com.hxyc.myframework.adapter.SimpleMulItemViewType;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSuperAdapter<D> extends RecyclerView.Adapter<SuperViewHolder> implements
        ILayoutManager, IHeaderFooter, IViewBindData<D, SuperViewHolder> {

    protected Context mContext;
    protected List<D> mList;

    protected IMulItemViewType<D> mMulItemViewType;
    private static final int DEFAULT_ITEM_VIEW_TYPE = 0;

    // View listener
    private SparseArray<SparseArray<OnClickListener<D>>> mAllClickListeners = new SparseArray<>();
    private SparseArray<SparseArray<OnLongClickListener<D>>> mAllLongClickListeners = new SparseArray<>();
    private SparseArray<SparseArray<OnTouchListener<D>>> mAllTouchListeners = new SparseArray<>();

    // Item listener
    private SparseArray<OnClickListener<D>> mItemClickListeners = new SparseArray<>();
    private SparseArray<OnLongClickListener<D>> mItemLongClickListeners = new SparseArray<>();
    private SparseArray<OnTouchListener<D>> mItemTouchListeners = new SparseArray<>();

    /**
     * headerViewType >= -0x100
     */
    private static final int TYPE_HEADER_START = -0x100;
    /**
     * footerViewType >= -0x200
     */
    private static final int TYPE_FOOTER_START = -0x200;

    protected List<View> mHeaders = new ArrayList<>();
    protected List<View> mFooters = new ArrayList<>();

    private RecyclerView mRecyclerView;

    public BaseSuperAdapter(Context context, List<D> list, int layoutId) {
        this.mContext = context;
        this.mList = list == null ? new ArrayList<>() : new ArrayList<>(list);
        this.mMulItemViewType = new SimpleMulItemViewType<D>() {
            @Override
            public int getLayoutId(int viewType) {
                return layoutId;
            }

            @Override
            public int getItemViewType(int position, D data) {
                return DEFAULT_ITEM_VIEW_TYPE;
            }
        };
    }

    /**
     * 如果 mulItemViewType 为空时, 请重写 offerMultiItemViewType() 方法
     *
     * @param context
     * @param list
     * @param mulItemViewType
     */
    public BaseSuperAdapter(Context context, List<D> list, IMulItemViewType<D> mulItemViewType) {
        this.mContext = context;
        this.mList = list == null ? new ArrayList<>() : new ArrayList<>(list);
        this.mMulItemViewType = mulItemViewType == null ? offerMultiItemViewType() : mulItemViewType;
    }

    protected IMulItemViewType<D> offerMultiItemViewType() {
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public List<D> getList() {
        return mList;
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewType, int viewId, OnClickListener<D> listener) {
        SparseArray<OnClickListener<D>> sa = mAllClickListeners.get(viewType);
        if (sa == null && listener != null) {
            sa = new SparseArray<>();
            mAllClickListeners.put(viewType, sa);
        } else if (sa == null && listener == null) {
            return;
        }
        if (listener == null) {
            sa.remove(viewId);
            if (sa.size() == 0) {
                mAllClickListeners.remove(viewType);
            }
        } else {
            sa.put(viewId, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, OnClickListener<D> listener) {
        setOnClickListener(DEFAULT_ITEM_VIEW_TYPE, viewId, listener);
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param viewId
     * @param listener
     */
    public void setOnLongClickListener(int viewType, int viewId, OnLongClickListener<D> listener) {
        SparseArray<OnLongClickListener<D>> sa = mAllLongClickListeners.get(viewType);
        if (sa == null && listener != null) {
            sa = new SparseArray<>();
            mAllLongClickListeners.put(viewType, sa);
        } else if (sa == null && listener == null) {
            return;
        }
        if (listener == null) {
            sa.remove(viewId);
            if (sa.size() == 0) {
                mAllLongClickListeners.remove(viewType);
            }
        } else {
            sa.put(viewId, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewId
     * @param listener
     */
    public void setOnLongClickListener(int viewId, OnLongClickListener<D> listener) {
        setOnLongClickListener(DEFAULT_ITEM_VIEW_TYPE, viewId, listener);
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param viewId
     * @param listener
     */
    public void setTouchListener(int viewType, int viewId, OnTouchListener<D> listener) {
        SparseArray<OnTouchListener<D>> sa = mAllTouchListeners.get(viewType);
        if (sa == null && listener != null) {
            sa = new SparseArray<>();
            mAllTouchListeners.put(viewType, sa);
        } else if (sa == null && listener == null) {
            return;
        }
        if (listener == null) {
            sa.remove(viewId);
            if (sa.size() == 0) {
                mAllTouchListeners.remove(viewType);
            }
        } else {
            sa.put(viewId, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewId
     * @param listener
     */
    public void setTouchListener(int viewId, OnTouchListener<D> listener) {
        setTouchListener(DEFAULT_ITEM_VIEW_TYPE, viewId, listener);
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param listener
     */
    public void setOnItemClickListener(int viewType, OnClickListener<D> listener) {
        if (listener == null) {
            mItemClickListeners.remove(viewType);
        } else {
            mItemClickListeners.put(viewType, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnClickListener<D> listener) {
        setOnItemClickListener(DEFAULT_ITEM_VIEW_TYPE, listener);
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param listener
     */
    public void setOnItemLongClickListener(int viewType, OnLongClickListener<D> listener) {
        if (listener == null) {
            mItemLongClickListeners.remove(viewType);
        } else {
            mItemLongClickListeners.put(viewType, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param listener
     */
    public void setOnItemLongClickListener(OnLongClickListener<D> listener) {
        setOnItemLongClickListener(DEFAULT_ITEM_VIEW_TYPE, listener);
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param viewType
     * @param listener
     */
    public void setOnItemTouchListener(int viewType, OnTouchListener<D> listener) {
        if (listener == null) {
            mItemTouchListeners.remove(viewType);
        } else {
            mItemTouchListeners.put(viewType, listener);
        }
    }

    /**
     * listener 为 null 时, 取消监听
     *
     * @param listener
     */
    public void setOnItemTouchListener(OnTouchListener<D> listener) {
        setOnItemTouchListener(DEFAULT_ITEM_VIEW_TYPE, listener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (isHeaderView(position)) {
            viewType = TYPE_HEADER_START + (position - 0);
        } else if (isFooterView(position)) {
            viewType = TYPE_FOOTER_START + (position - headerCount() - dataCount());
        } else {
            if (mMulItemViewType != null) {
                int index = position - headerCount();
                return mMulItemViewType.getItemViewType(index, mList.get(index));
            }
            return 0;
        }
        return viewType;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SuperViewHolder holder;
        try {
            if (viewType < 0) {
                // headerView holder & footerView holder
                if (viewType >= TYPE_HEADER_START) {
                    holder = new SuperViewHolder(mHeaders.get(viewType - TYPE_HEADER_START));
                } else {
                    holder = new SuperViewHolder(mFooters.get(viewType - TYPE_FOOTER_START));
                }
            } else {
                holder = onCreate(parent, viewType);
            }
        } catch (Exception e) {
            return new SuperViewHolder(new View(parent.getContext()));
        }
        if (holder.itemView.getParent() != null) {
            return new SuperViewHolder(new View(parent.getContext()));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType >= 0) {
            int index = position - headerCount();
            D item = mList.get(index);
            onBind(holder, viewType, index, item);

            setOnClickListener(holder, viewType, item);
            setOnLongClickListener(holder, viewType, item);
            setOnTouchListener(holder, viewType, item);

            setOnItemClickListener(holder, viewType, item);
            setOnItemLongClickListener(holder, viewType, item);
            setOnItemTouchListener(holder, viewType, item);
        }
    }

    private void setOnClickListener(SuperViewHolder holder, int viewType, D item) {
        SparseArray<OnClickListener<D>> lickListeners = mAllClickListeners.get(viewType);
        if (lickListeners != null) {
            int size = lickListeners.size();
            for (int i = 0; i < size; i++) {
                OnClickListener<D> listener = lickListeners.valueAt(i);
                int viewId = lickListeners.keyAt(i);
                holder.setOnClickListener(viewId, v -> listener.onClick(getDataPosition(holder), v, item));
            }
        }
    }

    private void setOnLongClickListener(SuperViewHolder holder, int viewType, D item) {
        SparseArray<OnLongClickListener<D>> lickListeners = mAllLongClickListeners.get(viewType);
        if (lickListeners != null) {
            int size = lickListeners.size();
            for (int i = 0; i < size; i++) {
                OnLongClickListener<D> listener = lickListeners.valueAt(i);
                int viewId = lickListeners.keyAt(i);
                holder.setOnLongClickListener(viewId, v -> listener.onLongClick(getDataPosition(holder), v, item));
            }
        }
    }

    private void setOnTouchListener(SuperViewHolder holder, int viewType, D item) {
        SparseArray<OnTouchListener<D>> lickListeners = mAllTouchListeners.get(viewType);
        if (lickListeners != null) {
            int size = lickListeners.size();
            for (int i = 0; i < size; i++) {
                OnTouchListener<D> listener = lickListeners.valueAt(i);
                int viewId = lickListeners.keyAt(i);
                holder.setOnTouchListener(viewId, (v, event) -> listener.onTouch(getDataPosition(holder), v, item,
                        event));
            }
        }
    }

    private void setOnItemClickListener(SuperViewHolder holder, int viewType, D item) {
        OnClickListener<D> listener = mItemClickListeners.get(viewType);
        if (listener != null) {
            holder.itemView.setOnClickListener(
                    (v) -> listener.onClick(getDataPosition(holder), holder.itemView, item)
            );
        }
    }

    private void setOnItemLongClickListener(SuperViewHolder holder, int viewType, D item) {
        OnLongClickListener<D> listener = mItemLongClickListeners.get(viewType);
        if (listener != null) {
            holder.itemView.setOnLongClickListener(
                    (v) -> listener.onLongClick(getDataPosition(holder), holder.itemView, item)
            );
        }
    }

    private void setOnItemTouchListener(SuperViewHolder holder, int viewType, D item) {
        OnTouchListener<D> listener = mItemTouchListeners.get(viewType);
        if (listener != null) {
            holder.itemView.setOnTouchListener(
                    (v, event) -> listener.onTouch(getDataPosition(holder), holder.itemView, item, event)
            );
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    @Override
    public void onViewAttachedToWindow(SuperViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            // add header or footer to StaggeredGridLayoutManager
            if (isHeaderView(holder.getLayoutPosition()) || isFooterView(holder.getLayoutPosition())) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    @Override
    public boolean hasLayoutManager() {
        return mRecyclerView != null && mRecyclerView.getLayoutManager() != null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return hasLayoutManager() ? mRecyclerView.getLayoutManager() : null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    /**
     * @return header.size() + data.size() + footer.size()
     */
    @Override
    public int getItemCount() {
        int size = dataCount() + headerCount() + footerCount();
        return size;
    }

    @Override
    public void addHeaderView(View header) {
        mHeaders.add(header);
        ifGlidLayoutManager();
        notifyItemInserted(headerCount() - 1);
    }

    @Override
    public void addFooterView(View footer) {
        mFooters.add(footer);
        ifGlidLayoutManager();
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public boolean removeHeaderView(View view) {
        if (mHeaders != null) {
            int length = mHeaders.size();
            int position = -1;
            for (int i = 0; i < length; i++) {
                if (mHeaders.get(i) == view) {
                    position = i;
                }
            }
            if (position != -1) {
                notifyItemRemoved(position);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeFooterView(View view) {
        if (mFooters != null) {
            int position = -1;
            int length = mFooters.size();
            for (int i = 0; i < length; i++) {
                if (mFooters.get(i) == view) {
                    position = i + headerCount() + dataCount();
                    break;
                }
            }
            if (position != -1) {
                mFooters.remove(view);
                notifyItemRemoved(position);
                return true;
            }
        }
        return false;
    }

    /**
     * 通过 ViewHolder 查找 当前的位置 - headerCount
     *
     * @param holder
     * @return
     */
    private int getDataPosition(SuperViewHolder holder) {
        return holder.getAdapterPosition() - headerCount();
    }

    @Override
    public int headerCount() {
        return mHeaders == null ? 0 : mHeaders.size();
    }

    @Override
    public int footerCount() {
        return mFooters == null ? 0 : mFooters.size();
    }

    @Override
    public boolean isHeaderView(int position) {
        return mHeaders != null && position < mHeaders.size();
    }

    @Override
    public boolean isFooterView(int position) {
        return mFooters != null && position >= getItemCount() - mFooters.size();
    }

    private int dataCount() {
        return mList == null ? 0 : mList.size();
    }

    protected void ifGlidLayoutManager() {
        final RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(
                    new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return (isHeaderView(position) || isFooterView(position)) ?
                                    ((GridLayoutManager) layoutManager).getSpanCount() :
                                    originalSpanSizeLookup.getSpanSize(position);
                        }
                    });
        }
    }
}
