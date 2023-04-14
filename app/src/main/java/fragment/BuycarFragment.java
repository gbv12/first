package fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.MainActivity;
import com.example.taobao.OrderActivity;
import com.example.taobao.ProductDetailActivity;
import com.example.taobao.R;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerCarAdapter;
import bean.Info;
import dao.InfoDao;

public class BuycarFragment extends Fragment {
    protected int flag = 0;
    private RecyclerView recyclerView;
    private InfoDao dao;
    public static List<Info> list;
    private RecyclerCarAdapter adapter;
    private View viewS;
    public static TextView total;
    private TextView jiesuan;
    private Button btn_all;
    private String content = null;
    public static double fee;
    public Boolean f=false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewS = inflater.inflate(R.layout.activity_shopcar, container, false);
        viewS.setClickable(true);
        initView(viewS);
        list = new ArrayList<Info>();
        dao = new InfoDao(getActivity());
        list = dao.queryAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).isFlag()==1){
                fee+=list.get(i).getCost();
            }
        }
        total.setText("￥"+tran(fee));
        adapter = new RecyclerCarAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewS.getContext()));
        recyclerView.setAdapter(adapter);

        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Info> onList=new ArrayList<Info>();
                double total=0.0;
                for(Info l:list){
                    if(l.isFlag()==1) {
                        onList.add(l);
                        total+=l.getNumber()*l.getPrice();
                    }
                }
                Intent intent=new Intent(getContext(), OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putDouble("jine",total);
                bundle.putSerializable("list", (Serializable) onList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_all = (Button) viewS.findViewById(R.id.btn_all);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!f){
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isFlag() == 0) {
                            list.get(i).setFlag(1);
                            fee += list.get(i).getPrice() * list.get(i).getNumber();
                            dao.update(list.get(i));
                        }
                    }
                    f=true;
                    adapter.notifyDataSetChanged();
                    total.setText("￥" + tran(fee));
                    btn_all.setText("反选");
                }
                else{
                    for(int i=0;i<list.size();i++) {
                        list.get(i).setFlag(0);
                        dao.update(list.get(i));
                    }
                    fee=0.0;
                    f=false;
                    adapter.notifyDataSetChanged();
                    total.setText("￥" + tran(fee));
                    btn_all.setText("全选");
                }

            }
        });
        MainActivity.all_cost=fee;
        write_cost();
        return viewS;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv2);
        total = (TextView) view.findViewById(R.id.total);
        jiesuan=(TextView) view.findViewById(R.id.jiesuan);

    }

    public void write_cost() {
        String filename = "all_cost.txt";
        content = "" + fee;
        FileOutputStream fos = null;
        try {
            fos = viewS.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            try {
                fos.write(content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static double tran(double p) {
        BigDecimal b = new BigDecimal(p);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
}
