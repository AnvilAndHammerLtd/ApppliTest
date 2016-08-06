package com.kyriakosalexandrou.ampersandtest.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.kyriakosalexandrou.ampersandtest.R;
import com.kyriakosalexandrou.ampersandtest.ui.fragments.NewsFeedFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goToNewsFeedFragment();
    }

    private void goToNewsFeedFragment() {
        FragmentManager fm = getSupportFragmentManager();
        NewsFeedFragment fragment = new NewsFeedFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, NewsFeedFragment.TAG);
        ft.commit();
        fm.executePendingTransactions();
    }
}
