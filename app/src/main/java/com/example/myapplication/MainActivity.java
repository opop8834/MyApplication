package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    MediaPlayer mediaPlayer;
    boolean flag = true;
    private Button music;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.background);
        //mediaPlayer.setLooping(true); // 무한 루프

        music = (Button) findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (flag == true)
                {
                    music.setBackgroundResource(R.drawable.mute);
                    mediaPlayer.start();
                    flag =false;
                }
                else if (flag == false)
                {
                    music.setBackgroundResource(R.drawable.on);
                    mediaPlayer.pause();
                    flag=true;
                }

            }
        });

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Loading.class);
                startActivity(intent);
                finish();
            }
        });

        Button game_method = (Button) findViewById(R.id.game_method);
        game_method.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Game_method.class);
                startActivity(intent);
                finish();
            }
        });

        Button developer_info= (Button) findViewById(R.id.developer_info);
        developer_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Developer_info.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

