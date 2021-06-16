package com.example.videodownloader;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("video")

    Call<InstaModel> getInfo(
        @Query("link") String link);

}
