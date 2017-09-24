package com.bobo.normalman.bobomovie.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter {
    public List<T> data;
    public boolean enableLoading;
    LoadMoreListener loadMoreListener;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_LIST = 1;

    public BaseListAdapter(List<T> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        this.data = data;
        this.enableLoading = enableLoading;
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_LOADING:
                if (enableLoading) {
                    loadMoreListener.loadMore();
                }
                break;
            case VIEW_TYPE_LIST:
                T d = data.get(position);
                setListData(holder, d);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position < data.size()) {
            return VIEW_TYPE_LIST;
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    @Override
    public int getItemCount() {
        return enableLoading ? data.size() + 1 : data.size();
    }

    public int getDataCount() {
        return data.size();
    }

    public void setEnableLoading(boolean enableLoading) {
        this.enableLoading = enableLoading;
    }

    public void appendAllData(List<T> d) {
        data.addAll(d);
        notifyDataSetChanged();
    }

    public void setData(List<T> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    public interface LoadMoreListener {
        void loadMore();
    }

    public abstract void setListData(RecyclerView.ViewHolder holder, T data);
}
