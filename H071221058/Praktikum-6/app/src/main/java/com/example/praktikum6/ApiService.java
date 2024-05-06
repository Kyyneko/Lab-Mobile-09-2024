package com.example.praktikum6;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<UserResponse> getData(@Query("page") String page);

    @GET("api/users/{id}")
    Call<ProfileResponse> getUser(@Path("id") String id);
}