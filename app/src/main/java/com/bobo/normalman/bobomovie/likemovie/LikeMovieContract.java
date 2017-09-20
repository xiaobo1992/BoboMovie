package com.bobo.normalman.bobomovie.likemovie;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by xiaobozhang on 9/19/17.
 */

public final class LikeMovieContract {

    public LikeMovieContract() {
    }

    public static final String AUTHORITY = "com.bobo.normalman.bobomovie";
    public static final String PATH_LIKEMOVIES = "likemovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static class LikeMovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI
                .buildUpon().appendPath(PATH_LIKEMOVIES).build();
        public static final String TABLE_NAME = "likemovies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";

    }
}
