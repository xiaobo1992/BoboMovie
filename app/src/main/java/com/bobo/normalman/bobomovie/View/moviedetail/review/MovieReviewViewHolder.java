package com.bobo.normalman.bobomovie.View.moviedetail.review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/20/17.
 */

public class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    TextView author, content;

    public MovieReviewViewHolder(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.movie_review_author);
        content = (TextView) itemView.findViewById(R.id.movie_review_content);
    }
}
