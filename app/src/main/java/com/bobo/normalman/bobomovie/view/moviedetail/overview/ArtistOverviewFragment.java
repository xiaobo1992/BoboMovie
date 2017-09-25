package com.bobo.normalman.bobomovie.view.moviedetail.overview;

import android.os.Bundle;

import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewAdapter;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewFragment;
import com.bobo.normalman.bobomovie.view.moviedetail.ProfileActivity;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistOverviewFragment extends BaseOverviewFragment<Artist> {
    public static ArtistOverviewFragment newInstance(Bundle args) {
        ArtistOverviewFragment fragment = new ArtistOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BaseOverviewAdapter setupAdapter(Artist data) {
        return new ArtistOverviewAdapter(data);
    }

    @Override
    public Artist getData() {
        return ModelUtil.toObject(getArguments().getString(ProfileActivity.KEY_DATA), new TypeToken<Artist>() {
        });
    }


}
