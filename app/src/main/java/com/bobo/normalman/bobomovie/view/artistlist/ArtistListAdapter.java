package com.bobo.normalman.bobomovie.view.artistlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;

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
        return new ArtistViewHolder(view);
    }

    @Override
    public void setListData(RecyclerView.ViewHolder holder, Artist data) {
        ArtistViewHolder viewHolder = (ArtistViewHolder) holder;
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.profile_path);
    }
}
