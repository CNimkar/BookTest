package com.example.chai.books.interfaces;

import com.example.chai.books.models.BookListing;
import com.example.chai.books.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BookkeepingService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET(Constants.LIST_BOOKS)
    Call<BookListing> listAllBooks();

}
