package com.jpiser.hubclient.presentation.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ViewHelper {

    @Inject
    public ViewHelper() {
    }

    public float convertDpToPx(Context context, float dp) {
        if (context == null) return -1;
        Resources resource = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resource.getDisplayMetrics());
    }

    public float convertPxToDp(Context context, float px) {
        if (context == null) return -1;
        Resources resource = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resource.getDisplayMetrics());
    }
}
