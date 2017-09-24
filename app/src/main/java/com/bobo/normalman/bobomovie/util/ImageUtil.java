package com.bobo.normalman.bobomovie.util;

import android.content.Context;
import android.widget.ImageView;

import com.bobo.normalman.bobomovie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class ImageUtil {

    public static void loadImage(Context context, ImageView imageView, String poster_path) {
        String IMAGE_SIZE = "w500";
        String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
        String url = IMAGE_BASE_URL + IMAGE_SIZE + "/" + poster_path;
        try {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
