package com.bobo.normalman.bobomovie.view.artistlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.base.BaseListFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class ArtistListFragment extends BaseListFragment {
    ArtistListAdapter adapter = null;
    public static final String KEY_POPULAR = "popular";

    public static ArtistListFragment newInstance() {
        Bundle args = new Bundle();
        ArtistListFragment fragment = new ArtistListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        adapter = new ArtistListAdapter(new ArrayList<Artist>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadArtistsTask(KEY_POPULAR, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public class LoadArtistsTask extends AsyncTask<Void, Void, List<Artist>> {
        String type;
        int page;

        public LoadArtistsTask(String type, int page) {
            this.type = type;
            this.page = page;
        }

        @Override
        protected List<Artist> doInBackground(Void... voids) {
            try {
                return MovieDB.getArtists(type, page);
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
