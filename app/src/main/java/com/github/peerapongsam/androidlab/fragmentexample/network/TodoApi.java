package com.github.peerapongsam.androidlab.fragmentexample.network;

import com.github.peerapongsam.androidlab.fragmentexample.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by peerapong on 5/22/17.
 */

public interface TodoApi {
    @GET("todos")
    Call<List<Todo>> getTodos();
}
