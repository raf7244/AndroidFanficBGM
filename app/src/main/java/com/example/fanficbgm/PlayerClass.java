package com.example.fanficbgm;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

class PlayerClass
{
    private static MediaPlayer mediaPlayer;
    private Context context;
    private String[] nameTable;
    private SongData songData;
    private byte currentChapter;
    private byte currentSong;
    private boolean isPlaying;

    PlayerClass(Context context)
    {
        mediaPlayer = new MediaPlayer();
        this.context = context;

        nameTable = FileClass.loadSonglist(context); //list of all the songs in the directory
        songData = FileClass.loadSongOrder(context); //book title, chapter names, song order
        //currentPosition = new byte[songData.getSongOrder().length][songData.getSongOrder()[0].length]; //wtf was that lol //TODO: make it a truly a non-rectangular table
        currentChapter = 0;
        currentSong = 1;
        isPlaying = false;

        initPlayer(songData.getSongOrder()[currentChapter][currentSong]); //TODO: read song played last time
    }

    private void initPlayer(int songNr) //used at first run AND song changes
    {
        if(nameTable!=null)
        {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            System.out.println(path);
            Uri uri = Uri.fromFile(new File(path+"/Katawa Shoujo Enigmatic Box of Sound/" + nameTable[songNr])); //TODO: nameTable[] should use the last used song or sth
            System.out.println(uri);

            try
            {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(context, uri);
                System.out.println(uri);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    System.out.println("setting attrs");

                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                }
                else //in case it's older than Lollipop
                {
                    System.out.println("alternative attrs");

                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                }


                //mediaPlayer.setOnPreparedListener(onPrepared(mediaPlayer));
//                mediaPlayer.prepare();
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    }

    void play()
    {
        if(songData.getSongOrder()[currentChapter][currentSong] == 0) //if the song is "no song", do nothing
        {
//            initPlayer(songData.getSongOrder()[1][1]);
//            play();
            System.out.print("-no song playing, as intended-");
        }
        else
        {
            if(isPlaying)
            {
                mediaPlayer.stop();
                isPlaying=false;

            }
            else
            {
                initPlayer(songData.getSongOrder()[currentChapter][currentSong]);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                {
                    @Override
                    public void onPrepared(MediaPlayer mp)
                    {
                        mediaPlayer.start();
                        isPlaying=true;
                    }
                });
                try
                {
                    mediaPlayer.prepare();
                }catch (IOException e)
                {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }


//        mediaPlayer.prepare();
//        mediaPlayer.start();

    }

    void next()
    {
        mediaPlayer.stop();
        if (songData.getSongOrder()[currentChapter][currentSong + 1] != -1) //a standard situation
        {
            currentSong++;
        }
        else //if it is the last song in a chapter
        {
            if (songData.getSongOrder()[currentChapter + 1][1] != -1) // if it's the last chapter
            {
                currentChapter++;
                currentSong=1;
            }
        }

        if(songData.getSongOrder()[currentChapter][currentSong]!=0)initPlayer(songData.getSongOrder()[currentChapter][currentSong]); //don't load anything if the song is null
        if (isPlaying)
        {
            play();
        }
    }

    void reset() //go to chapter 1 song 1
    {
        for(int i=0;i<70;i++)
        {
            for(int j=0;j<20;j++)
            {
                System.out.print(songData.getSongOrder()[i][j]);
            }
            System.out.println();
        }
    }

    void prevChapter()
    {

    }
    void nextChapter()
    {

    }
    void prevSong()
    {

    }

    void nextSong()
    {

    }

    void updateUI()
    {
        //textView1.setText("Title"); //deprecated?
    }

    byte getCurrentChapter()
    {
        return currentChapter; //deprecated?
    }

    byte getCurrentSong()
    {
        return currentSong;
    }
    String getCurrentChapterName()
    {
        return songData.getChapterNames()[currentChapter];
    }

    byte getCurrentSongName()
    {
        return currentSong; //deprecated?
    }
}
