package com.bobo.normalman.bobomovie.view.artistlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.moviedetail.ArtistProfileActivity;
import com.bobo.normalman.bobomovie.view.moviedetail.ProfileActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class ArtistListAdapter extends BaseListAdapter<Artist> {
    public ArtistListAdapter(List<Artist> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        super(data, enableLoading, loadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_media, parent, false);
        return new ArtistListViewHolder(view);
    }

    @Override
    public void setListData(final RecyclerView.ViewHolder holder, final Artist data) {
        ArtistListViewHolder viewHolder = (ArtistListViewHolder) holder;
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.profile_path);
        viewHolder.name.setText(data.name);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ArtistProfileActivity.class);
                String artist = ModelUtil.toString(data, new TypeToken<Artist>(){});
                intent.putExtra(ProfileActivity.KEY_DATA, artist);
                context.startActivity(intent);
            }
        });
    }
}
