package com.example.chai.books.viewmodels;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.chai.books.models.Book;
import com.example.chai.books.models.ListingAPIResponse;
import com.example.chai.books.repository.BooksRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BooksListingViewModelTest {

    BooksRepository repository;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initRep(){
        repository = BooksRepository.getInstance();
    }

    @Test
    public void getApiResponse() {
        BooksListingViewModel viewModel = Mockito.spy(BooksListingViewModel.class);
        viewModel.getApiResponse();
        verify(viewModel, times(1)).getData();
    }

    @Test
    public void getApiResponseNotCalledObservableNotNull() {
        MutableLiveData<ListingAPIResponse> apiResponse = new MutableLiveData<>();
        apiResponse.postValue(new ListingAPIResponse(new ArrayList<Book>()));
        BooksListingViewModel viewModel = Mockito.spy(BooksListingViewModel.class);
        viewModel.booksObservable = apiResponse;
        viewModel.getApiResponse();
        verify(viewModel, times(0)).getData();

    }

    @Test
    public void getApiResponseCalledEarlierFailed() {
        MutableLiveData<ListingAPIResponse> apiResponse = new MutableLiveData<>();
        ListingAPIResponse response = new ListingAPIResponse(new ArrayList<Book>());
        response.setResponseType(ListingAPIResponse.REQUEST_ERROR_RESPONSE);
        apiResponse.postValue(response);
        BooksListingViewModel viewModel = Mockito.spy(BooksListingViewModel.class);
        viewModel.booksObservable = apiResponse;
        viewModel.getApiResponse();
        verify(viewModel, times(1)).getData();

    }
}