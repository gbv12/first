package adapter;


import static fragment.BuycarFragment.fee;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.R;

import java.util.List;

import bean.Info;
import dao.InfoDao;
import fragment.BuycarFragment;

public class RecyclerCarAdapter extends RecyclerView.Adapter<RecyclerCarAdapter.MyViewHolder> {
    private List<Info> list;
    private View view;
    private InfoDao dao;

    public RecyclerCarAdapter(List<Info> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_shopcar, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        dao = new InfoDao(view.getContext());
        final Info a = list.get(position);
        if (list==null) BuycarFragment.total.setText("￥"+0);
        holder.dianming.setText(a.getDianming());
        holder.mingzi.setText(a.getMingzi());
        holder.price.setText(a.getPrice() + "");
        holder.number.setText(a.getNumber() + "");
        holder.shangpin.setImageResource(Math.toIntExact(a.getShangpin()));
        if(a.isFlag()==1){
            holder.cb.setChecked(true);
        }
        else {
            holder.cb.setChecked(false);
        }

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cb.isChecked()) {
                    a.setFlag(1);
                    dao.update(a);
                    fee+=a.getCost()*a.getNumber();
                    BuycarFragment.total.setText("￥"+ BuycarFragment.tran(fee));
                } else {
                    a.setFlag(0);
                    dao.update(a);
                    fee-=a.getCost()*a.getNumber();
                    BuycarFragment.total.setText("￥"+BuycarFragment.tran(fee));
                }
                notifyDataSetChanged();
            }
        });
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setNumber(a.getNumber() + 1);
                if (holder.cb.isChecked()) {
                    fee += a.getPrice();
                    BuycarFragment.total.setText("￥"+BuycarFragment.tran(fee));
                }
                notifyDataSetChanged();
                dao.update(a);
            }
        });
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (a.getNumber() > 1) {
                    a.setNumber(a.getNumber() - 1);
                    if (holder.cb.isChecked()) {
                        fee -= a.getPrice();
                        BuycarFragment.total.setText("￥"+BuycarFragment.tran(fee));
                    }
                    notifyDataSetChanged();
                    dao.update(a);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener listener =
                        new android.content.DialogInterface.
                                OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (holder.cb.isChecked()) {
                                    fee -= a.getPrice() * a.getNumber();
                                    BuycarFragment.total.setText("￥"+BuycarFragment.tran(fee));
                                }
                                list.remove(a);          // 从集合中删除
                                dao.delete(a.getID());// 从数据库中删除

                                notifyDataSetChanged();// 刷新界面
                            }
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()); // 创建对话框
                builder.setTitle("确定要删除吗?");                    // 设置标题
                // 设置确定按钮的文本以及监听器
                builder.setPositiveButton("确定", listener);
                builder.setNegativeButton("取消", null);         // 设置取消按钮
                builder.show(); // 显示对话框
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dianming, mingzi, price, number;
        ImageButton up, down, delete;
        ImageView shangpin;
        CheckBox cb;

        public MyViewHolder(View v) {
            super(v);
            dianming = (TextView) v.findViewById(R.id.tv_dianming);
            mingzi = (TextView) v.findViewById(R.id.mingzi);
            price = (TextView) v.findViewById(R.id.jiage);
            number = (TextView) v.findViewById(R.id.tv_shuliang);
            shangpin = (ImageView) v.findViewById(R.id.shangpin);
            up = (ImageButton) v.findViewById(R.id.ib_jiahao);
            down = (ImageButton) v.findViewById(R.id.ib_jianhao);
            delete = (ImageButton) v.findViewById(R.id.delete);
            cb = (CheckBox) v.findViewById(R.id.cb);
        }
    }
}
