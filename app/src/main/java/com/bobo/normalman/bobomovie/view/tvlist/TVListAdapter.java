package com.bobo.normalman.bobomovie.view.tvlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.detail.ProfileActivity;
import com.bobo.normalman.bobomovie.view.detail.TVProfileActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class TVListAdapter extends BaseListAdapter<TV> {
    public TVListAdapter(List<TV> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        super(data, enableLoading, loadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_media, parent, false);
        return new TVListViewHolder(view);
    }

    @Override
    public void setListData(final RecyclerView.ViewHolder holder, final TV data) {
        final TVListViewHolder viewHolder = (TVListViewHolder) holder;
        ImageUtil.loadImage(viewHolder.itemView.getContext(), viewHolder.imageView, data.poster_path);
        viewHolder.name.setText(data.original_name);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewHolder.itemView.getContext();
                Intent intent = new Intent(context, TVProfileActivity.class);
                String tv = ModelUtil.toString(data, new TypeToken<TV>() {
                });
                intent.putExtra(ProfileActivity.KEY_DATA, tv);
                context.startActivity(intent);
            }
        });
    }
}
