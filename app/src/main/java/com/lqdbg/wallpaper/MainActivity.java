package com.lqdbg.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.lqdbg.wallpaper.utilt.SbTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SbTools.with(this)
                .setSbFontColorBlack()
                .setSTSBgColor(Color.TRANSPARENT)
                .init();
    }
}