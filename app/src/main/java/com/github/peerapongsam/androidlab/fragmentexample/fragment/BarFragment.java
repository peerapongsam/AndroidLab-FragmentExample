package com.github.peerapongsam.androidlab.fragmentexample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.peerapongsam.androidlab.fragmentexample.R;

/**
 * Created by peerapong on 5/19/17.
 */

public class BarFragment extends Fragment {
    private static final String ARGUMENT_FOO = "argument_foo";
    public static final String KEY_FOO = "key_foo";

    private String foo;
    private TextView tvBar;

    public static BarFragment newInstance(String commitUrl) {
        Bundle args = new Bundle();
        BarFragment fragment = new BarFragment();
        args.putString(ARGUMENT_FOO, commitUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public BarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            if (getArguments() != null) {
                Bundle arguments = getArguments();
                foo = arguments.getString(ARGUMENT_FOO);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void bindView(View view) {
        tvBar = (TextView) view.findViewById(R.id.tv_bar);
    }

    private void setupView() {
        tvBar.setText(foo);
    }
}
