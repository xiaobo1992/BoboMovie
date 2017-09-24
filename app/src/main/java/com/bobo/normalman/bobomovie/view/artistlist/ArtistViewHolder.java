package com.bobo.normalman.bobomovie.view.artistlist;

import android.view.View;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.view.base.BaseViewHolder;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class ArtistViewHolder extends BaseViewHolder {
    final ImageView imageView;
    public ArtistViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_list_image);

    }
}
