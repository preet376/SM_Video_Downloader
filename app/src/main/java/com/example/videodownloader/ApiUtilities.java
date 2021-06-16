package com.example.videodownloader;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities
{
    public static Retrofit retrofit = null;

    private static final String BASE_URL = "https://instagram-unofficial-api.herokuapp.com/unofficial/api/";

    public static ApiInterface getApiInterface()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
