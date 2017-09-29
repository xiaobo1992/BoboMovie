package com.bobo.normalman.bobomovie.view.detail.similar;

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
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.tvlist.TVListAdapter;
import com.bobo.normalman.bobomovie.view.tvlist.TVListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/25/17.
 */

public class SimilarTVFragment extends TVListFragment {
    TVListAdapter adapter = null;
    public static String KEY_TV_ID = "tv_id";

    public static SimilarTVFragment newInstance(String tvID) {
        Bundle args = new Bundle();
        SimilarTVFragment fragment = new SimilarTVFragment();
        args.putString(KEY_TV_ID, tvID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        final String tvID = getArguments().getString(KEY_TV_ID);
        adapter = new TVListAdapter(new ArrayList<TV>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadSimilarTVsTask(tvID, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    class LoadSimilarTVsTask extends AsyncTask<Void, Void, List<TV>> {

        String tvID;
        int page;

        public LoadSimilarTVsTask(String tvID, int page) {
            this.tvID = tvID;
            this.page = page;
        }

        @Override
        protected List<TV> doInBackground(Void... voids) {
            try {
                return MovieDB.getSimilarTV(tvID, page);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<TV> tvs) {
            if (tvs != null) {
                adapter.appendAllData(tvs);
                adapter.setEnableLoading(tvs.size() == COUNT_PER_PAGE);
            }
            super.onPostExecute(tvs);
        }
    }
}
