package com.bobo.normalman.bobomovie.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class BaseOverviewViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ImageView like;
    public TextView title;
    public TextView release;
    public TextView rating;
    public TextView overview;

    public BaseOverviewViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_detail_image);
        title = itemView.findViewById(R.id.movie_detail_title);
        release = itemView.findViewById(R.id.movie_detail_release_date);
        rating = itemView.findViewById(R.id.movie_detail_rating);
        overview = itemView.findViewById(R.id.movie_detail_overview);
        like = itemView.findViewById(R.id.movie_detail_like);
    }
}
