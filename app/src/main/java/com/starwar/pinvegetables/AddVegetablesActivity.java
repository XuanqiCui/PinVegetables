package com.starwar.pinvegetables;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddVegetablesActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton flbtn_save_data;
    private EditText et_add_vegetables_want_vegetables;
    private EditText et_add_vegetables_price_vegetables;
    private EditText et_add_vegetables_weight_vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_vegetables);

        //初始化bmob数据库
        initView();



        flbtn_save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vegetableName = et_add_vegetables_want_vegetables.getText().toString();
                String vegetablePrice = et_add_vegetables_price_vegetables.getText().toString();
                String vegetableWeight = et_add_vegetables_weight_vegetables.getText().toString();

                Vegetables vegetables = new Vegetables();
                vegetables.setVegename(vegetableName);
                vegetables.setVegeprice(vegetablePrice);
                vegetables.setVegeweight(vegetableWeight);
                vegetables.setVegestatus(false);
                vegetables.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null){
                            Toast.makeText(AddVegetablesActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });
    }

    private void initView() {
        flbtn_save_data = (ExtendedFloatingActionButton) findViewById(R.id.flbtn_save_data);
        et_add_vegetables_want_vegetables = (EditText) findViewById(R.id.et_add_vegetables_want_vegetables);
        et_add_vegetables_price_vegetables = (EditText) findViewById(R.id.et_add_vegetables_price_vegetables);
        et_add_vegetables_weight_vegetables = (EditText) findViewById(R.id.et_add_vegetables_weight_vegetables);


    }


}