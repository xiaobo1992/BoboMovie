package com.bobo.normalman.bobomovie.View.moviedetail.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.Util.ModelUtil;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieProfileFragment extends Fragment {
    private RecyclerView recycleView = null;
    public static final String KEY_MOVE = "MOVIE";

    public static MovieProfileFragment newInstance(Bundle args) {
        MovieProfileFragment fragment = new MovieProfileFragment();
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
        Movie movie = ModelUtil.toObject(getArguments().getString(KEY_MOVE), new TypeToken<Movie>() {
        });
        MovieProfileAdapter adapter = new MovieProfileAdapter(movie);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
