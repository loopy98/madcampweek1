package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImagePopup extends Activity {
    private Context mContext = null;
    private final int imgWidth = 960;
    private final int imgHeight = 960;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_popup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String imgPath = extras.getString("filename");

        /** 완성된 이미지 보여주기  */
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        ImageView imageView;
        imageView = new ImageView(mContext);

        

//        BitmapFactory.Options bfo = new BitmapFactory.Options();
//        bfo.inSampleSize = 2;
//        Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
//        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
//        iv.setImageBitmap(resized);

        Glide.with(mContext).load(imgPath).into(iv);
    }


}


