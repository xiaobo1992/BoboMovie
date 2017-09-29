package com.bobo.normalman.bobomovie.view.detail.overview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.os.AsyncTaskCompat;

import com.bobo.normalman.bobomovie.MovieDB.MovieDB;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.util.ModelUtil;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewAdapter;
import com.bobo.normalman.bobomovie.view.base.BaseOverviewFragment;
import com.bobo.normalman.bobomovie.view.detail.ProfileActivity;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public class ArtistOverviewFragment extends BaseOverviewFragment<Artist> {
    ArtistOverviewAdapter adapter;

    public static ArtistOverviewFragment newInstance(Bundle args) {
        ArtistOverviewFragment fragment = new ArtistOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BaseOverviewAdapter setupAdapter(Artist data) {
        adapter = new ArtistOverviewAdapter(data);
        return adapter;
    }

    @Override
    public Artist getData() {
        Artist artist = ModelUtil.toObject(getArguments().getString(ProfileActivity.KEY_DATA), new TypeToken<Artist>() {
        });
        AsyncTaskCompat.executeParallel(new LoadArtistDetailTask(artist.id));
        return artist;
    }

    class LoadArtistDetailTask extends AsyncTask<Void, Void, Artist> {
        String person_id;

        public LoadArtistDetailTask(String person_id) {
            this.person_id = person_id;
        }

        @Override
        protected Artist doInBackground(Void... voids) {
            try {
                return MovieDB.getArtistDetail(person_id);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Artist artist) {
            adapter.setData(artist);
        }
    }
}
