package com.bobo.normalman.bobomovie.View.moviedetail.video;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/20/17.
 */

class MovieVideoViewHolder extends RecyclerView.ViewHolder {
    final TextView title;
    final ImageView play;

    public MovieVideoViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.movie_video_title);
        play = itemView.findViewById(R.id.movie_video_play);
    }
}
