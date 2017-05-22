package com.github.peerapongsam.androidlab.fragmentexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.peerapongsam.androidlab.fragmentexample.fragment.BarFragment;
import com.github.peerapongsam.androidlab.fragmentexample.fragment.FooFragment;

/**
 * Created by peerapong on 5/19/17.
 */

public class MainActivity extends AppCompatActivity implements FooFragment.OnFooClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, FooFragment.newInstance())
                    .addToBackStack("FooFragment")
                    .commit();
        }
    }

    @Override
    public void onFooClick(String foo) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BarFragment.newInstance(foo))
                .addToBackStack("BarFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
