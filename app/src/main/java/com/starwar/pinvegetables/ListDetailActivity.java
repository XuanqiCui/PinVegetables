package com.starwar.pinvegetables;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

public class ListDetailActivity extends AppCompatActivity {

    private EditText et_detail_now_vegetables;
    private ListView lv_detail_now_people;
    private Button btn_detail_pin;
    private Button btn_refresh;
    private String vegename;
    private int nowstatus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_detail);

        initView();
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String username = prefs.getString("username", "");
//        Toast.makeText(ListDetailActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();

        btn_detail_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<PinGroup> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("vegename", vegename); // 添加条件，查询指定蔬菜名称的记录"
                bmobQuery.findObjects(new FindListener<PinGroup>() {
                    @Override
                    public void done(List<PinGroup> list, BmobException e) {

                        if (e == null){
                            for (PinGroup pinGroup : list) {
                                if (pinGroup.getUsername().equals(username)) {
                                    Toast.makeText(ListDetailActivity.this, "你已经加入过该拼团了", Toast.LENGTH_SHORT).show();
                                    nowstatus = 0;
                                    return;
                                }
                            }
                        }

                        if (nowstatus == 1){
                            PinGroup group = new PinGroup();
                            group.setUsername(username);
                            group.setVegename(et_detail_now_vegetables.getText().toString());
                            group.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null){
                                        Toast.makeText(ListDetailActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }


                    }
                });
                
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bql = "select vegename,username from PinGroup where vegename = ?";
                BmobQuery<PinGroup> bmobQuery = new BmobQuery<>();
                bmobQuery.doSQLQuery(bql, new SQLQueryListener<PinGroup>() {
                    @Override
                    public void done(BmobQueryResult<PinGroup> bmobQueryResult, BmobException e) {
                        List<PinGroup> results = bmobQueryResult.getResults();
                        if (results != null && !results.isEmpty()){
                            PinGroupAdapter adapter = new PinGroupAdapter(ListDetailActivity.this, results);
                            lv_detail_now_people.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(ListDetailActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                },vegename);
            }
        });


    }


    private void initView() {
        et_detail_now_vegetables = (EditText) findViewById(R.id.et_detail_now_vegetables);
        lv_detail_now_people = (ListView) findViewById(R.id.lv_detail_now_people);
        btn_detail_pin = (Button) findViewById(R.id.btn_detail_pin);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);

        //获取intent，并把值放到控件上
        Intent intent = getIntent();
        vegename = intent.getStringExtra("vegename");
        et_detail_now_vegetables.setText(vegename);


    }


}