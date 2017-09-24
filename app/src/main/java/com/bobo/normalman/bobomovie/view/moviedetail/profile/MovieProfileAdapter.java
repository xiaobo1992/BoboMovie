/*
I like to share this code, which can present the example of infinte loading on Android.
It bascialy combine both mix-typed adapter and Asynctask.
*/
package com.bobo.normalman.bobomovie.view.moviedetail.profile;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.LikeMovieUtil;
import com.bobo.normalman.bobomovie.model.Movie;

/**
 * Created by xiaobozhang on 9/15/17.
 */

class MovieProfileAdapter extends RecyclerView.Adapter {
    private final Movie movie;

    private static final int KEY_VIEWTYPE_DETAIL = 0;
    private static final int KEY_VIEWTYPE_REVIEW = 1;

    public MovieProfileAdapter(Movie movie) {
        this.movie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case KEY_VIEWTYPE_DETAIL:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_movie_detail, parent, false);
                return new MovieProfileViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case KEY_VIEWTYPE_DETAIL:
                final MovieProfileViewHolder viewHolder = (MovieProfileViewHolder) holder;
                viewHolder.overview.setText(movie.overview);
                viewHolder.rating.setText(String.valueOf(movie.vote_average));
                viewHolder.title.setText(movie.title);
                viewHolder.release.setText(movie.release_date);
                ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, movie.poster_path);
                movie.liked = LikeMovieUtil.isLike(viewHolder.itemView.getContext(),
                        movie);
                Drawable drawable = movie.liked ?
                        ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_pink_24px) :
                        ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_black_24px);
                viewHolder.like.setImageDrawable(drawable);
                viewHolder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        if (!movie.liked) {
                            LikeMovieUtil.LikeMovie(viewHolder.itemView.getContext(),
                                    movie);
                            notifyDataSetChanged();
                        } else {
                            LikeMovieUtil.disLikeMovie(viewHolder.itemView.getContext(),
                                    movie);
                            notifyDataSetChanged();
                        }
                    }
                });
            default:
                break;
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
