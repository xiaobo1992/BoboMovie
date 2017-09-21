package com.bobo.normalman.bobomovie.View.moviedetail.review;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.Util.ModelUtil;
import com.bobo.normalman.bobomovie.View.base.SpaceItemDecoration;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.model.Review;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/20/17.
 */

public class MovieReviewFragment extends Fragment {
    public static final String KEY_MOVIE_ID = "MOVIE_ID";
    public int COUNT_PER_PAGE = 20;
    RecyclerView recycleView;
    MovieReviewAdapter adapter;

    public static MovieReviewFragment newInstance(String movieId) {
        Bundle args = new Bundle();
        MovieReviewFragment fragment = new MovieReviewFragment();
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
        adapter = new MovieReviewAdapter(new ArrayList<Review>(), true, new MovieReviewAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                AsyncTaskCompat.executeParallel(new LoadReviewTask(movieId,
                        adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.small_padding)));
    }

    public class LoadReviewTask extends AsyncTask<Void, Void, List<Review>> {
        String movieId;
        int page;

        public LoadReviewTask(String movieId, int page) {
            this.movieId = movieId;
            this.page = page;
        }

        @Override
        protected List<Review> doInBackground(Void... voids) {
            try {
                return MovieDB.getReviews(movieId, page);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Review> reviews) {
            if (reviews != null) {
                adapter.appendReviews(reviews);
                adapter.setEnableLoading(reviews.size() == COUNT_PER_PAGE);
            }
        }
    }
}
