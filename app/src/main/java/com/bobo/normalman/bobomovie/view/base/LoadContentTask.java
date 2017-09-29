package com.bobo.normalman.bobomovie.view.base;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by xiaobozhang on 9/25/17.
 */

public abstract class LoadContentTask<T> extends AsyncTask<Void, Void, List<T>> {

    @Override
    protected List<T> doInBackground(Void... voids) {

        return null;
    }


}
