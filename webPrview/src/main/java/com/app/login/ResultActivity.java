package com.app.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

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
}
