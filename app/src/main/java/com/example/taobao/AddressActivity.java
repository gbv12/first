package com.example.taobao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerContactsAdapter;
import bean.ContactInfo;
import fragment.MessageFragment;

public class AddressActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView iv_return;
    private RecyclerContactsAdapter adapter;
    protected List<ContactInfo> list=new ArrayList<ContactInfo>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ActivityCompat.requestPermissions(AddressActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);

        iv_return = (ImageView) findViewById(R.id.fanhui);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("Range")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            String phone = null,id=null,name=null;
            ContactInfo contactInfo;
            Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                        null, null);
                while (phones.moveToNext()) {
                    phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phones.close();
                contactInfo=new ContactInfo((long) R.drawable.usephone,name,phone);
                list.add(contactInfo);
            }
            cursor.close();
            recyclerView = (RecyclerView) findViewById(R.id.rv_tongxunlu);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerContactsAdapter(list);
            recyclerView.setAdapter(adapter);
        } else {
            Intent intent = new Intent(AddressActivity.this, MessageFragment.class);
            startActivity(intent);
        }
    }
}