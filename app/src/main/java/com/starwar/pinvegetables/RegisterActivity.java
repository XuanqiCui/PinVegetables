package com.starwar.pinvegetables;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_jump_to_list;
    private EditText et_register_username;
    private TextView tv_register_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initView();
        btn_jump_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //拿数据，存到bmob数据库Guest表中
                String username = et_register_username.getText().toString();
                Guest guest = new Guest();
                guest.setGuestname(username);
                guest.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null){

                            //改本地缓存文件为false
                            SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("first_launch", false);
                            editor.apply();

                            //设置这侧注册状态为false，下次直接跳转到ListActivity
                            Intent intent = new Intent(RegisterActivity.this, ListActivity.class);
                            startActivity(intent);
                            finish();

                            //弹出提示
                            Toast.makeText(RegisterActivity.this,"done",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(RegisterActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });

//        MyDBOpenHelper helper = new MyDBOpenHelper(this);

    }

    private void initView() {
        btn_jump_to_list = findViewById(R.id.btn_jump_to_list);
        et_register_username = (EditText) findViewById(R.id.et_register_username);
        tv_register_username = (TextView) findViewById(R.id.tv_register_username);

    }
}