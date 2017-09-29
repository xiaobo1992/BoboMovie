package com.bobo.normalman.bobomovie.view.detail.overview;

import android.os.Bundle;

import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewFragment;
import com.bobo.normalman.bobomovie.view.detail.ProfileActivity;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieOverviewFragment extends BaseOverviewFragment<Movie> {

    public static MovieOverviewFragment newInstance(Bundle args) {
        MovieOverviewFragment fragment = new MovieOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MovieOverviewAdapter setupAdapter(Movie data) {
        return new MovieOverviewAdapter(data);
    }

    @Override
    public Movie getData() {
        return ModelUtil.toObject(getArguments().getString(ProfileActivity.KEY_DATA), new TypeToken<Movie>() {
        });
    }
}
