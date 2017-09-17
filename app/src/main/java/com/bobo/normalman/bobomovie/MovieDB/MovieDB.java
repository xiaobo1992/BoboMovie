package com.bobo.normalman.bobomovie.MovieDB;

import com.bobo.normalman.bobomovie.Model.Movie;
import com.bobo.normalman.bobomovie.Model.Result;
import com.bobo.normalman.bobomovie.Model.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class MovieDB {
    private static String API_KEY = "8a526ebf41d4e3192ffa6fe89d8f6d17";
    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static String BASE_MOVIE_URL = BASE_URL + "movie/";

    private static Response makeGetRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    private static Request buildGetRequest(String url) {
        return new Request.Builder().url(url).build();
    }

    private static <T> T parseResponse(Response response, TypeToken<T> token) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(response.body().string(), token.getType());
    }

    public static List<Movie> getMovies(String type, int page) throws IOException {
        String url = BASE_MOVIE_URL + type + "?" + "api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        Result result = parseResponse(makeGetRequest(request), new TypeToken<Result>() {
        });
        return result.results;
    }

    public static Movie getMovie(String movie_id) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        return parseResponse(makeGetRequest(request), new TypeToken<Movie>() {
        });
    }

    public static List<Review> getReviews(String movie_id) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/reviews?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        return parseResponse(makeGetRequest(request), new TypeToken<List<Review>>() {
        });
    }
}
