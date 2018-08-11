package com.example.chai.books.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.books.models.ListingAPIResponse;
import com.example.chai.books.repository.BooksRepository;

public class BooksListingViewModel extends ViewModel {
    public MutableLiveData<ListingAPIResponse> booksObservable;
    BooksRepository repository = BooksRepository.getInstance();

    public BooksListingViewModel() {
        booksObservable = new MutableLiveData<>();
    }

    public LiveData<ListingAPIResponse> getApiResponse() {
        if (booksObservable.getValue() == null ||
                booksObservable.getValue().getResponseType()
                        != ListingAPIResponse.SUCCESSFUL_RESPONSE) {
            getData();
        }

        return booksObservable;
    }


    public void getData() {
        booksObservable = repository.getData();
    }

}
