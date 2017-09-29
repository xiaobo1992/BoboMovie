package com.bobo.normalman.bobomovie.view.detail.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Video;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.detail.PlayVideoActivity;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class MovieVideoListAdapter extends BaseListAdapter<Video> {

    public MovieVideoListAdapter(List<Video> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        super(data, enableLoading, loadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        return new MovieVideoViewHolder(view);
    }

    @Override
    public void setListData(RecyclerView.ViewHolder holder, final Video data) {
        final MovieVideoViewHolder viewHolder = (MovieVideoViewHolder) holder;
        viewHolder.title.setText(data.name);
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewHolder.itemView.getContext();
                Intent intent = new Intent(context, PlayVideoActivity.class);
                intent.putExtra(PlayVideoActivity.VIDEO_KEY, data.key);
                intent.putExtra(PlayVideoActivity.VIDEO_TITLE, data.name);
                context.startActivity(intent);
            }
        });
    }
}
