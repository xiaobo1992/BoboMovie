package com.bobo.normalman.bobomovie.view.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public abstract class ProfileActivity<T> extends AppCompatActivity {
    T data;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;
    public static final String KEY_DATA = "data";
    public ImageView cover;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activty_detatil_viewpager);
        setContentView(R.layout.acticity_profile);
        setData();
        setToolbar();
        setViewPager();
        setupTitle();
        setCover();
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

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout  = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.movie_detail_pager);
        FragmentPagerAdapter pagerAdapter = setPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.movie_detail_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    public abstract FragmentPagerAdapter setPagerAdapter(FragmentManager fm);

    public abstract void setupTitle();

    public abstract void setData();

    public abstract void setCover();

}
