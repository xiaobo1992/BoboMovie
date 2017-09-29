package com.bobo.normalman.bobomovie.view.tvlist;

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
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.base.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public class TVListFragment extends BaseListFragment<TV> {
    public static final String KEY_TYPE = "type";

    public static final String KEY_POPULAR = "popular";
    public static final String KEY_TOP_RATE = "top_rated";
    public static final String KEY_ON_AIR = "on_the_air";
    public static final String KEY_AIR_TODAY = "airing_today";

    public static final String KEY_TYPE_FAVOURITE = "favourite";

    TVListAdapter adapter;
    String type = null;

    public static TVListFragment newInstance(String type) {
        Bundle args = new Bundle();
        TVListFragment fragment = new TVListFragment();
        args.putString(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        type = getArguments().getString(KEY_TYPE);
        switch (type) {
            case KEY_POPULAR:
                setupAdapter(type, true);
                break;
            case KEY_TOP_RATE:
                setupAdapter(type, true);
                break;
            case KEY_ON_AIR:
                setupAdapter(type, true);
                break;
            case KEY_AIR_TODAY:
                setupAdapter(type, true);
                break;
            case KEY_TYPE_FAVOURITE:
                setupAdapter(type, false);
                break;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumns()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tv, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_popular:
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TVListFragment.newInstance(KEY_POPULAR))
                        .commit();
                break;
            case R.id.menu_air_today:
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TVListFragment.newInstance(KEY_AIR_TODAY))
                        .commit();
                break;
            case R.id.menu_top_rated:
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TVListFragment.newInstance(KEY_TOP_RATE))
                        .commit();
                break;
            case R.id.menu_on_tv:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TVListFragment.newInstance(KEY_ON_AIR))
                        .commit();
                break;

        }
        return false;
    }

    public void setupAdapter(final String type, boolean enableLoading) {
        adapter = new TVListAdapter(new ArrayList<TV>(), enableLoading, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadTVsTask(type, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
    }

    public class LoadTVsTask extends AsyncTask<Void, Void, List<TV>> {
        String type;
        int page;

        public LoadTVsTask(String type, int page) {
            this.type = type;
            this.page = page;
        }

        @Override
        protected List<TV> doInBackground(Void... voids) {
            try {
                return MovieDB.getTVs(type, page);
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
