package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.MainActivity2;
import com.example.taobao.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import adapter.RecyclerGridAdapter;

public class FirstPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    TextView tv_time;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.top, container, false);

        //RecyclerView
        layoutManager=new GridLayoutManager(getActivity(),2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerGridAdapter(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //文字、图片轮播
        ViewFlipper viewFlipper=(ViewFlipper) view.findViewById(R.id.vf);
        viewFlipper.startFlipping();
        ViewFlipper viewFlipper2=(ViewFlipper) view.findViewById(R.id.vf2);
        viewFlipper2.startFlipping();

        tv_time=(TextView) view.findViewById(R.id.tv_deTime);
        new TimeThread().start();


        Button btn11=(Button) view.findViewById(R.id.btn11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
            }
        });

        return  view;
    }
    //新进程
    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);//间隔一秒刷新
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);

        }
    }
    //计算时间差及传入TextView时间
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    Date d1,d2= null;

                    try
                    {
                        d1 = df.parse("24:00:00");
                        d2 = df.parse(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
                        long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
                        long days = diff / (1000 * 60 * 60 * 24);

                        long hours = diff/(1000* 60 * 60);
                        long minutes = (diff-hours*(1000* 60 * 60))/(1000* 60);
                        long seconds=((diff-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000));
                        tv_time.setText(""+hours+":"+minutes+":"+seconds);
                    }catch (Exception e)
                    {
                    }

                    break;
            }
            return false;
        }
    });
}
