package com.example.fanficbgm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    PlayerClass player;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        player = new PlayerClass(this);
    }

    public void playButton(View v)
    {
        player.play();
        updateUI();
    }

    public void nextButton(View v)
    {
        player.next();
        updateUI();
    }

    public void resetButton(View v)
    {
        player.reset();
        updateUI();
    }

    public void prevChapterButton(View v)
    {
        player.nextChapter();
        updateUI();
    }
    public void nextChapterButton(View v)
    {
        player.prevChapter();
        updateUI();
    }
    public void prevSongButton(View v)
    {
        player.prevSong();
        updateUI();
    }

    public void nextSongButton(View v)
    {
        player.nextSong();
        updateUI();
    }

    void updateUI()
    {
        textView2.setText("echo!");

//        textView2.setText("Chapter " + player.getCurrentChapter());
        textView2.setText(player.getCurrentChapterName());
        textView3.setText("Song " + player.getCurrentSong());
    }
}
