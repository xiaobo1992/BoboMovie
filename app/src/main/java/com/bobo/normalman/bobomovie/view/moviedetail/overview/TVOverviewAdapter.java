package com.bobo.normalman.bobomovie.view.moviedetail.overview;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewAdapter;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class TVOverviewAdapter extends BaseOverviewAdapter<TV> {

    public TVOverviewAdapter(TV data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_detail, parent, false);
        return new TVOverviewViewHolder(view);
    }

    @Override
    public void setProfile(TV data, RecyclerView.ViewHolder holder) {
        TVOverviewViewHolder viewHolder = (TVOverviewViewHolder) holder;
        viewHolder.overview.setText(data.overview);
        viewHolder.rating.setText(String.valueOf(data.vote_average));
        viewHolder.title.setText(data.original_name);
        viewHolder.release.setText(data.first_air_date);
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.poster_path);
        data.liked = false;
        Drawable drawable = data.liked ?
                ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_pink_24px) :
                ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_favorite_black_24px);
        viewHolder.like.setImageDrawable(drawable);
        /*viewHolder.like.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }
}
