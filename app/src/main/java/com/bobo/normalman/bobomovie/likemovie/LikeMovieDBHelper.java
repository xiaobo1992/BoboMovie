package com.bobo.normalman.bobomovie.likemovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bobo.normalman.bobomovie.likemovie.LikeMovieContract.LikeMovieEntry;

/**
 * Created by xiaobozhang on 9/19/17.
 */

class LikeMovieDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "likemovie.db";
    private static final int DB_VERSION = 1;

    public LikeMovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_QUERY = "CREATE TABLE " + LikeMovieEntry.TABLE_NAME + " ("
                + LikeMovieEntry._ID + " INTEGER PRIMARY KEY,"
                + LikeMovieEntry.COLUMN_NAME_MOVIE_ID + " TEXT NOT NULL,"
                + LikeMovieEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL,"
                + LikeMovieEntry.COLUMN_NAME_POSTER_PATH + " TEXT,"
                + LikeMovieEntry.COLUMN_NAME_OVERVIEW + " TEXT,"
                + LikeMovieEntry.COLUMN_NAME_VOTE_AVERAGE + " NUMBER,"
                + LikeMovieEntry.COLUMN_NAME_RELEASE_DATE + " TEXT"
                + ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LikeMovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
