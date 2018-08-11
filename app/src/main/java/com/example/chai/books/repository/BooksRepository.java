package com.example.chai.books.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.chai.books.interfaces.BookkeepingService;
import com.example.chai.books.models.BookListing;
import com.example.chai.books.models.ListingAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksRepository {
    static BooksRepository booksRepository;
    MutableLiveData<ListingAPIResponse> listingAPIResponse =
            new MutableLiveData<>();
    BookkeepingService bookkeepingService;

    private BooksRepository() {
        bookkeepingService = BookkeepingService.retrofit.create(BookkeepingService.class);
    }

    public static synchronized BooksRepository getInstance() {
        if (booksRepository == null) {
            booksRepository = new BooksRepository();
        }
        return booksRepository;
    }

    public MutableLiveData<ListingAPIResponse> getData() {
        BookkeepingService webService =
                BookkeepingService.retrofit.create(BookkeepingService.class);
        Call<BookListing> call;

        call = webService.listAllBooks();

        call.enqueue(new Callback<BookListing>() {
            @Override
            public void onResponse(Call<BookListing> call,
                                   Response<BookListing> response) {
                if (response.isSuccessful()) {
                    listingAPIResponse.postValue(
                            new ListingAPIResponse(
                                    response.body()));
                } else {
                    int statusCode = response.code();
                    listingAPIResponse.postValue(new ListingAPIResponse(statusCode));
                }
            }

            @Override
            public void onFailure(Call<BookListing> call, Throwable t) {
                listingAPIResponse.postValue(new ListingAPIResponse(t));
            }


        });

        return listingAPIResponse;

    }

}
