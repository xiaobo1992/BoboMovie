package com.bobo.normalman.bobomovie.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/24/17.
 */

public abstract class BaseOverviewFragment<T> extends Fragment {
    RecyclerView recycleView = null;
    BaseOverviewAdapter<T> adapter;
    T data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        recycleView = view.findViewById(R.id.recycleview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        data = getData();
        adapter = setupAdapter(data);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public abstract BaseOverviewAdapter setupAdapter(T data);

    public abstract T getData();
}
