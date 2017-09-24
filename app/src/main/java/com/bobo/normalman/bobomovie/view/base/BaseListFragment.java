package com.bobo.normalman.bobomovie.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/23/17.
 */

public abstract class BaseListFragment extends Fragment {
    public RecyclerView recyclerView;
    public static final int COUNT_PER_PAGE = 20;
    public static final int IMAGE_WIDTH = 400;
    public static final int MIN_COLUMN = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        recyclerView = view.findViewById(R.id.recycleview);
        return view;
    }

    @Override
    public abstract void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    @Override
    public abstract void onCreateOptionsMenu(Menu menu, MenuInflater inflater);

    @Override
    public abstract boolean onOptionsItemSelected(MenuItem item);

    public int getColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = IMAGE_WIDTH;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < MIN_COLUMN) return MIN_COLUMN;
        return nColumns;
    }
}
