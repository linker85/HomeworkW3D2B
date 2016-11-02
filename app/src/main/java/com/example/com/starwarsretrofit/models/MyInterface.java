package com.example.com.starwarsretrofit.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by raul on 01/11/2016.
 */

public interface MyInterface {
    @GET("?format=json")
    Call<Example> retrieveCharactersByQuery(@Query("q") String q);
}
