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

public class TVCastFragment extends ArtistListFragment {
    public static final String KEY_TV_ID = "tv_id";

    public static TVCastFragment newInstance(String tvID) {
        Bundle args = new Bundle();
        TVCastFragment fragment = new TVCastFragment();
        args.putString(KEY_TV_ID, tvID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String tvID = getArguments().getString(KEY_TV_ID);
        adapter = new ArtistListAdapter(new ArrayList<Artist>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadMoreTVCast(tvID));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    public class LoadMoreTVCast extends AsyncTask<Void, Void, List<Artist>> {
        String tvID;

        public LoadMoreTVCast(String tvID) {
            this.tvID = tvID;
        }

        @Override
        protected List<Artist> doInBackground(Void... voids) {
            try {
                return MovieDB.getTVCast(tvID);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Artist> artists) {
            if (artists != null) {
                adapter.appendAllData(artists);
                adapter.setEnableLoading(false);
            }
            super.onPostExecute(artists);
        }
    }
}
