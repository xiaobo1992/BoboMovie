/*
I like to share this code, which can present the example of infinte loading on Android.
It bascialy combine both mix-typed adapter and Asynctask.
*/
package com.bobo.normalman.bobomovie.view.moviedetail.overview;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.LikeMovieUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewAdapter;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieOverviewAdapter extends BaseOverviewAdapter<Movie> {

    public MovieOverviewAdapter(Movie data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_detail, parent, false);
        return new MovieOverviewViewHolder(view);
    }

    @Override
    public void setProfile(final Movie data, RecyclerView.ViewHolder holder) {
        final MovieOverviewViewHolder viewHolder = (MovieOverviewViewHolder) holder;
        viewHolder.overview.setText(data.overview);
        viewHolder.rating.setText(String.valueOf(data.vote_average));
        viewHolder.title.setText(data.title);
        viewHolder.release.setText(data.release_date);
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.poster_path);
        data.liked = LikeMovieUtil.isLike(viewHolder.itemView.getContext(),
                data);
        Drawable drawable = data.liked ?
                ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_pink_24px) :
                ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_black_24px);
        viewHolder.like.setImageDrawable(drawable);
        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!data.liked) {
                    LikeMovieUtil.LikeMovie(viewHolder.itemView.getContext(),
                            data);
                    notifyDataSetChanged();
                } else {
                    LikeMovieUtil.disLikeMovie(viewHolder.itemView.getContext(),
                            data);
                    notifyDataSetChanged();
                }
            }
        });
    }
}
