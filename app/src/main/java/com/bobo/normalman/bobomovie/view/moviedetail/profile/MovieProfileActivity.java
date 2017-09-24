package com.bobo.normalman.bobomovie.view.moviedetail.profile;

import android.support.v4.app.Fragment;

import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.SingleFragmentActivity;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieProfileActivity extends SingleFragmentActivity {

    @Override
    public Fragment newFragment() {
        Movie movie = ModelUtil.toObject(getIntent().getExtras().getString(MovieProfileFragment.KEY_MOVE),
                new TypeToken<Movie>() {
                });
        setTitle(movie.title);
        return MovieProfileFragment.newInstance(getIntent().getExtras());
    }

}
