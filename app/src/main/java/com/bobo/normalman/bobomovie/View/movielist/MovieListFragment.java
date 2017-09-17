package com.bobo.normalman.bobomovie.View.movielist;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.Model.Movie;
import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class MovieListFragment extends Fragment {
    private static String KEY_TYPE = "VIEW_TYPE";
    public static String KEY_POPULAR_TYPE = "popular";
    public static String KEY_TOP_TYPE = "top_rated";

    private static int COUNT_PER_PAGE = 20;
    private MovieListAdapter adapter = null;
    private RecyclerView recycleView = null;
    private String type = null;

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
        adapter = new MovieListAdapter(new ArrayList<Movie>(), new MovieListAdapter.loadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadMoviesTask(type, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        GridLayoutManager layoutManager = null;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getContext(), 2);
        } else {
            layoutManager = new GridLayoutManager(getContext(), 3);
        }
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(layoutManager);
    }

    public class LoadMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        int page;
        String type;

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
            }
        }
    }
}
