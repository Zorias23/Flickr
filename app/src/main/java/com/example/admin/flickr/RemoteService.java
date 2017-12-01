package com.example.admin.flickr;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Admin on 11/29/2017.
 */

public interface RemoteService {

    @GET("photos_public.gne?tag=kitten&format=json&nojsoncallback=1")
    Observable<Feed> getBooks();
}
