package com.example.prateek.Miniproject;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager vp = (ViewPager) findViewById(R.id.vp);


        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());


        vp.setAdapter(adapter);


        TabLayout tl = (TabLayout) findViewById(R.id.tl);


        tl.setSelectedTabIndicatorColor(Color.BLACK);
                tl.setupWithViewPager(vp);


    }
}
