package com.bobo.normalman.bobomovie.View.moviedetail;

import android.support.v4.app.Fragment;

import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.Util.ModelUtil;
import com.bobo.normalman.bobomovie.View.base.SingleFragmentActivity;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/15/17.
 */

public class MovieDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment newFragment() {
        Movie movie = ModelUtil.toObject(getIntent().getExtras().getString(MovieDetailFragment.KEY_MOVE),
                new TypeToken<Movie>() {
                });
        setTitle(movie.title);
        return MovieDetailFragment.newInstance(getIntent().getExtras());
    }

}
