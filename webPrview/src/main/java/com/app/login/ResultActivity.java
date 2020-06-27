package com.app.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.app.libbase.ImageLoad.ImageLoadService;

@Route(path = "/webPage/web_activity")
public class ResultActivity extends AppCompatActivity {

    @Autowired(name = "key")
    protected String parmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ARouter.getInstance().inject(this);

//        Intent intent = getIntent();
//        String value = intent.getStringExtra("key");

        if (parmas != null) {
            Toast.makeText(this, "收到参数：" + parmas, Toast.LENGTH_SHORT).show();
        }

    }

    @Autowired(name = "/imageLoad/imageLoad_service")
    protected ImageLoadService imageLoadService;

    public void loadImage(View view) {
        ARouter.getInstance().inject(this);
        ImageView iv = findViewById(R.id.iv);
        String imgUrl = "http://02imgmini.eastday.com/mobile/20200626/20200626102013_fef240ebdd6f6399f0592aaf1ff3ad46_1.jpeg";
        imageLoadService.displayImage(iv, imgUrl);
    }
}
