package com.github.peerapongsam.androidlab.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, FooFragment.newInstance(), "FooFragment")
//                    .commit();
            openFragment(R.id.fragment_container, FooFragment.newInstance());
        }
    }

    @Override
    public void onFooClick(String foo) {
        openFragment(R.id.fragment_container, BarFragment.newInstance(foo));
    }

    private void openFragment(int containerView, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment oldFragment = fm.findFragmentById(containerView);
        FragmentTransaction ft = fm.beginTransaction();
        if (oldFragment != null) {
            ft.hide(oldFragment);
        }
        ft.add(containerView, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
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
