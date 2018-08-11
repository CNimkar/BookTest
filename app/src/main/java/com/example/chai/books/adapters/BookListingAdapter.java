package com.example.chai.books.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.chai.books.R;
import com.example.chai.books.models.Book;
import com.example.chai.books.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListingAdapter extends RecyclerView.Adapter<BookListingAdapter.BookDetailViewHolder> {

    List<Book> books;
    Context context;

    public BookListingAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BookDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new BookDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookDetailViewHolder holder, int position) {
        Book book = books.get(position);
        showLogo(book.getImageUrl(), holder.bookImage);
        Log.d("ImageName", book.getImageUrl() + "");
        holder.bookName.setText(book.getTitle());
    }


    private void showLogo(String url, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bookImage)
        ImageView bookImage;

        @BindView(R.id.bookName)
        TextView bookName;


        public BookDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
