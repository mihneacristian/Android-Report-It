package com.mihneacristian.report_it.data.remote;

import com.mihneacristian.report_it.data.dto.IssuesDTO;
import com.mihneacristian.report_it.domain.entity.IssuesEntity;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApplicationAPI {
    String BASE_URL = "https://report-it-86853-default-rtdb.europe-west1.firebasedatabase.app/";

    @GET("issues.json")
    @Headers("Content-Type: application/json")
    Call<List<IssuesDTO>> getIssues();

    static ApplicationAPI createAPI() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApplicationAPI.class);
    }
}
