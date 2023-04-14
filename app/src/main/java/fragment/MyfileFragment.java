package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taobao.LoginActivity;
import com.example.taobao.R;
import com.example.taobao.SettingActivity;

public class MyfileFragment extends Fragment {
    TextView tv1;
    TextView tv2;
    ImageView iv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_mine, container, false);
        tv1 = (TextView) view.findViewById(R.id.tv_nicheng);
        tv2 = (TextView) view.findViewById(R.id.tv_zhanghao);
        ImageButton btn = (ImageButton) view.findViewById(R.id.ib_touxiang);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
            }
        });
        iv=(ImageView) view.findViewById(R.id.iv_setting);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        view.setClickable(true);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            Bundle bundle=data.getExtras();
            String number=bundle.getString("number");
            tv1.setText("");
            tv2.setText(number);
        }
    }
}
