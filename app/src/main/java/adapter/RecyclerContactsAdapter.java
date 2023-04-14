package adapter;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.AddressActivity;
import com.example.taobao.R;

import java.util.List;

import bean.ContactInfo;

public class RecyclerContactsAdapter extends RecyclerView.Adapter<RecyclerContactsAdapter.MyViewHolder>{
    private List<ContactInfo> list;
    private  View view;
    private Object AddressActivity;

    public RecyclerContactsAdapter(List<ContactInfo> list){
       this.list=list;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iv_contact.setImageResource(Math.toIntExact(list.get(position).getPhoto()));
        holder.contactname.setText(list.get(position).getName());
        holder.contactphone.setText(list.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contactname, contactphone;
        ImageView iv_contact;

        public MyViewHolder(View v) {
            super(v);
            contactname = (TextView) v.findViewById(R.id.contactname);
            contactphone = (TextView) v.findViewById(R.id.contactphone);
            iv_contact = (ImageView) v.findViewById(R.id.iv_contactphoto);
        }
    }
}
