package com.twu.biblioteca;

/**
 * Created by vlee on 12/08/2016.
 */
public class Account {

    private final String libraryNumber;
    private final String password;

    public Account(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }
}
