package org.lebedko.device.monitor.service;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.lebedko.device.monitor.service.api.AccidentApi;

public class ApiBuilder {

    private static final String TAG = "ApiBuilder";

    private static volatile ApiBuilder apiBuilder;
    private Retrofit retrofit;

    public static AccidentApi accidentApi;

    private ApiBuilder(String host, Integer port) {
        retrofit = new Retrofit.Builder()
                .baseUrl(host + ":" + port)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accidentApi = retrofit.create(AccidentApi.class);
    }

    public static ApiBuilder init(String host, Integer port) {
        try {
            Log.i(TAG, "init");
            if (apiBuilder == null) {
                synchronized (ApiBuilder.class) {
                    if (apiBuilder == null) {
                        apiBuilder = new ApiBuilder(host, port);
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Error occurd during init ApiBuilder");
        }
        return apiBuilder;
    }

    public static ApiBuilder getInstance() {
        Log.i(TAG, "getInstance");
        if (apiBuilder == null) {
            throw new IllegalStateException("Class was not initialized. Please call init first.");
        }
        return apiBuilder;
    }
}
