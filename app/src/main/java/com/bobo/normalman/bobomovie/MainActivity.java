package com.bobo.normalman.bobomovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bobo.normalman.bobomovie.View.movielist.MovieListFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,
                            MovieListFragment.newInstance(MovieListFragment.KEY_POPULAR_TYPE))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_top_rated:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_TOP_TYPE))
                        .commit();
                return true;
            case R.id.menu_popular:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_POPULAR_TYPE))
                        .commit();
                return true;
            case R.id.menu_favourite:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(MovieListFragment.KEY_TOP_FAVOURITE))
                        .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
