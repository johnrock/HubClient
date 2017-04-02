package com.jpiser.hubclient.domain.helpers;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface ImageHelper {

    /**
     * Load an image into the given imageView either from the network or the cache, as decided by the image cache implementation
     * @param imageView
     * @param url
     */
    void loadImage(Object imageView, String url);
}
