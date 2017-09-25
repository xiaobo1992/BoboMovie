package com.bobo.normalman.bobomovie.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public abstract class BaseOverviewAdapter<T> extends RecyclerView.Adapter {
    T data;

    public BaseOverviewAdapter(T data) {
        this.data = data;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setProfile(data, holder);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public abstract void setProfile(T data, RecyclerView.ViewHolder holder);
}
