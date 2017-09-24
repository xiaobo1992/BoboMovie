package com.bobo.normalman.bobomovie.view.moviedetail.review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/20/17.
 */

class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    final TextView author;
    final TextView content;

    public MovieReviewViewHolder(View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.movie_review_author);
        content = itemView.findViewById(R.id.movie_review_content);
    }
}
