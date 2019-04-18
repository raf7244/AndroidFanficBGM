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
        player.play();
    }

    public void nextButton(View v)
    {
        player.next();
    }

    public void resetButton(View v)
    {
        player.reset();
    }

    public void prevChapterButton(View v)
    {
        player.nextChapter();
    }
    public void nextChapterButton(View v)
    {
        player.prevChapter();
    }
    public void prevSongButton(View v)
    {
        player.prevSong();
    }

    public void nextSongButton(View v)
    {
        player.nextSong();
    }


}
