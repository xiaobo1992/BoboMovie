package com.bobo.normalman.bobomovie.View.moviedetail.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Review;

import java.util.List;

/**
 * Created by xiaobozhang on 9/20/17.
 */

class MovieReviewAdapter extends RecyclerView.Adapter {
    public final List<Review> reviews;
    public Boolean enableLoading;
    private final LoadMoreListener listener;
    private static final int VIEW_TYPE_REVIEW = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public MovieReviewAdapter(List<Review> reviews, boolean enableLoading, LoadMoreListener listener) {
        this.reviews = reviews;
        this.enableLoading = enableLoading;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie_review, parent,false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_LOADING:
                if (enableLoading) {
                    listener.loadMore();
                }
                break;
            case VIEW_TYPE_REVIEW:
                Review review = reviews.get(position);
                MovieReviewViewHolder viewHolder = (MovieReviewViewHolder) holder;
                viewHolder.author.setText(review.author);
                viewHolder.content.setText(review.content);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < reviews.size()) {
            return VIEW_TYPE_REVIEW;
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    @Override
    public int getItemCount() {
        return enableLoading ? reviews.size() + 1 : reviews.size();
    }

    public void setEnableLoading(boolean enableLoading) {
        this.enableLoading = enableLoading;
    }

    public void appendReviews(List<Review> reviews) {
        this.reviews.addAll(reviews);
        notifyDataSetChanged();
    }

    public int getDataCount() {
        return reviews.size();
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
