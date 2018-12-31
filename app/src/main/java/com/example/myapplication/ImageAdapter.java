package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String imgData;
    private String geoData;
    private ArrayList<String> thumbsDataList;
    private ArrayList<String> thumbsIDList;

    public ImageAdapter(Context c) {
        mContext = c;
        thumbsDataList = new ArrayList<String>();
        thumbsIDList = new ArrayList<>();
        getThumbInfo(thumbsIDList, thumbsDataList);
    }

    public final void callImageViewer(int selectedIndex) {
        Intent i = new Intent(mContext, GallaryImagePopup.class);
        String imgPath = getImageInfo(imgData, geoData, thumbsIDList.get(selectedIndex));
        i.putExtra("filename", imgPath);
        startActivityForResult(i, 1);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        int rowWidth = (mMetrics.widthPixels) / 3;

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(rowWidth,rowWidth));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}

//public class ImageAdapter extends BaseAdapter {
//    private Context mContext;
//
//    public ImageAdapter(Context c) {
//        mContext = c;
//    }
//
////    public int getCount() {
////        return mThumbIds.length;
////    }
//
//    public Object getItem(int position) {
//        return null;
//    }
//
//    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
//    }
//}
