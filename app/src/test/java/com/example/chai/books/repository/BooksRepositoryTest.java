package com.example.chai.books.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.chai.books.models.ListingAPIResponse;
import com.example.chai.books.utils.Constants;
import com.example.chai.books.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class BooksRepositoryTest {

    BooksRepository repository;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initRep(){
        repository = BooksRepository.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(repository);
    }



    @Test
    public void getData() {
        try {
            ListingAPIResponse response =
                    LiveDataTestUtil.getValue(repository.getData());
            assertEquals(Constants.book_items_expected, response.getBooks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}