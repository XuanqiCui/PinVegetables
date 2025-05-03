package com.starwar.pinvegetables;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class ListActivity extends AppCompatActivity {


    private ListView lv_now_vegetables;
    private FloatingActionButton btn_pin;
    private LinearLayout ll_title;
    private TextView tv_welcome;
    private EditText et_guestname;
    private FloatingActionButton btn_list_refresh;


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
        //刷新页面
        btn_list_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询数据并放到列表上
                String bql = "select vegename,vegestatus from Vegetables";
                BmobQuery<Vegetables> bmobQuery = new BmobQuery<>();
                bmobQuery.doSQLQuery(bql, new SQLQueryListener<Vegetables>() {
                    @Override
                    public void done(BmobQueryResult<Vegetables> bmobQueryResult, BmobException e) {
                        List<Vegetables> results = bmobQueryResult.getResults();
                        if (results != null && !results.isEmpty()){
                            NowVegetablesAdapter adapter = new NowVegetablesAdapter(ListActivity.this, results);
                            lv_now_vegetables.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(ListActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        lv_now_vegetables.setOnItemClickListener(((parent, view, position, id) -> {
            TextView tvId = view.findViewById(R.id.tv_item_title_context);
            String item = tvId.getText().toString();
//            Log.d("点击项", "你点击的是：" + item);
            Intent intent = new Intent(this, ListDetailActivity.class);
            intent.putExtra("vegename", item);
            startActivity(intent);
        }));
    }


    private void initView() {
        lv_now_vegetables = findViewById(R.id.lv_now_vegetables);
        btn_pin = (FloatingActionButton) findViewById(R.id.btn_pin);
        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        tv_welcome = (TextView) findViewById(R.id.tv_welcome);
        et_guestname = (EditText) findViewById(R.id.et_guestname);
        btn_list_refresh = (FloatingActionButton) findViewById(R.id.btn_list_refresh);

        //读取本地的sharepreference中的username,并把username字段放到Editext上
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String username = prefs.getString("username", "");
        et_guestname.setText(username);

        //查询数据并放到列表上
        String bql = "select vegename,vegestatus from Vegetables";
        BmobQuery<Vegetables> bmobQuery = new BmobQuery<>();
        bmobQuery.doSQLQuery(bql, new SQLQueryListener<Vegetables>() {
            @Override
            public void done(BmobQueryResult<Vegetables> bmobQueryResult, BmobException e) {
                List<Vegetables> results = bmobQueryResult.getResults();
                if (results != null && !results.isEmpty()){
                    NowVegetablesAdapter adapter = new NowVegetablesAdapter(ListActivity.this, results);
                    lv_now_vegetables.setAdapter(adapter);
                }
                else {
                    Toast.makeText(ListActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}