package com.example.taobao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerOrderAdapter;
import bean.Info;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tv_pay;
    private List<Info> infoList;
    private RecyclerOrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tv_pay.setText("支付(￥"+bundle.getDouble("jine")+")");
        infoList= (ArrayList) bundle.getSerializable("list");
        adapter=new RecyclerOrderAdapter(infoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        recyclerView.setAdapter(adapter);
    }
    public void init(){
        recyclerView=(RecyclerView) findViewById(R.id.rv3);
        tv_pay= (TextView) findViewById(R.id.pay);
    }
}