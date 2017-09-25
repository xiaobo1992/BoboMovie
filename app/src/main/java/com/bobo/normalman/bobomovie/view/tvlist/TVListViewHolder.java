package com.bobo.normalman.bobomovie.view.tvlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.view.base.BaseViewHolder;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class TVListViewHolder extends BaseViewHolder {
    final ImageView imageView;
    final TextView name;
    public TVListViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_list_image);
        name = itemView.findViewById(R.id.movie_list_name);
    }
}
