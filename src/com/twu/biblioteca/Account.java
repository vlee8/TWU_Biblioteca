package com.twu.biblioteca;

/**
 * Created by vlee on 12/08/2016.
 */
public class Account {

    private final String libraryNumber;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public Account(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
