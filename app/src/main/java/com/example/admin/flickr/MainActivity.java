package com.example.admin.flickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://www.flickr.com/services/feeds/";
    ArrayList<Feed> feeds = new ArrayList<>();
    Feed f;
    ArrayList<Item> items = new ArrayList<>();
    private RecyclerView recyclerView;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            RemoteDataSource.getBooks().observeOn(AndroidSchedulers.mainThread()) //give me results on main thread
                    .subscribeOn(Schedulers.io()) //this is a different thread
                    .subscribe(new Observer<Feed>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //view.showProgress("downloading repos");
                        }

                        @Override
                        public void onNext(Feed books) {
                            // Log.d("BookListPresenter", "onNext: " + books.size());
                            Log.d(TAG, "onNext: got the feeds!");
                            //items.addAll(books.getItems());
                            f = books;
                           Log.d(TAG, "onNext: feeds items size is " + f.getItems().size());
                            //view.setBooks(bookList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: we got an error getting the feeds!");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            //view.showProgress("check your logs");
                            // view.setBooks(bookList);
                            //view.showProgress("Downloaded all repos");
                            items.addAll(f.getItems());
                            Log.d(TAG, "onNext: feeds items size is " + f.getItems().size());
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                            recyclerView = findViewById(R.id.rvMain);
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            recyclerView.setLayoutManager(layoutManager); //need layoutManager
                            recyclerView.setItemAnimator(itemAnimator); //don't need this but it allows animation for each item
                             RecyclerAdapter ra = new RecyclerAdapter(items);
                            recyclerView.setAdapter(ra); //need adapter
                        }
                    });
        Log.d(TAG, "onCreate: on create is happening...");





    }
}
