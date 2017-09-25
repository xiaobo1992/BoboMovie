package com.bobo.normalman.bobomovie;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bobo.normalman.bobomovie.view.artistlist.ArtistListFragment;
import com.bobo.normalman.bobomovie.view.movielist.MovieListFragment;
import com.bobo.normalman.bobomovie.view.tvlist.TVListFragment;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setupNavDrawer();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,
                            MovieListFragment.newInstance(MovieListFragment.KEY_POPULAR))
                    .commit();
            setTitle(getString(R.string.movie));
        }

    }

    public void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setupNavDrawer() {
        navigationView = (NavigationView) findViewById(R.id.main_nav);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_movie:
                        fragment = MovieListFragment.newInstance(MovieListFragment.KEY_POPULAR);
                        setTitle(getString(R.string.movie));
                        break;
                    case R.id.menu_tv:
                        fragment = TVListFragment.newInstance(TVListFragment.KEY_POPULAR);
                        setTitle(R.string.TV);
                        break;
                    case R.id.menu_artist:
                        fragment = ArtistListFragment.newInstance();
                        setTitle(getString(R.string.artist));
                }

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
