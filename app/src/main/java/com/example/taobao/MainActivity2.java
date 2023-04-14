package com.example.taobao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;
import bean.Info;
import bean.ProductInfo;
import dao.InfoDao;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import utils.Constant;
import utils.JsonParse;


public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;
    private List<ProductInfo> infoList;
//    private String[] names={"天猫","天猫","天猫","淘宝"};
    private int[]  icons={R.drawable.shuiguogan,R.drawable.jianguo,R.drawable.guazi,R.drawable.yupi};
//    private int[] icons2={R.drawable.figure1,R.drawable.figure2,R.drawable.figure3,R.drawable.figure4};
    private MHandler mHandler;

    private InfoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        mHandler=new MHandler();
        recyclerView=(androidx.recyclerview.widget.RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new HomeAdapter();
        recyclerView.setAdapter(mAdapter);
        infoList= mAdapter.list;
        dao=new InfoDao(MainActivity2.this);
    }
    class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        private List<ProductInfo> list;
        private Context mContext;
        public HomeAdapter() {
            this.mContext = getApplicationContext();
        }
        public ProductInfo getItem(int position) {
            return list == null ? null : list.get(position);
        }

        public void setData(List<ProductInfo> data) {
            this.list = data;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(MainActivity2.this).inflate(R.layout.recycler_item2,parent,false));
            return holder;
        }
        @SuppressLint("ResourceType")
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            ProductInfo info=getItem(position);
            holder.name.setText(info.getType());
//            holder.iv1.setImageResource(icons[position]);
//            holder.iv2.setImageResource(icons2[position]);
            holder.amount.setText(info.getAmount());
            holder.introduce.setText(info.getName());
            holder.price.setText("￥"+info.getPrice());
            holder.ib.setImageResource(R.drawable.shopcar);
            holder.dianming.setText(info.getShop());
            Glide.with(mContext)
                    .load(info.getIcon())
                    .into(holder.iv1);
            Glide.with(mContext)
                    .load(info.getIcon2())
                    .into(holder.iv2);
            holder.ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Info info1=new Info(info.getShop(),info.getName(),info.getPrice(),1,icons[position],0);
                    dao.insert(info1);
                    notifyDataSetChanged();
                    Toast.makeText(MainActivity2.this,
                            "加入成功",
                            Toast.LENGTH_SHORT).show();
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity2.this,ProductDetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("product",info);
                    bundle.putInt("id",position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name,price,amount,introduce,dianming;
            ImageView iv1,iv2;
            ImageButton ib;

            public  MyViewHolder(View view)
            {
                super(view);
                name=(TextView) view.findViewById(R.id.tv_name);
                price=(TextView) view.findViewById(R.id.tv_price);
                amount=(TextView) view.findViewById(R.id.tv_amount);
                introduce=(TextView) view.findViewById(R.id.tv_introduce);
                dianming=(TextView) view.findViewById(R.id.diamming);
                iv1=(ImageView) view.findViewById(R.id.iv1);
                iv2=(ImageView)view.findViewById(R.id.iv2);
                ib=(ImageButton) view.findViewById(R.id.ib_shopcar);

            }

        }

    }
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_SHOP_URL).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取店铺数据
                Message msg = new Message();
                msg.what = 1;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //解析获取的JSON数据
                        List<ProductInfo> pythonList = JsonParse.getInstance().
                                getInfoFromJson(vlResult);
                        mAdapter.setData(pythonList);
                    }
                    break;
            }
        }
    }
}