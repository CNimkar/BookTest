package com.example.chai.books.models;

import java.util.List;

public class ListingAPIResponse {

    public static final int SUCCESSFUL_RESPONSE = 100;
    public static final int REQUEST_ERROR_RESPONSE = 101;
    public static final int THROWABLE_ERROR_RESPONSE = 102;

    private List<Book> books;
    private int requestError = 0;
    private Throwable error;
    private int responseType;

    public ListingAPIResponse(List<Book> books) {
        setResponseType(SUCCESSFUL_RESPONSE);
        this.books = books;
        this.error = null;
    }

    public ListingAPIResponse(int requestError) {
        setResponseType(REQUEST_ERROR_RESPONSE);
        this.requestError = requestError;
        this.error = null;
        this.books = null;
    }

    public ListingAPIResponse(Throwable error) {
        setResponseType(THROWABLE_ERROR_RESPONSE);
        this.error = error;
        this.books = null;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setDrinks(List<Book> drinks) {
        this.books = books;
    }

    public int getRequestError() {
        return requestError;
    }

    public void setRequestError(int requestError) {
        this.requestError = requestError;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public int getResponseType() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }
}
