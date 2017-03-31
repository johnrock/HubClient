package com.jpiser.hubclient.data.models.shared;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class Credentials {

    private final String username;
    private final String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean readOnly(){
        return password == null;
    }

    public static Credentials empty(){
        return new Credentials(null, null);
    }
}
