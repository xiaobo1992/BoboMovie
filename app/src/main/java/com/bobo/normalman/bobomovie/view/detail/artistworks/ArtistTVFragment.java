package com.bobo.normalman.bobomovie.view.detail.artistworks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.tvlist.TVListAdapter;
import com.bobo.normalman.bobomovie.view.tvlist.TVListFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/28/17.
 */

public class ArtistTVFragment extends TVListFragment {
    public static final String KEY_PERSON_ID = "person_id";
    TVListAdapter adapter;

    public static ArtistTVFragment newInstance(String personID) {
        Bundle args = new Bundle();
        ArtistTVFragment fragment = new ArtistTVFragment();
        args.putString(KEY_PERSON_ID, personID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String personID = getArguments().getString(KEY_PERSON_ID);
        adapter = new TVListAdapter(new ArrayList<TV>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadArtistTVsTask(personID));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    public class LoadArtistTVsTask extends AsyncTask<Void, Void, List<TV>> {
        String personID;

        public LoadArtistTVsTask(String personID) {
            this.personID = personID;
        }

        @Override
        protected List<TV> doInBackground(Void... voids) {
            try {
                return MovieDB.getTVCredits(personID);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<TV> tvs) {
            if (tvs != null) {
                adapter.appendAllData(tvs);
                adapter.setEnableLoading(false);
            }
            super.onPostExecute(tvs);
        }
    }
}
