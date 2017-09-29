package com.bobo.normalman.bobomovie.view.detail.recommend;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.movielist.MovieListAdapter;
import com.bobo.normalman.bobomovie.view.movielist.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/25/17.
 */

public class RecommendMovieFragment extends MovieListFragment {
    MovieListAdapter adapter = null;
    public static final String KEY_MOVIE_ID = "movie_id";

    public static RecommendMovieFragment newInstance(String movieID) {
        Bundle args = new Bundle();
        RecommendMovieFragment fragment = new RecommendMovieFragment();
        args.putString(KEY_MOVIE_ID, movieID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        final String movieID = getArguments().getString(KEY_MOVIE_ID);
        adapter = new MovieListAdapter(new ArrayList<Movie>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadRecommendMoviesTask(movieID, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        layoutManager = new GridLayoutManager(getContext(), getColumns());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    public class LoadRecommendMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        String movieID;
        int page;

        public LoadRecommendMoviesTask(String movieID, int page) {
            this.movieID = movieID;
            this.page = page;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            try {
                return MovieDB.getRecommendMovie(movieID, page);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null) {
                adapter.appendAllData(movies);
                adapter.setEnableLoading(movies.size() == COUNT_PER_PAGE);
            }
            super.onPostExecute(movies);
        }
    }
}
