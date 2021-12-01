package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        ImageView rail = (ImageView)findViewById(R.id.rail);
        Glide.with(this).load(R.raw.rail_gif).into(rail);
        ImageView rail2 = (ImageView)findViewById(R.id.rail2);
        Glide.with(this).load(R.raw.rail_gif).into(rail2);
        ImageView rail3 = (ImageView)findViewById(R.id.rail3);
        Glide.with(this).load(R.raw.rail_gif).into(rail3);
        ImageView rail4 = (ImageView)findViewById(R.id.rail4);
        Glide.with(this).load(R.raw.rail_gif).into(rail4);
        ImageView rail5 = (ImageView)findViewById(R.id.rail5);
        Glide.with(this).load(R.raw.rail_gif).into(rail5);
        ImageView rail6 = (ImageView)findViewById(R.id.rail6);
        Glide.with(this).load(R.raw.rail_gif).into(rail6);

        imageDice = findViewById(R.id.imageDice);
        Button bac = (Button) findViewById(R.id.bac);
        int[] diceId = {R.drawable.dice1, R.drawable.dice2,
                R.drawable.dice3, R.drawable.dice4,
                R.drawable.dice5, R.drawable.dice6};
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (true)
                {
                    rand = getRandom(6, 0);
                    imageDice.setImageResource(diceId[rand]);
                }
            }
        };
        timer.schedule(timerTask, 0, 500);


        bac.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onClick(View view) throws InterruptedException {

        ImageView dice1 = (ImageView)findViewById(R.id.animDice);
        final AnimationDrawable drawable = (AnimationDrawable) dice1.getDrawable();
        randai = 3;

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