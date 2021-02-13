package com.mihneacristian.report_it;

import com.mihneacristian.report_it.data.remote.ApplicationAPI;
import com.mihneacristian.report_it.data.remote.RemoteDataSource;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

public class FetchRemoteSourceDataTest {

    @Test
    public void fetch_remote_data_source() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        ApplicationAPI api = new Retrofit.Builder()
                .baseUrl("https://report-it-86853-default-rtdb.europe-west1.firebasedatabase.app/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApplicationAPI.class);

        RemoteDataSource remoteDataSource = new RemoteDataSource(api);
        assertEquals(11, remoteDataSource.getIssues().size());
    }

    @Test
    public void fetch_wrong_remote_data_source() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        ApplicationAPI api = new Retrofit.Builder()
                .baseUrl("https://firebase.google.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApplicationAPI.class);

        RemoteDataSource remoteDataSource = new RemoteDataSource(api);
        assertEquals(0, remoteDataSource.getIssues().size());
    }
}
