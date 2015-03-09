package com.apisense.bee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.apisense.bee.R;
import com.apisense.bee.games.BeeGameActivity;
import com.apisense.bee.ui.fragment.ConnectivityFragment;
import com.apisense.bee.ui.fragment.NotFoundFragment;
import com.apisense.bee.ui.fragment.RegisterFragment;
import com.apisense.bee.ui.fragment.RewardFragment;
import com.apisense.bee.ui.fragment.SignInFragment;
import com.apisense.bee.ui.fragment.WhatFragment;
import com.viewpagerindicator.CirclePageIndicator;

import fr.inria.bsense.APISENSE;
import fr.inria.bsense.APISENSEListenner;
import fr.inria.bsense.service.BeeSenseServiceManager;

public class SlideshowActivity extends BeeGameActivity {

    /**
     * The number of pages (wizard steps) to show
     * Be careful if you are adding some slides, button listeners may not match
     */
    private static final int NUM_PAGES = 5;

    /* Page order */
    private final static int REWARD = 0;
    private final static int WHAT = 1;
    private final static int CONNECTIVITY = 2;
    private final static int SIGNIN = 3;
    private final static int REGISTER = 4;

    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        Toolbar toolbar = (Toolbar) findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // Check if we are coming from Anonymous HomeActivity
        try {
            Intent intent = getIntent(); // gets the previously created intent
            String destination = intent.getStringExtra("goTo");
            if (destination.equals("register")) {
                mPager.setCurrentItem(REGISTER); // Coming from an other activity
            } else if (destination.equals("signin")) {
                mPager.setCurrentItem(SIGNIN); // Coming from an other activity
            } else {
                mPager.setCurrentItem(WHAT); // Default
            }
        } catch (NullPointerException e) {
            mPager.setCurrentItem(WHAT); // Launching the app
        }

        //Bind the title indicator to the adapter
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);


        // Init APISENSE and check if already connected, just go to home Activity
        APISENSE.init(getApplicationContext(), new APISENSEListenner() {
            @Override
            public void onConnected(BeeSenseServiceManager beeSenseServiceManager) {
                if (APISENSE.apisServerService().isConnected()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Slide show adapter used to generate all slides
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case WHAT:
                    return new WhatFragment();
                case REWARD:
                    return new RewardFragment();
                case SIGNIN:
                    return new SignInFragment();
                case REGISTER:
                    return new RegisterFragment();
                case CONNECTIVITY:
                    return new ConnectivityFragment();
                default:
                    return new NotFoundFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "test";
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
