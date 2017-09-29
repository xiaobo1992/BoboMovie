package com.bobo.normalman.bobomovie.view.detail.overview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistOverviewViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView name;
    public ArtistOverviewViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.artist_image);
        name = itemView.findViewById(R.id.artist_name);
    }
}
