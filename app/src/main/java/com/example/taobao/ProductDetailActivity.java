package com.example.taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import bean.Info;
import bean.ProductInfo;
import dao.InfoDao;

public class ProductDetailActivity extends AppCompatActivity {
    TextView tv_price, tv_type, tv_name, tv_sale, tv_add;
    ImageView iv_product;
    private InfoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        dao = new InfoDao(ProductDetailActivity.this);
        init();
        Intent intent = getIntent();
        ProductInfo info = (ProductInfo) intent.getSerializableExtra("product");
        Bundle bundle = intent.getExtras();
        int i = bundle.getInt("id");
        tv_price.setText("￥" + info.getPrice());
        tv_type.setText(info.getType());
        tv_name.setText(info.getName());
        tv_sale.setText("月售：" + info.getAmount());
        Glide.with(getApplicationContext())
                .load(info.getIcon())
                .into(iv_product);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 0;
                if (i == 1) {
                    id = R.drawable.shuiguogan;
                } else if (i == 2) {
                    id = R.drawable.jianguo;
                } else if (i == 3) {
                    id = R.drawable.guazi;
                } else if (i == 4) {
                    id = R.drawable.yupi;
                }
                Info info1 = new Info(info.getShop(), info.getName(), info.getPrice(), 1, id, 0);
                dao.insert(info1);
                Toast.makeText(ProductDetailActivity.this,
                        "加入成功",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        tv_price = (TextView) findViewById(R.id.price);
        tv_type = (TextView) findViewById(R.id.type);
        tv_name = (TextView) findViewById(R.id.name);
        tv_sale = (TextView) findViewById(R.id.sale);
        iv_product = (ImageView) findViewById(R.id.iv_product);
        tv_add = (TextView) findViewById(R.id.add);
    }
}