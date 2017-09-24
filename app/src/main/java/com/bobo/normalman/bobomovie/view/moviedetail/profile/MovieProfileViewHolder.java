package com.bobo.normalman.bobomovie.view.moviedetail.profile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/15/17.
 */

class MovieProfileViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageView;
    final ImageView like;
    final TextView title;
    final TextView release;
    final TextView rating;
    final TextView overview;

    public MovieProfileViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_detail_image);
        title = itemView.findViewById(R.id.movie_detail_title);
        release = itemView.findViewById(R.id.movie_detail_release_date);
        rating = itemView.findViewById(R.id.movie_detail_rating);
        overview = itemView.findViewById(R.id.movie_detail_overview);
        like = itemView.findViewById(R.id.movie_detail_like);
    }
}
