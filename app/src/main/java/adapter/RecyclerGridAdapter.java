package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.R;

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.MyViewHolder> {
    private int[] icons={R.drawable.tian,R.drawable.shuang,R.drawable.jin,R.drawable.yi,R.drawable.wan,
            R.drawable.qiang,R.drawable.ershi,R.drawable.yu,R.drawable.dian,R.drawable.shou,
            R.drawable.taopiao,R.drawable.xianyu,R.drawable.qiche,R.drawable.chihuo};
    private String[] info={"天猫 U先","天天特卖","芭芭农场","飞猪旅行","天猫超市","充值中心","淘鲜达","翎淘金币",
            "阿里拍卖","分类","淘票票","闲鱼","天猫汽车","吃货"};

    public RecyclerGridAdapter(Context context) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //加载图片
//        holder.img.setImageResource(imgList.get(position));
        holder.img.setImageResource(icons[position]);
        holder.tv.setText(info[position]);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }
}
