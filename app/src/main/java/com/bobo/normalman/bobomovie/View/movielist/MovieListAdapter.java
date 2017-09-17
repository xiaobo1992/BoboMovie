package com.bobo.normalman.bobomovie.View.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.Model.Movie;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.Util.ImageUtil;
import com.bobo.normalman.bobomovie.Util.ModelUtil;
import com.bobo.normalman.bobomovie.View.moviedetail.MovieDetailActivity;
import com.bobo.normalman.bobomovie.View.moviedetail.MovieDetailFragment;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter {
    private List<Movie> data;
    private static final int VIEW_TYPE_MOVIE = 0;
    private static final int VIEW_TYPE_LOADING = 1;
    private loadMoreListener loadMoreListener;

    public MovieListAdapter(List<Movie> movies, loadMoreListener listener) {
        this.data = movies;
        this.loadMoreListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieListHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_LOADING:
                loadMoreListener.loadMore();
                break;
            case VIEW_TYPE_MOVIE:
                final MovieListHolder movieListHolder = (MovieListHolder) holder;
                final Movie movie = data.get(position);
                ImageUtil.loadImage(holder.itemView.getContext(),
                        movieListHolder.imageView, movie.poster_path);
                movieListHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = holder.itemView.getContext();
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        String movieStr = ModelUtil.toString(movie, new TypeToken<Movie>() {
                        });
                        intent.putExtra(MovieDetailFragment.KEY_MOVE, movieStr);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < data.size()) {
            return VIEW_TYPE_MOVIE;
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    public void appendAllData(List<Movie> movies) {
        data.addAll(movies);
        notifyDataSetChanged();
    }

    public int getDataCount() {
        return data.size();
    }

    public interface loadMoreListener {
        void loadMore();
    }
}