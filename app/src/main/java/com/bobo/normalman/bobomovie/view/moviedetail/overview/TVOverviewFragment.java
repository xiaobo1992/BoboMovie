package com.bobo.normalman.bobomovie.view.moviedetail.overview;

import android.os.Bundle;

import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.ProfileActivity;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class TVOverviewFragment extends BaseOverviewFragment<TV> {

    public static TVOverviewFragment newInstance(Bundle args) {
        TVOverviewFragment fragment = new TVOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public TVOverviewAdapter setupAdapter(TV data) {
        return new TVOverviewAdapter(data);
    }

    @Override
    public TV getData() {
        return ModelUtil.toObject(getArguments().getString(ProfileActivity.KEY_DATA), new TypeToken<TV>() {
        });
    }
}
