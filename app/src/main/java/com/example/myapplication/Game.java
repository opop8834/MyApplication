package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.Random;

public class Game extends AppCompatActivity{

    ImageView imageDice;
    static int locate;
    static int random;
    static int locateai;
    static int randomai;
    static boolean win = true;
    static boolean lose = true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        imageDice = findViewById(R.id.imageDice);

        Button bac = (Button) findViewById(R.id.bac);
        bac.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onClick(View view) {
        int[] diceId = {R.drawable.dice1, R.drawable.dice2,
                R.drawable.dice3, R.drawable.dice4,
                R.drawable.dice5, R.drawable.dice6};
        int rand = getRandom(6, 0);
        int randai = getRandom(6, 0);
        imageDice.setImageResource(diceId[rand]);
        final ImageView iv = (ImageView)findViewById(R.id.imagelion);
        final ImageView ivai = (ImageView)findViewById(R.id.image1);
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
        else
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