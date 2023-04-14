package adapter;

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

public class RecyclerOrderAdapter extends RecyclerView.Adapter<RecyclerOrderAdapter.MyViewHolder> {
    private List<Info> list;
    private View view;
    private InfoDao dao;
    public RecyclerOrderAdapter(List<Info> list){
        this.list=list;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dao=new InfoDao(view.getContext());
        final Info a=list.get(position);
        holder.dianming.setText(a.getDianming());
        holder.name.setText(a.getMingzi());
        holder.amount.setText("x"+a.getNumber());
        holder.price.setText("￥"+a.getPrice()*a.getNumber());
        holder.shangpin.setImageResource(Math.toIntExact(a.getShangpin()));
        holder.type.setText(" 天猫 ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dianming,name,amount,price,type;
        ImageView shangpin;
        public MyViewHolder(View v) {
            super(v);
            dianming = (TextView) v.findViewById(R.id.order_dianming);
            name=(TextView) v.findViewById(R.id.textView3);
            amount=(TextView) v.findViewById(R.id.textView5);
            price=(TextView) v.findViewById(R.id.textView6);
            shangpin=(ImageView) v.findViewById(R.id.imageView3);
            type=(TextView) v.findViewById(R.id.textView10);
        }
    }
}
