package com.jpiser.hubclient.data.logging;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface LogHelper {

    void debug(String logtag, String message);
    void error(String logtag, String message);
    void info(String logtag, String message);
    void verbose(String logtag, String message);
    boolean debugMode() ;

}
