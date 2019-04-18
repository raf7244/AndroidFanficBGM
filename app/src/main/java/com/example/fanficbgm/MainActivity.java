package com.example.fanficbgm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    PlayerClass player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = new PlayerClass(this);
    }

    public void playButton(View v)
    {
        player.selectSong();
    }

    public void nextButton(View v)
    {
        player.play();
    }

    public void resetButton(View v)
    {
        player.next();
    }

    public void prevChapterButton(View v)
    {

    }
    public void nextChapterButton(View v)
    {

    }
    public void prevSongButton(View v)
    {

    }

    public void nextSongButton(View v)
    {

    }


}
