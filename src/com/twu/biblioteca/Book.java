package com.twu.biblioteca;

/**
 * Created by vlee on 12/08/2016.
 */
public class Book {


    private final String title;
    private final String author;
    private final int year;
    private boolean checkedOut;
    private Account checkedOutBy;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setCheckedOut(boolean isCheckedOut) {
        checkedOut = isCheckedOut;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public Account getCheckedOutBy() {
        return checkedOutBy;
    }


}
