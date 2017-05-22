package com.github.peerapongsam.androidlab.fragmentexample.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.peerapongsam.androidlab.fragmentexample.R;
import com.github.peerapongsam.androidlab.fragmentexample.adapter.FooAdapter;
import com.github.peerapongsam.androidlab.fragmentexample.model.Todo;
import com.github.peerapongsam.androidlab.fragmentexample.network.TodoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by peerapong on 5/19/17.
 */

public class FooFragment extends Fragment {
    private static final String TAG = "FooFragment";
    private static final String KEY_FOOS = "key_foos";
    private RecyclerView rvFoos;
    private FooAdapter fooAdapter;

    public static FooFragment newInstance() {
        Bundle args = new Bundle();
        FooFragment fragment = new FooFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FooFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupInstance();
        setupView();
    }

    private void bindView(View view) {
        rvFoos = (RecyclerView) view.findViewById(R.id.rv_foos);
    }

    private void setupInstance() {
        fooAdapter = new FooAdapter();
    }

    private void setupView() {
        fooAdapter.setListener(onFooItemClickListener());

        rvFoos.setAdapter(fooAdapter);
        rvFoos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFoos.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated() called with: savedInstanceState = [" + savedInstanceState + "]");
        if (savedInstanceState == null) {
            callService();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void callService() {
        TodoService.getInstance().getApi()
                .getTodos()
                .enqueue(new Callback<List<Todo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                        if (response.isSuccessful()) {
                            List<String> foos = new ArrayList<>();
                            if (response.body() != null) {
                                for (Todo todo : response.body()) {
                                    foos.add(todo.toString());
                                }
                            }
                            fooAdapter.setFoos(foos);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
        List<String> foos = savedInstanceState.getStringArrayList(KEY_FOOS);
        fooAdapter.setFoos(foos);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        outState.putStringArrayList(KEY_FOOS, (ArrayList<String>) fooAdapter.getFoos());
    }

    @NonNull
    private FooAdapter.OnFooItemClickListener onFooItemClickListener() {
        return new FooAdapter.OnFooItemClickListener() {
            @Override
            public void onFooItemClick(String foo) {
                ((OnFooClickListener) getActivity()).onFooClick(foo);
            }
        };
    }

    public interface OnFooClickListener {
        void onFooClick(String foo);
    }
}
