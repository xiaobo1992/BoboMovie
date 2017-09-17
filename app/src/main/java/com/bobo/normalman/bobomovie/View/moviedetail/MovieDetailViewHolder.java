package com.bobo.normalman.bobomovie.View.moviedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieDetailViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title, release, rating, overview;

    public MovieDetailViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_detail_image);
        title = itemView.findViewById(R.id.movie_detail_title);
        release = itemView.findViewById(R.id.movie_detail_release_date);
        rating = itemView.findViewById(R.id.movie_detail_rating);
        overview = itemView.findViewById(R.id.movie_detail_overview);
    }
}
