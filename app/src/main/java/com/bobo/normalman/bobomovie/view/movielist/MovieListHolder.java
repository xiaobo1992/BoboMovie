package com.bobo.normalman.bobomovie.view.movielist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/14/17.
 */

class MovieListHolder extends RecyclerView.ViewHolder {
    final ImageView imageView;
    final TextView name;

    public MovieListHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_list_image);
        name = itemView.findViewById(R.id.movie_list_name);
    }
}
