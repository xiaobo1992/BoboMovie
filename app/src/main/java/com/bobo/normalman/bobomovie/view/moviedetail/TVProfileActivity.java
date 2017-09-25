package com.bobo.normalman.bobomovie.view.moviedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.moviedetail.overview.TVOverviewFragment;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class TVProfileActivity extends ProfileActivity<TV> {
    @Override
    public PageAdapter setPagerAdapter(FragmentManager fm) {
        return new PageAdapter(fm);
    }

    @Override
    public void setupTitle() {
        setTitle(data.original_name);
    }

    @Override
    public void setData() {
        data = ModelUtil.toObject(getIntent().getExtras().getString(KEY_DATA), new TypeToken<TV>() {
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
                    return TVOverviewFragment.newInstance(getIntent().getExtras());
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.overview);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
