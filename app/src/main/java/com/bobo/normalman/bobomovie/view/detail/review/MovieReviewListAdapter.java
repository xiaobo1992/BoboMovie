package com.bobo.normalman.bobomovie.view.detail.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Review;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class MovieReviewListAdapter extends BaseListAdapter<Review> {
    public MovieReviewListAdapter(List<Review> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        super(data, enableLoading, loadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_media_review, parent,false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void setListData(RecyclerView.ViewHolder holder, Review data) {
        MovieReviewViewHolder viewHolder = (MovieReviewViewHolder) holder;
        viewHolder.author.setText(data.author);
        viewHolder.content.setText(data.content);
    }
}
