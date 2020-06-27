package com.app.plguinapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
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

    /**
     * 跳转到web页面UI
     * @param view
     */
    public void toWebPage(View view) {
        String url = "/webPage/web_activity";
        ARouter.getInstance()
                .build(url)
                .withString("key", "this is a good param")
                .navigation();
    }
}
