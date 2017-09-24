package com.bobo.normalman.bobomovie.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bobo.normalman.bobomovie.likemovie.LikeMovieContract.LikeMovieEntry;
import com.bobo.normalman.bobomovie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozhang on 9/19/17.
 */

public class LikeMovieUtil {

    public static boolean isLike(Context context, Movie movie) {
        Cursor cursor = context.getContentResolver()
                .query(LikeMovieEntry.CONTENT_URI,
                        null,
                        LikeMovieEntry.COLUMN_NAME_MOVIE_ID + " = ?",
                        new String[]{movie.id},
                        null);
        return cursor.getCount() == 1;
    }

    public static void LikeMovie(Context context, Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LikeMovieEntry.COLUMN_NAME_MOVIE_ID, movie.id);
        contentValues.put(LikeMovieEntry.COLUMN_NAME_TITLE, movie.title);
        contentValues.put(LikeMovieEntry.COLUMN_NAME_POSTER_PATH, movie.poster_path);
        contentValues.put(LikeMovieEntry.COLUMN_NAME_OVERVIEW, movie.overview);
        contentValues.put(LikeMovieEntry.COLUMN_NAME_RELEASE_DATE, movie.release_date);
        contentValues.put(LikeMovieEntry.COLUMN_NAME_VOTE_AVERAGE, movie.vote_average);
        context.getContentResolver().insert(LikeMovieEntry.CONTENT_URI, contentValues);
    }

    public static void disLikeMovie(Context context, Movie movie) {
        context.getContentResolver().delete(LikeMovieEntry.CONTENT_URI, LikeMovieEntry.COLUMN_NAME_MOVIE_ID + " = ?",
                new String[]{movie.id});
    }

    public static List<Movie> loadAllLikedMovie(Context context) {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = context.getContentResolver()
                .query(LikeMovieEntry.CONTENT_URI, null, null, null, null);
        int movieIdIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_MOVIE_ID);
        int titleIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_TITLE);
        int posterIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_POSTER_PATH);
        int overviewIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_OVERVIEW);
        int releaseIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_RELEASE_DATE);
        int voteIndex = cursor.getColumnIndex(LikeMovieEntry.COLUMN_NAME_VOTE_AVERAGE);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Movie movie = new Movie();
                movie.id = cursor.getString(movieIdIndex);
                movie.title = cursor.getString(titleIndex);
                movie.poster_path = cursor.getString(posterIndex);
                movie.overview = cursor.getString(overviewIndex);
                movie.release_date = cursor.getString(releaseIndex);
                movie.vote_average = cursor.getDouble(voteIndex);
                movie.liked = true;
                movies.add(movie);
            }
        }
        return movies;
    }

}
