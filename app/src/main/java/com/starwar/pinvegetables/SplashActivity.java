package com.starwar.pinvegetables;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import cn.bmob.v3.Bmob;

public class SplashActivity extends AppCompatActivity {

    private ImageView iv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        //创建本地缓存文件AppPrefs
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean("first_launch", true);
        initVew();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirstLaunch){
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, ListActivity.class));
                }
                finish();
            }
        },2000);


    }

    private void initVew() {
        Bmob.initialize(this,"f3656b69beef4b62f81b5a781bf731fa");
        iv_splash = (ImageView) findViewById(R.id.iv_splash_img);
    }
}