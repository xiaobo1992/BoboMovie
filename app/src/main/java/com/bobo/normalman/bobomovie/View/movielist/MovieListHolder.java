package com.bobo.normalman.bobomovie.View.movielist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class MovieListHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public MovieListHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_list_image);
    }
}
