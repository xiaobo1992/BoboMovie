package com.bobo.normalman.bobomovie.View.moviedetail.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.View.moviedetail.PlayVideoActivity;
import com.bobo.normalman.bobomovie.model.Video;

import java.util.List;

/**
 * Created by xiaobozhang on 9/20/17.
 */

class MovieVideoAdapter extends RecyclerView.Adapter {
    private final List<Video> videos;
    private final LoadMoreListener listener;
    private boolean enableLoading;
    private static final int VIEW_TYPE_VIDEO = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public MovieVideoAdapter(List<Video> videos, boolean enableLoading, LoadMoreListener listener) {
        this.videos = videos;
        this.enableLoading = enableLoading;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        return new MovieVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_LOADING:
                if (enableLoading) {
                    listener.loadMore();
                }
                break;
            case VIEW_TYPE_VIDEO:
                final MovieVideoViewHolder viewHolder = (MovieVideoViewHolder) holder;
                final Video video = videos.get(position);
                viewHolder.title.setText(video.name);
                viewHolder.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = viewHolder.itemView.getContext();
                        Intent intent = new Intent(context, PlayVideoActivity.class);
                        intent.putExtra(PlayVideoActivity.VIDEO_KEY, video.key);
                        intent.putExtra(PlayVideoActivity.VIDEO_TITLE, video.name);
                        context.startActivity(intent);
                    }
                });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < videos.size()) {
            return VIEW_TYPE_VIDEO;
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    @Override
    public int getItemCount() {
        return enableLoading ? videos.size() + 1 : videos.size();
    }


    public int getDataCount() {
        return videos.size();
    }

    public void appendVideos(List<Video> v) {
        videos.addAll(v);
        notifyDataSetChanged();
    }

    public void setEnableLoading(boolean enableLoading) {
        this.enableLoading = enableLoading;
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
