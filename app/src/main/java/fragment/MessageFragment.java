package fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.taobao.AddressActivity;
import com.example.taobao.R;

public class MessageFragment extends Fragment {
    private ImageView iv_contact;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.activity_message, container, false);
        view1.setClickable(true);
        iv_contact=(ImageView) view1.findViewById(R.id.iv_tongxunlu);
        iv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });
        return  view1;
    }

}
