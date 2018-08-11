package com.example.chai.books;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chai.books.adapters.BookListingAdapter;
import com.example.chai.books.models.Book;
import com.example.chai.books.models.ListingAPIResponse;
import com.example.chai.books.viewmodels.BooksListingViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksListingActivity extends AppCompatActivity {


    @BindView(R.id.booksList)
    RecyclerView booksList;

    BooksListingViewModel viewModel;
    BookListingAdapter adapter;

    List<Book> allBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_listing);

        ButterKnife.bind(this);

        initViewModel();
        initAdaptersAndObservers();

    }

    private void initViewModel() {
        viewModel = ViewModelProviders
                .of(this)
                .get(BooksListingViewModel.class);
    }

    private void initAdaptersAndObservers() {
        allBooks = new ArrayList<>();
        initAdapterWithNoData();
        subscribeToResponseObserver();
    }

    private void initAdapterWithNoData() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        booksList.setLayoutManager(layoutManager);
        adapter =
                new BookListingAdapter(allBooks, this);
        booksList.setAdapter(adapter);
    }

    private void loadDataWithSubscription(List<Book> books) {
        allBooks.clear();
        allBooks.addAll(books);
        adapter.notifyDataSetChanged();
    }

    private void subscribeToResponseObserver() {

        viewModel.getApiResponse().observe(this, new Observer<ListingAPIResponse>() {
            @Override
            public void onChanged(@Nullable ListingAPIResponse apiResponse) {
                switch (apiResponse.getResponseType()) {
                    case ListingAPIResponse.SUCCESSFUL_RESPONSE:
                        loadDataWithSubscription(apiResponse.getBooks());
                        break;
                    case ListingAPIResponse.REQUEST_ERROR_RESPONSE:
                    case ListingAPIResponse.THROWABLE_ERROR_RESPONSE:
                        displayNetworkingErrorToast();
                }
            }
        });


    }

    private void displayNetworkingErrorToast() {
        Toast.makeText(this,
                "Unable to get the list",
                Toast.LENGTH_LONG).show();
    }
}
