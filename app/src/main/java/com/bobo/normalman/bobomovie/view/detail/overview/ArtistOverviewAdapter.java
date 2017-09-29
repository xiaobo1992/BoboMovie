package com.bobo.normalman.bobomovie.view.detail.overview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewAdapter;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistOverviewAdapter extends BaseOverviewAdapter<Artist> {
    public ArtistOverviewAdapter(Artist data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artist_detail, parent, false);
        return new ArtistOverviewViewHolder(view);
    }

    @Override
    public void setProfile(Artist data, RecyclerView.ViewHolder holder) {
        ArtistOverviewViewHolder viewHolder = (ArtistOverviewViewHolder) holder;
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.profile_path);
        viewHolder.name.setText(data.name);
        viewHolder.birthday.setText(data.birthday);
        viewHolder.bio.setText(data.biography);
        viewHolder.place.setText(data.place_of_birth);
    }

    public void setData(Artist artist) {
        this.data = artist;
        notifyDataSetChanged();
    }
}
