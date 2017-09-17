/*
I like to share this code, which can present the example of infinte loading on Android. 
It bascialy combine both mix-typed adapter and Asynctask. 
*/

package com.bobo.normalman.bobomovie.View.movie_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.Model.Movie;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.Util.ImageUtil;

/**
 * Created by xiaobozhang on 9/15/17.
 */

class MovieDetailAdapter extends RecyclerView.Adapter {
    private Movie movie;

    private static final int KEY_VIEWTYPE_DETAIL = 0;
    private static final int KEY_VIEWTYPE_REVIEW = 1;

    public MovieDetailAdapter(Movie movie) {
        this.movie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case KEY_VIEWTYPE_DETAIL:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_movie_detail, parent, false);
                return new MovieDetailViewHolder(view);
            case KEY_VIEWTYPE_REVIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_movie_review, parent, false);
                return new MovieDetailReviewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case KEY_VIEWTYPE_DETAIL:
                MovieDetailViewHolder viewHolder = (MovieDetailViewHolder) holder;
                viewHolder.overview.setText(movie.overview);
                viewHolder.rating.setText(String.valueOf(movie.vote_average));
                viewHolder.title.setText(movie.title);
                viewHolder.release.setText(movie.release_date);
                ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, movie.poster_path);
            default:
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return KEY_VIEWTYPE_DETAIL;
        } else if (position == 1) {
            return KEY_VIEWTYPE_REVIEW;
        }
        return -1;
    }
}
