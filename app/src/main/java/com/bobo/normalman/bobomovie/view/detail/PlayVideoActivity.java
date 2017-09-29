package com.bobo.normalman.bobomovie.view.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bobo.normalman.bobomovie.R;

/**
 * Created by xiaobozhang on 9/20/17.
 */

public class PlayVideoActivity extends AppCompatActivity {
    public static final String VIDEO_KEY = "video_key";
    public static final String VIDEO_TITLE = "video_title";

    private static final String YOUTUBE_BASE = "https://www.youtube.com/watch?v=";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        setToolbar();
        setTitle(getIntent().getStringExtra(VIDEO_TITLE));
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        String key = getIntent().getStringExtra(VIDEO_KEY);
        webView.loadUrl(YOUTUBE_BASE + key);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
