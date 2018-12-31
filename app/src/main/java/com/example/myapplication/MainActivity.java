package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> al = new ArrayList<String>();
        al.add("나만의 전화번호부 ");
        al.add("나만의 갤러리 ");
        al.add("할리갈리게임 ");

        ArrayAdapter<String> ad;
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);

        ListView list=(ListView)findViewById(R.id.listview);
        list.setAdapter(ad);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent it = new Intent(MainActivity.this, phone_num.class);
                        startActivity(it);
                        finish();
                        break;
                    case 1:
                        Intent it1 = new Intent(MainActivity.this, GalleryActivity.class);
                        startActivity(it1);
                        finish();
                        break;
                    case 2:
                        Intent it2 = new Intent(MainActivity.this, HalligalliActivity.class);
                        startActivity(it2);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기를 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}