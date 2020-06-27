package com.app.plguinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.libimageloader.ImageLoadManager;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private String imgUrl = "http://04imgmini.eastday.com/mobile/20200627/20200627060410_ab8e5feb22bcbac83e103112314fcb25_2.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        ImageLoadManager.getInstance().displayImageForCircle(iv, imgUrl);

        FrameLayout group = findViewById(R.id.bg);
        ImageLoadManager.getInstance().displayImageForViewGroup(group, imgUrl);


    }
}
