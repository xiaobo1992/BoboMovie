package com.bobo.normalman.bobomovie.view.movielist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.LikeMovieUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.base.BaseListFragment;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class MovieListFragment extends BaseListFragment {
    private static final String KEY_TYPE = "VIEW_TYPE";

    public static final String KEY_POPULAR = "popular";
    public static final String KEY_TOP_RATE = "top_rated";
    public static final String KEY_FAVOURITE = "favourite";
    public static final String KEY_UPCOMING = "upcoming";
    public static final String KEY_NOW_PALYING = "now_playing";

    private static final String KEY_MOVIES = "movies";
    private static final String KEY_STATE = "state";
    private static final String KEY_ENABLE_LOADING = "loading";

    private MovieListAdapter adapter = null;
    private RecyclerView recycleView = null;
    private String type = null;
    GridLayoutManager layoutManager = null;

    public static MovieListFragment newInstance(String type) {
        Bundle args = new Bundle();
        MovieListFragment fragment = new MovieListFragment();
        args.putString(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        recycleView = view.findViewById(R.id.recycleview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        type = getArguments().getString(KEY_TYPE);
        setHasOptionsMenu(true);
        switch (type) {
            case KEY_POPULAR:
                setAdapter(type, true);
                break;
            case KEY_TOP_RATE:
                setAdapter(type, true);
                break;
            case KEY_UPCOMING:
                setAdapter(type, true);
                break;
            case KEY_NOW_PALYING:
                setAdapter(type, true);
                break;
            case KEY_FAVOURITE:
                List<Movie> movies = LikeMovieUtil.loadAllLikedMovie(getContext());
                setAdapter(type, false);
                adapter.appendAllData(movies);
                break;
        }

        layoutManager = new GridLayoutManager(getContext(), getColumns());
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == KEY_FAVOURITE) {
            List<Movie> movies = LikeMovieUtil.loadAllLikedMovie(getContext());
            adapter.setData(movies);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable state = layoutManager.onSaveInstanceState();
        outState.putParcelable(KEY_STATE, state);
        outState.putString(KEY_MOVIES, ModelUtil.toString(adapter.data,
                new TypeToken<List<Movie>>() {
                }));
        outState.putBoolean(KEY_ENABLE_LOADING, adapter.enableLoading);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(KEY_STATE);
            List<Movie> movies = ModelUtil.toObject(savedInstanceState.getString(KEY_MOVIES),
                    new TypeToken<List<Movie>>() {
                    });
            adapter.appendAllData(movies);
            adapter.setEnableLoading(savedInstanceState.getBoolean(KEY_ENABLE_LOADING));
            layoutManager.onRestoreInstanceState(state);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_top_rated:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_TOP_RATE))
                        .commit();
                return true;
            case R.id.menu_popular:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_POPULAR))
                        .commit();
                return true;
            case R.id.menu_favourite:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_FAVOURITE))
                        .commit();
                return true;
            case R.id.menu_now_playing:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_NOW_PALYING))
                        .commit();
                return true;
            case R.id.menu_upcoming:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_UPCOMING))
                        .commit();
                return true;

        }
        return false;
    }

    public void setAdapter(final String type, boolean enableLoading) {
        adapter = new MovieListAdapter(new ArrayList<Movie>(), enableLoading,
                new BaseListAdapter.LoadMoreListener() {
                    @Override
                    public void loadMore() {
                        AsyncTaskCompat.executeParallel(new LoadMoviesTask(type, adapter.getDataCount() / COUNT_PER_PAGE + 1));

                    }
                });
    }

    public class LoadMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        final int page;
        final String type;

        public LoadMoviesTask(String type, int page) {
            this.page = page;
            this.type = type;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            try {
                return MovieDB.getMovies(type, page);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null) {
                adapter.appendAllData(movies);
                adapter.setEnableLoading(movies.size() == COUNT_PER_PAGE);
            }
        }
    }
}
