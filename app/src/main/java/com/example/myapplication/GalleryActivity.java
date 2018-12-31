package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {
    private final int GALLERY_CODE = 1112;
    private Context mContext = this;
    GridView gridview = (GridView)findViewById(R.id.gallery);
    ImageAdapter imageAdapter = new ImageAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // permission 확인, permission 이 없으면 MainActivity 로 돌아간다.
        boolean hasPermission = getGalleryPermission();
        if (!hasPermission) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }

        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                imageAdapter.callImageViewer(position);
            }
        });
    }

    private boolean getGalleryPermission() {
        // 버전이 16보다 낮으면 따로 permission 을 물어볼 필요가 없다 (manifest 에 적은 것만으로 permission 획득)
        if (Build.VERSION.SDK_INT < 16) return true;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) return true;

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
//        Toast toast = Toast.makeText(getApplicationContext(), "ㅋㅋ", Toast.LENGTH_SHORT);
//        toast.show();
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) return true;
        if (permissionCheck == PackageManager.PERMISSION_DENIED) return false;
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    imageView.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}