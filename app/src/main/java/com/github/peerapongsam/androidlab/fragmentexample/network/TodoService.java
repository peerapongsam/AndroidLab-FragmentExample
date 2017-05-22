package com.github.peerapongsam.androidlab.fragmentexample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peerapong on 5/22/17.
 */

public class TodoService {
    private static TodoService instance;
    private TodoApi api;

    public static TodoService getInstance() {
        if (instance == null) {
            instance = new TodoService();
        }
        return instance;
    }

    private TodoService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(TodoApi.class);
    }

    public TodoApi getApi() {
        return api;
    }
}
