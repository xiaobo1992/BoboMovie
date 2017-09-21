package com.bobo.normalman.bobomovie.likemovie;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bobo.normalman.bobomovie.likemovie.LikeMovieContract.LikeMovieEntry;

/**
 * Created by xiaobozhang on 9/19/17.
 */

public class LikeMovieContentProvider extends ContentProvider {
    private LikeMovieDBHelper dbHelper;
    private final UriMatcher matcher = buildUriMatcher();

    private static final int LIKE_MOVIES = 100;
    private static final int LIKE_MOVIES_WITH_ID = 101;

    @Override
    public boolean onCreate() {
        dbHelper = new LikeMovieDBHelper(getContext());
        return true;
    }

    private UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(LikeMovieContract.AUTHORITY, LikeMovieContract.PATH_LIKEMOVIES, LIKE_MOVIES);
        matcher.addURI(LikeMovieContract.AUTHORITY,
                LikeMovieContract.PATH_LIKEMOVIES + "/#", LIKE_MOVIES_WITH_ID);
        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int code = matcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case LIKE_MOVIES:
                cursor = db.query(LikeMovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri retUri = null;
        int code = matcher.match(uri);
        switch (code) {
            case LIKE_MOVIES:
                long id = db.insert(LikeMovieEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    retUri = ContentUris.withAppendedId(LikeMovieEntry.CONTENT_URI, id);
                } else {
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String sel, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int code = matcher.match(uri);
        int deletedMovies;
        switch (code) {
            case LIKE_MOVIES:
                deletedMovies = db.delete(LikeMovieEntry.TABLE_NAME, sel, strings);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (deletedMovies > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedMovies;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
