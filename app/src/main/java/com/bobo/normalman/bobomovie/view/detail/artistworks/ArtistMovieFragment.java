package com.bobo.normalman.bobomovie.view.detail.artistworks;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/28/17.
 */

public class ArtistMovieFragment extends MovieListFragment {
    public static final String KEY_PERSON_ID = "person_id";
    MovieListAdapter adapter;

    public static ArtistMovieFragment newInstance(String personID) {
        Bundle args = new Bundle();
        ArtistMovieFragment fragment = new ArtistMovieFragment();
        args.putString(KEY_PERSON_ID, personID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String personID = getArguments().getString(KEY_PERSON_ID);
        adapter = new MovieListAdapter(new ArrayList<Movie>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadArtistMoviesTask(personID));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    public class LoadArtistMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        String personID;

        public LoadArtistMoviesTask(String personID) {
            this.personID = personID;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            try {
                return MovieDB.getMovieCredits(personID);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null) {
                adapter.appendAllData(movies);
                adapter.setEnableLoading(false);
            }
            super.onPostExecute(movies);
        }
    }

}
