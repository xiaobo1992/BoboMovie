package com.bobo.normalman.bobomovie.Util;

import android.content.Context;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class ImageUtil {
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static String IMAGE_SIZE = "w500";

    public static void loadImage(Context context, ImageView imageView, String poster_path) {
        String url = IMAGE_BASE_URL + IMAGE_SIZE + "/" + poster_path;
        try {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_local_movies_black_24px)
                    .error(R.drawable.ic_local_movies_black_24px)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
