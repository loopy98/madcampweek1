package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView phonenum;

        MyViewHolder(View view){
            super(view);
            icon = view.findViewById(R.id.imageView);
            name = view.findViewById(R.id.textView1);
            phonenum = view.findViewById(R.id.textView2);
        }
    }

    private ArrayList<phonenum_item> data;
    MyAdapter(ArrayList<phonenum_item> data){
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonenum_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.icon.setImageResource(data.get(position).getIcon());
        myViewHolder.name.setText(data.get(position).getName());
        myViewHolder.phonenum.setText(data.get(position).getPhonenum());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public String getname(RecyclerView.ViewHolder holder) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String name = myViewHolder.name.toString();
        return name;
    }

}

