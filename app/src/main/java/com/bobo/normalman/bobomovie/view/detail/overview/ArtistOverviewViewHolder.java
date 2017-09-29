package com.bobo.normalman.bobomovie.view.detail.overview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.normalman.bobomovie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistOverviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.artist_image)
    ImageView imageView;
    @BindView(R.id.artist_name)
    TextView name;
    @BindView(R.id.artist_birthday)
    TextView birthday;
    @BindView(R.id.artist_bio)
    TextView bio;
    @BindView(R.id.artist_place_of_birth)
    TextView place;

    public ArtistOverviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
