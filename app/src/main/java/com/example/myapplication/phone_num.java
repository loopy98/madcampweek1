package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class phone_num extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_num);

        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                finish();
            }
        }
        mRecyclerView = findViewById(R.id.phonenum);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<phonenum_item> data;
        data = getContactList();

        MyAdapter myAdapter = new MyAdapter(data);
        mRecyclerView.setAdapter(myAdapter);
    }

    public void home(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    private ArrayList<phonenum_item> getContactList() {
        ArrayList<phonenum_item> data = new ArrayList<>();
        int icon = R.drawable.user;

        Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI, new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID
        }, null, null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");

        while (cursor.moveToNext()) {
            try {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String phonenum = contactsPhone(id);
                phonenum_item item = new phonenum_item(icon, name, phonenum);
                data.add(item);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return data;
    }

    private String contactsPhone(String id) {
        String result = null;

        if ((id == null) || (id.trim().equals(""))) {
            return null;
        }

        Cursor cursor = managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER
        }, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);

        while (cursor.moveToNext()) {
            try {
                result = cursor.getString(0);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();

        return result;
    }
}