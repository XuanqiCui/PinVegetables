package com.starwar.pinvegetables;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    private ListView lv_now_vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        initView();

    }

    private void initView() {
        lv_now_vegetables = findViewById(R.id.lv_now_vegetables);

        ArrayList<VegetableListInfo> vegetableList = new ArrayList<>();
        vegetableList.add(new VegetableListInfo("Tomato", "Fresh"));
        vegetableList.add(new VegetableListInfo("Potato", "Fresh"));
        vegetableList.add(new VegetableListInfo("Carrot", "Fresh"));

        NowVegetablesAdapter adapter = new NowVegetablesAdapter(this, vegetableList);
        lv_now_vegetables.setAdapter(adapter);
    }


}