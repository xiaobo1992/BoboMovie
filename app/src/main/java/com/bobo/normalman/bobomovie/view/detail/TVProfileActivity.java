package com.bobo.normalman.bobomovie.view.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.util.ImageUtil;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.detail.cast.TVCastFragment;
import com.bobo.normalman.bobomovie.view.detail.overview.TVOverviewFragment;
import com.bobo.normalman.bobomovie.view.detail.recommend.RecommendTVFragment;
import com.bobo.normalman.bobomovie.view.detail.similar.SimilarTVFragment;
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
                    return TVOverviewFragment.newInstance(getIntent().getExtras());
                case 1:
                    return SimilarTVFragment.newInstance(data.id);
                case 2:
                    return RecommendTVFragment.newInstance(data.id);
                case 3:
                    return TVCastFragment.newInstance(data.id);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.overview);
                case 1:
                    return getString(R.string.similar);
                case 2:
                    return getString(R.string.recommend);
                case 3:
                    return getString(R.string.cast);

            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
