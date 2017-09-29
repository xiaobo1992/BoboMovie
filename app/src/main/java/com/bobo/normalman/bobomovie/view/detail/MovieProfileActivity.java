package com.bobo.normalman.bobomovie.view.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.detail.cast.MovieCastFragment;
import com.bobo.normalman.bobomovie.view.detail.crew.MovieCrewFragment;
import com.bobo.normalman.bobomovie.view.detail.overview.MovieOverviewFragment;
import com.bobo.normalman.bobomovie.view.detail.recommend.RecommendMovieFragment;
import com.bobo.normalman.bobomovie.view.detail.review.MovieReviewFragment;
import com.bobo.normalman.bobomovie.view.detail.similar.SimilarMovieFragment;
import com.bobo.normalman.bobomovie.view.detail.video.MovieVideoFragment;
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
        collapsingToolbarLayout.setTitle(data.title);
    }

    @Override
    public void setData() {
        data = ModelUtil.toObject(getIntent().getExtras().getString(KEY_DATA), new TypeToken<Movie>() {
        });
    }

    @Override
    public void setCover() {
        cover = (ImageView) findViewById(R.id.collapse_image);
        ImageUtil.loadImage(getApplicationContext(), cover, data.backdrop_path);
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
                case 3:
                    return SimilarMovieFragment.newInstance(data.id);
                case 4:
                    return RecommendMovieFragment.newInstance(data.id);
                case 5:
                    return MovieCastFragment.newInstance(data.id);
                case 6:
                    return MovieCrewFragment.newInstance(data.id);
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
                case 3:
                    return getString(R.string.similar);
                case 4:
                    return getString(R.string.recommend);
                case 5:
                    return getString(R.string.cast);
                case 6:
                    return getString(R.string.crew);
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 7;
        }
    }

}
