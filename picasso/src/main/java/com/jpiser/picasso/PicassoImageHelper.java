package com.jpiser.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.jpiser.hubclient.common.imaging.ImageHelper;
import com.squareup.picasso.Picasso;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class PicassoImageHelper implements ImageHelper {

    private Picasso picasso;

    public PicassoImageHelper(Context context, boolean indicatorsEnabled) {
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(indicatorsEnabled);
    }
    @Override
    public void loadImage(Object imageView, String url) {

        if(imageView instanceof ImageView &&
                url != null){

            picasso.load(url).into((ImageView) imageView);
        }
    }
}
