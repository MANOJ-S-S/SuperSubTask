package com.test.supersub;

import com.test.supersub.models.MainResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitAPI {

    @GET("drill")
    Call<MainResponse> getResponse();
}
