package com.bobo.normalman.bobomovie.view.detail.video;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Video;
import com.bobo.normalman.bobomovie.view.base.BaseListAdapter;
import com.bobo.normalman.bobomovie.view.base.SpaceItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/20/17.
 */

public class MovieVideoFragment extends Fragment {
    private final int COUNT_PER_PAGE = 20;
    private static final String KEY_MOVIE_ID = "MOVIE_ID";
    private RecyclerView recycleView = null;
    private MovieVideoListAdapter adapter = null;
    private static final String KEY_STATE = "state";
    private static final String KEY_VIDEOS = "videos";
    private static final String KEY_ENABLE_LOADING = "loading";

    public static MovieVideoFragment newInstance(String movieId) {
        Bundle args = new Bundle();
        MovieVideoFragment fragment = new MovieVideoFragment();
        args.putString(KEY_MOVIE_ID, movieId);
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
        final String movieId = getArguments().getString(KEY_MOVIE_ID);
        adapter = new MovieVideoListAdapter(new ArrayList<Video>(), true, new BaseListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadVideosTask(movieId, adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.small_padding)));
    }

    /*
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putParcelable(KEY_STATE, recycleView.getLayoutManager().onSaveInstanceState());
            outState.putString(KEY_VIDEOS,
                    ModelUtil.toString(adapter.data, new TypeToken<List<Video>>() {
                    }));
            outState.putBoolean(KEY_ENABLE_LOADING, adapter.enableLoading);
        }

        @Override
        public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
            if (savedInstanceState != null) {
                List<Video> videos = ModelUtil.toObject(savedInstanceState.getString(KEY_VIDEOS),
                        new TypeToken<List<Video>>() {
                        });
                adapter.appendAllData(videos);
                adapter.setEnableLoading(savedInstanceState.getBoolean(KEY_ENABLE_LOADING));
                recycleView.getLayoutManager()
                        .onRestoreInstanceState(savedInstanceState.getParcelable(KEY_STATE));
            }
        }
    */
    public class LoadVideosTask extends AsyncTask<Void, Void, List<Video>> {
        final String movieId;
        final int page;

        public LoadVideosTask(String movieId, int page) {
            this.movieId = movieId;
            this.page = page;
        }

        @Override
        protected List<Video> doInBackground(Void... voids) {
            try {
                return MovieDB.getVideos(movieId, page);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            if (videos != null) {
                adapter.appendAllData(videos);
                adapter.setEnableLoading(videos.size() == COUNT_PER_PAGE);
            }
        }
    }
}
