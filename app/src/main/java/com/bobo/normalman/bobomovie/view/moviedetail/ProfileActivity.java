package com.bobo.normalman.bobomovie.view.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public abstract class ProfileActivity<T> extends AppCompatActivity {
    T data;
    ViewPager viewPager;
    public static final String KEY_DATA = "data";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_detatil_viewpager);
        setData();
        setupTitle();
        setToolbar();
        setViewPager();
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
        getSupportActionBar().setHomeButtonEnabled(true);
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

}
