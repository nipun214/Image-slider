package com.example.slider;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final Integer[] IMAGES = {R.drawable.abc, R.drawable.abc1, R.drawable.abc2, R.drawable.abc1};
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Integer> ImagesArray = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        init ();
    }

    private void init() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add ( IMAGES[i] );
        mPager = findViewById ( R.id.pager );
        mPager.setAdapter ( new SliderAdapterExample ( MainActivity.this, ImagesArray ) );

        com.viewpagerindicator.CirclePageIndicator indicator = findViewById ( R.id.indicator );

        indicator.setViewPager ( mPager );

        final float density = getResources ().getDisplayMetrics ().density;
        indicator.setRadius ( 5 * density );
        NUM_PAGES = IMAGES.length;
        final Handler handler = new Handler ();
        final Runnable Update = new Runnable () {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem ( currentPage++, true );
            }
        };
        Timer swipeTimer = new Timer ();
        swipeTimer.schedule ( new TimerTask () {
            @Override
            public void run() {
                handler.post ( Update );
            }
        }, 3000, 3000 );
            indicator.setOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {}
            @Override
            public void onPageScrollStateChanged(int pos) {}
        } );
    }
}