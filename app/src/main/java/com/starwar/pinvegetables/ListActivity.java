package com.starwar.pinvegetables;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    private ListView lv_now_vegetables;
    private FloatingActionButton btn_pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        initView();

        //跳转去填写要拼的菜的页面
        btn_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddVegetablesActivity.class);
                startActivity(intent);
            }
        });

        lv_now_vegetables.setOnItemClickListener(((parent, view, position, id) -> {
            Intent intent = new Intent();
            startActivity(intent);
        }));
    }

    private void initView() {
        lv_now_vegetables = findViewById(R.id.lv_now_vegetables);
        btn_pin = (FloatingActionButton) findViewById(R.id.btn_pin);
        ArrayList<VegetableListInfo> vegetableList = new ArrayList<>();
        vegetableList.add(new VegetableListInfo("Tomato", "Fresh"));
        vegetableList.add(new VegetableListInfo("Potato", "Fresh"));
        vegetableList.add(new VegetableListInfo("Carrot", "Fresh"));

        NowVegetablesAdapter adapter = new NowVegetablesAdapter(this, vegetableList);
        lv_now_vegetables.setAdapter(adapter);
    }


}