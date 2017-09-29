package com.bobo.normalman.bobomovie.view.detail.cast;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.view.artistlist.ArtistListAdapter;
import com.bobo.normalman.bobomovie.view.artistlist.ArtistListFragment;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/25/17.
 */

public class MovieCastFragment extends ArtistListFragment {

    public static final String KEY_MOVIE_ID = "movie_id";

    public static MovieCastFragment newInstance(String movieID) {
        Bundle args = new Bundle();
        MovieCastFragment fragment = new MovieCastFragment();
        args.putString(KEY_MOVIE_ID, movieID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String movieID = getArguments().getString(KEY_MOVIE_ID);
        adapter = new ArtistListAdapter(new ArrayList<Artist>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadMovieCastTask(movieID));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    class LoadMovieCastTask extends AsyncTask<Void, Void, List<Artist>> {
        String movieID;

        public LoadMovieCastTask(String movieID) {
            this.movieID = movieID;
        }

        @Override
        protected List<Artist> doInBackground(Void... voids) {
            try {
                return MovieDB.getMovieCast(movieID);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Artist> artists) {
            if (artists != null) {
                adapter.appendAllData(artists);
                adapter.setEnableLoading(artists.size() == COUNT_PER_PAGE);
            }
            super.onPostExecute(artists);
        }
    }
}
