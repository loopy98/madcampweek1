package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HalligalliActivity extends AppCompatActivity {
    int player1_score = 0;
    int player2_score = 0;
    int current_score = 0;
    DisplayMetrics mMetrics = new DisplayMetrics();
    GridView gridview = (GridView) findViewById(R.id.halligalli_gridview);
    Integer[] halligalli_list = {R.drawable.banana1, R.drawable.banana2, R.drawable.banana3, R.drawable.banana4, R.drawable.banana5,
            R.drawable.jadu1, R.drawable.jadu2, R.drawable.jadu3, R.drawable.jadu4, R.drawable.jadu5,
            R.drawable.melon1, R.drawable.melon2, R.drawable.melon3, R.drawable.melon4, R.drawable.melon5,
            R.drawable.strawberry1, R.drawable.strawberry2, R.drawable.strawberry3, R.drawable.strawberry4, R.drawable.strawberry5};
    Integer[] current_list = {R.drawable.white, R.drawable.white, R.drawable.white, R.drawable.white};
    Integer[] fruit_num = {0, 0, 0, 0};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halligalli);

        setScore();
    }

    public void setScore() {
        TextView score1 = (TextView) findViewById(R.id.player1_score);
        TextView score2 = (TextView) findViewById(R.id.player2_score);
        String player1Score = Integer.toString(player1_score) + " 점";
        String player2Score = Integer.toString(player2_score) + " 점";

        score1.setText(player1Score);
        score2.setText(player2Score);
    }

    public void halligalli_main(){
        if ( player1_score >= 100 ) {
            setContentView(R.layout.halligalli_victory);
            TextView who = findViewById(R.id.halligalli_victory);
            String who_wins = "Player 1 Wins!!";
            who.setText(who_wins);

            Handler delayHandler = new Handler();
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(HalligalliActivity.this, HalligalliActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }, 3000);
        }
        else if (player2_score >= 100){
            setContentView(R.layout.halligalli_victory);
            TextView who = findViewById(R.id.halligalli_victory);
            String who_wins = "Player 2 Wins!!";
            who.setText(who_wins);

            Handler delayHandler = new Handler();
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent2 = new Intent(HalligalliActivity.this, HalligalliActivity.class);
                    startActivity(intent2);
                    finish();
                }
            }, 3000);
        }
        else {

        }
    }

    public void halligalli_start(View v){
        gridview.setAdapter(new ImageAdapter(this));
        halligalli_main();
    }

    public void halligalli_home(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void tab1(View v) {
        if ( (fruit_num[0] == 5) || (fruit_num[1] == 5) || (fruit_num[2] == 5) || (fruit_num[3] == 5)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Good Job!!", Toast.LENGTH_SHORT);
            toast.show();

            player1_score += current_score;
            current_score = 0;
            for (int i=0; i<4; i++) {
                current_list[i] = R.drawable.white;
                fruit_num[i] = 0;
            }

            Handler delayHandler = new Handler();
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    halligalli_main();
                }
            }, 1500);
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return current_list.length;
        }

        public Object getItem(int position) {
            return current_list[position];
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

            int height = (mMetrics.heightPixels) / 2;

            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams((int)(0.66*((float)height)), height));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(3, 3, 3, 3);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(current_list[position]);
            return imageView;
        }
    }
}

