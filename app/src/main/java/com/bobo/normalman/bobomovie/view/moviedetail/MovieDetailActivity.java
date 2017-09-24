package com.bobo.normalman.bobomovie.view.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.moviedetail.profile.MovieProfileFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.review.MovieReviewFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.video.MovieVideoFragment;
import com.bobo.normalman.bobomovie.model.Movie;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/20/17.
 */

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_movie_detatil_viewpager);
        this.movie = ModelUtil.toObject(getIntent().getExtras().getString(MovieProfileFragment.KEY_MOVE),
                new TypeToken<Movie>() {});
        setTitle(movie.title);
        setViewPager();
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.movie_detail_pager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.movie_detail_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MovieProfileFragment.newInstance(getIntent().getExtras());
                case 1:
                    return MovieReviewFragment.newInstance(movie.id);
                case 2:
                    return MovieVideoFragment.newInstance(movie.id);
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
