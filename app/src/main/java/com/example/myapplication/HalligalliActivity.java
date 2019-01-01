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

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Random;

public class HalligalliActivity extends AppCompatActivity {
    private long backPressedTime = 0;
    private final long FINISH_INTERVAL_TIME = 2000;
    int player1_score = 0;
    int player2_score = 0;
    int current_score = 0;
    int random_num;
    boolean is_tapped = false;
    boolean stop_main = false;
    GridView gridview;
    ImageAdapter imageadapter;
    DisplayMetrics mMetrics = new DisplayMetrics();
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
        if (stop_main) return;
        stop_main = true;
        if ( player1_score >= 50 ) {
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
        else if (player2_score >= 50){
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
//        게임이 종료되지 않고 정상적으로 진행된다
        else {
            Random random = new Random();
            random_num = random.nextInt(20);
            Integer new_card = halligalli_list[random_num];

            if (current_score >= 4) {
                int prev_random_num = Arrays.asList(halligalli_list).indexOf(current_list[current_score%4]);
                fruit_num[prev_random_num/5] -= prev_random_num%5 + 1;
            }
            fruit_num[random_num/5] += random_num%5 + 1;
            current_list[current_score%4] = new_card;
            current_score++;

//            imageadapter = new ImageAdapter(this);
//            gridview = (GridView) findViewById(R.id.halligalli_gridview);
            gridview.setAdapter(imageadapter);
//            imageadapter.notifyDataSetChanged();

            Handler delayHandler = new Handler();
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!is_tapped) {
                        stop_main = false;
                        halligalli_main();
                    }
                }
            }, 1000);
        }
    }

    public void halligalli_start(View v){
        gridview = (GridView) findViewById(R.id.halligalli_gridview);
        imageadapter = new ImageAdapter(this);
        gridview.setAdapter(imageadapter);
        if (is_tapped) {
            stop_main = false;
            is_tapped = false;
        }
        halligalli_main();
    }

//    public void halligalli_home(View v) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void tap1(View v) {
        if (is_tapped) return;
        is_tapped = true;
        stop_main = true;
        if ( (fruit_num[0] == 5) || (fruit_num[1] == 5) || (fruit_num[2] == 5) || (fruit_num[3] == 5)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Player 1 Good Job!!", Toast.LENGTH_SHORT);
            toast.show();

            player1_score += current_score;
            setScore();
            current_score = 0;
            for (int i=0; i<4; i++) {
                current_list[i] = R.drawable.white;
                fruit_num[i] = 0;
            }
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Oops!!", Toast.LENGTH_SHORT);
            toast.show();

            player1_score = ( (player1_score >= 5) ? player1_score-5 : 0 );
            setScore();
        }
    }

    public void tap2(View v) {
        if (is_tapped) return;
        is_tapped = true;
        stop_main = true;
        if ( (fruit_num[0] == 5) || (fruit_num[1] == 5) || (fruit_num[2] == 5) || (fruit_num[3] == 5)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Player 2 Good Job!!", Toast.LENGTH_SHORT);
            toast.show();

            player2_score += current_score;
            setScore();
            current_score = 0;
            for (int i=0; i<4; i++) {
                current_list[i] = R.drawable.white;
                fruit_num[i] = 0;
            }
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Oops!!", Toast.LENGTH_SHORT);
            toast.show();

            player2_score = ( (player2_score >= 5) ? player2_score-5 : 0 );
            setScore();
        }
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
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

//            int height = (mMetrics.heightPixels) / 2;

            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 450));
//                imageView.setLayoutParams(new GridView.LayoutParams((int)(0.66*((float)height)), height));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(3, 3, 3, 3);
            } else {
                imageView = (ImageView) convertView;
            }
//            imageView.setImageResource(current_list[position]);
            Glide.with(mContext).load(current_list[position]).into(imageView);
            return imageView;
        }
    }
}

