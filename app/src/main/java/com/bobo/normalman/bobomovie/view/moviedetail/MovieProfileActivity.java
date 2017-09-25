package com.bobo.normalman.bobomovie.view.moviedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.moviedetail.overview.MovieOverviewFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.review.MovieReviewFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.video.MovieVideoFragment;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class MovieProfileActivity extends ProfileActivity<Movie> {

    @Override
    public FragmentPagerAdapter setPagerAdapter(FragmentManager fm) {
        return new PageAdapter(fm);
    }

    @Override
    public void setupTitle() {
        setTitle(data.title);
    }

    @Override
    public void setData() {
        data = ModelUtil.toObject(getIntent().getExtras().getString(KEY_DATA), new TypeToken<Movie>() {
        });
    }

    class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MovieOverviewFragment.newInstance(getIntent().getExtras());
                case 1:
                    return MovieReviewFragment.newInstance(data.id);
                case 2:
                    return MovieVideoFragment.newInstance(data.id);
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.overview);
                case 1:
                    return getResources().getString(R.string.review);
                case 2:
                    return getResources().getString(R.string.video);
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
