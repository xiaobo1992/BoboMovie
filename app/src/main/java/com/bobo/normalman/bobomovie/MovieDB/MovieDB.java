package com.bobo.normalman.bobomovie.MovieDB;

import android.util.Log;

import com.bobo.normalman.bobomovie.BuildConfig;
import com.bobo.normalman.bobomovie.model.Artist;
import com.bobo.normalman.bobomovie.model.ArtistResult;
import com.bobo.normalman.bobomovie.model.Credits;
import com.bobo.normalman.bobomovie.model.Movie;
import com.bobo.normalman.bobomovie.model.MovieCredits;
import com.bobo.normalman.bobomovie.model.MovieResult;
import com.bobo.normalman.bobomovie.model.Review;
import com.bobo.normalman.bobomovie.model.ReviewResult;
import com.bobo.normalman.bobomovie.model.TV;
import com.bobo.normalman.bobomovie.model.TVCredits;
import com.bobo.normalman.bobomovie.model.TVResult;
import com.bobo.normalman.bobomovie.model.Video;
import com.bobo.normalman.bobomovie.model.VideoResult;
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
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String BASE_MOVIE_URL = BASE_URL + "movie/";
    private static final String BASE_TV_URL = BASE_URL + "tv/";
    private static final String BASE_PEOPLE_URL = BASE_URL + "person/";
    private static final String SIMILAR = "similar";
    private static final String RECOMMEND = "recommendations";
    private static final String CREDITS = "credits";
    private static final String MOVIE_CREDITS = "movie_" + CREDITS;
    private static final String TV_CREDITS = "tv_" + CREDITS;

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
        MovieResult movieResult = parseResponse(makeGetRequest(request), new TypeToken<MovieResult>() {
        });
        return movieResult.results;
    }

    public static Movie getMovie(String movie_id) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        return parseResponse(makeGetRequest(request), new TypeToken<Movie>() {
        });
    }

    public static List<Review> getReviews(String movie_id, int page) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/reviews?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        ReviewResult result = parseResponse(makeGetRequest(request), new TypeToken<ReviewResult>() {
        });
        return result.results;
    }

    public static List<Video> getVideos(String movie_id, int page) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/videos?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        VideoResult result = parseResponse(makeGetRequest(request), new TypeToken<VideoResult>() {
        });
        return result.results;
    }

    public static List<TV> getTVs(String type, int page) throws IOException {
        String url = BASE_TV_URL + type + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        TVResult result = parseResponse(makeGetRequest(request), new TypeToken<TVResult>() {
        });
        return result.results;
    }

    public static List<Artist> getArtists(String type, int page) throws IOException {
        String url = BASE_PEOPLE_URL + type + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        ArtistResult result = parseResponse(makeGetRequest(request), new TypeToken<ArtistResult>() {
        });
        return result.results;
    }

    public static List<Movie> getSimilarMovie(String movie_id, int page) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/" + SIMILAR + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        MovieResult result = parseResponse(makeGetRequest(request), new TypeToken<MovieResult>() {
        });
        return result.results;
    }

    public static List<TV> getSimilarTV(String tv_id, int page) throws IOException {
        String url = BASE_TV_URL + tv_id + "/" + SIMILAR + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        TVResult result = parseResponse(makeGetRequest(request), new TypeToken<TVResult>() {
        });
        Log.d("tv url", url);
        return result.results;
    }

    public static List<Movie> getRecommendMovie(String movie_id, int page) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/" + RECOMMEND + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        MovieResult result = parseResponse(makeGetRequest(request), new TypeToken<MovieResult>() {
        });
        return result.results;
    }

    public static List<TV> getRecommendTV(String tv_id, int page) throws IOException {
        String url = BASE_TV_URL + tv_id + "/" + RECOMMEND + "?api_key=" + API_KEY + "&page=" + page;
        Request request = buildGetRequest(url);
        TVResult result = parseResponse(makeGetRequest(request), new TypeToken<TVResult>() {
        });
        return result.results;
    }

    public static List<Artist> getMovieCast(String movie_id) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/" + CREDITS + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        Credits credits = parseResponse(makeGetRequest(request), new TypeToken<Credits>() {
        });
        return credits.cast;
    }

    public static List<Artist> getMovieCrew(String movie_id) throws IOException {
        String url = BASE_MOVIE_URL + movie_id + "/" + CREDITS + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        Credits credits = parseResponse(makeGetRequest(request), new TypeToken<Credits>() {
        });
        return credits.crew;
    }

    public static List<Artist> getTVCast(String tv_id) throws IOException {
        String url = BASE_TV_URL + tv_id + "/" + CREDITS + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        Credits credits = parseResponse(makeGetRequest(request), new TypeToken<Credits>() {
        });
        return credits.cast;
    }

    public static List<Movie> getMovieCredits(String person_id) throws IOException {
        String url = BASE_PEOPLE_URL + person_id + "/" + MOVIE_CREDITS + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        MovieCredits credits = parseResponse(makeGetRequest(request), new TypeToken<MovieCredits>() {
        });
        return credits.cast;
    }

    public static List<TV> getTVCredits(String person_id) throws IOException {
        String url = BASE_PEOPLE_URL + person_id + "/" + TV_CREDITS + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        TVCredits credits = parseResponse(makeGetRequest(request), new TypeToken<TVCredits>() {
        });
        return credits.cast;
    }

    public static Artist getArtistDetail(String person_id) throws IOException {
        String url = BASE_PEOPLE_URL + person_id + "?api_key=" + API_KEY;
        Request request = buildGetRequest(url);
        Artist artist = parseResponse(makeGetRequest(request), new TypeToken<Artist>(){});
        return artist;
    }
}
