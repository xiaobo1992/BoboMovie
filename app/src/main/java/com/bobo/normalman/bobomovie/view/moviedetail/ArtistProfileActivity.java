package com.bobo.normalman.bobomovie.view.moviedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.moviedetail.overview.ArtistOverviewFragment;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistProfileActivity extends ProfileActivity<Artist> {
    @Override
    public FragmentPagerAdapter setPagerAdapter(FragmentManager fm) {
        return new PageAdapter(fm);
    }

    @Override
    public void setupTitle() {
        setTitle(data.name);
    }

    @Override
    public void setData() {
        data = ModelUtil.toObject(getIntent().getExtras().getString(KEY_DATA), new TypeToken<Artist>() {
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
                    return ArtistOverviewFragment.newInstance(getIntent().getExtras());
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.overview);
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
