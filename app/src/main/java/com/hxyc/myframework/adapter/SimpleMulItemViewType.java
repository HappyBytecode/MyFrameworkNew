package com.hxyc.myframework.adapter;

public abstract class SimpleMulItemViewType<D> implements IMulItemViewType<D> {

    @Override
    public int getItemViewType(int position, D data) {
        return 0;
    }

}
