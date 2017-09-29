package com.bobo.normalman.bobomovie.view.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.detail.artistworks.ArtistMovieFragment;
import com.bobo.normalman.bobomovie.view.detail.artistworks.ArtistTVFragment;
import com.bobo.normalman.bobomovie.view.detail.overview.ArtistOverviewFragment;
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

    @Override
    public void setCover() {

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
                case 1:
                    return ArtistMovieFragment.newInstance(data.id);
                case 2:
                    return ArtistTVFragment.newInstance(data.id);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.overview);
                case 1:
                    return getString(R.string.movie);
                case 2:
                    return getString(R.string.TV);
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
