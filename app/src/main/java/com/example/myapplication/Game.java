package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity{


    ImageView imageDice;
    static int locate;
    static int random;
    static int locateai;
    static int randomai;
    static boolean win = true;
    static boolean lose = true;
    static boolean anim = true;
    int rand;
    int randai;
    int count;
    static int overlap;
    ImageView wow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        imageDice = findViewById(R.id.imageDice);
        Button bac = (Button) findViewById(R.id.bac);
        int[] diceId = {R.drawable.dice1, R.drawable.dice2,
                R.drawable.dice3, R.drawable.dice4,
                R.drawable.dice5, R.drawable.dice6};
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (true) {
                    rand = getRandom(6, 0);
                    if (overlap == rand) {
                        if (rand > 3) {
                            rand = getRandom(3, 0);  //3이하 출력
                        } else if (rand <= 3) {
                            rand = getRandom(3, 3);  //4이상 출력
                        }
                    }
                    imageDice.setImageResource(diceId[rand]);
                    overlap = rand;
                }
            }
        };
        timer.schedule(timerTask, 0, 800);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onClick(View view) throws InterruptedException {

        ImageView walk = (ImageView)findViewById(R.id.walking);
        final AnimationDrawable drawable = (AnimationDrawable) walk.getDrawable();
        randai = 3;
        wow=(ImageView)findViewById(R.id.wowimg);
        if (rand==5)  //주사위 눈금이 6일떄
        {
            wow.setImageResource(R.drawable.wow);
        }
        else
        {
            wow.setImageResource(0);
        }

        if (drawable.isRunning())
        {
            drawable.stop();
            anim = true;
        }
        else
        {
            drawable.getCurrent();
            drawable.start();

            anim = false;
        }
        final ImageView iv = (ImageView)findViewById(R.id.imagelion);
        iv.bringToFront();
        final ImageView ivai = (ImageView)findViewById(R.id.image1);
        ivai.bringToFront();
        if ( win == true && lose == true)
        {
            random = random + rand*50;
            randomai = randomai + randai*50;
            TranslateAnimation anim = new TranslateAnimation(locate,  random, 0, 0);
            TranslateAnimation animai = new TranslateAnimation(locateai,  randomai, 0, 0);
            locate = locate + rand*50;
            locateai = locateai + randai*50;

            anim.setFillAfter(true);
            anim.setDuration(2000);
            animai.setFillAfter(true);
            animai.setDuration(2000);
            iv.startAnimation(anim);
            ivai.startAnimation(animai);

            if(locate>800 && locate>locateai)
            {
                win = false;
            }
            else if(locateai > 800 && locateai>locate)
            {
                lose = false;
            }
        }
        else if (win == false || lose == false)
        {
            if (win == false && lose == true)
            {
                Intent intent = new Intent(getApplicationContext(), Win.class);
                startActivity(intent);
                locate = 0;
                locateai = 0;
                random = 0;
                randomai = 0;
                win = true;
                lose = true;
                finish();
            }
            if (win == true && lose == false)
            {
                Intent intent = new Intent(getApplicationContext(), Lose.class);
                startActivity(intent);
                locate = 0;
                locateai = 0;
                random = 0;
                randomai = 0;
                win = true;
                lose = true;
                finish();
            }

        }

    }

    int getRandom(int range, int min)
    {
        return (int)(Math.random() * range) + min;
    }
}