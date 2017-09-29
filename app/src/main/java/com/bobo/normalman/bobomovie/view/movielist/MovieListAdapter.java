package com.bobo.normalman.bobomovie.view.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.detail.MovieProfileActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class MovieListAdapter extends BaseListAdapter<Movie> {
    public MovieListAdapter(List<Movie> data, boolean enableLoading, LoadMoreListener loadMoreListener) {
        super(data, enableLoading, loadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_media, parent, false);
        return new MovieListHolder(view);
    }

    @Override
    public void setListData(final RecyclerView.ViewHolder holder, final Movie data) {
        MovieListHolder movieListHolder = (MovieListHolder) holder;
        ImageUtil.loadImage(movieListHolder.itemView.getContext(),
                movieListHolder.imageView, data.poster_path);
        movieListHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, MovieProfileActivity.class);
                String movie = ModelUtil.toString(data, new TypeToken<Movie>() {
                });
                intent.putExtra(MovieProfileActivity.KEY_DATA, movie);
                context.startActivity(intent);
            }
        });
        movieListHolder.name.setText(data.title);
    }
}
