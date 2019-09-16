package com.example.fanficbgm;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class FileClass
{
    static String[] loadSonglist(Context context)
    {
        String[] nameTable = new String[50];
        try
        {
            InputStream inStream = context.getAssets().open("songlist.txt");

            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line;

            for (int i=1;(line = bufferedReader.readLine()) != null;i++)
            {
                nameTable[i]=line;
//                System.out.println(line);
//                Toast.makeText(context, line, Toast.LENGTH_SHORT).show();
            }
        }
        catch (IOException e)
        {
            Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
        }
        return nameTable;
    }

    static SongData loadSongOrder(Context context)
    {
        String bookTitle = "";
        String[] chapterNames = new String[70];
        byte[][] songOrder = new byte[70][20]; //TODO: make it a truly a non-rectangular table
        for(int x=0;x<70;x++)
        {
            for(int y=0;y<20;y++)
                songOrder[x][y]=(byte)-1;
        }

        try
        {
            InputStream inStream = context.getAssets().open("order.txt");

            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line;
            String lineFragments[];
            String lineFragmentFragments[];


//            if((bookTitle = bufferedReader.readLine()) != null) bookTitle = bufferedReader.readLine(); //1st line is the book title


            if((bookTitle = bufferedReader.readLine()) != null)
            {
                System.out.println("Book title: " + bookTitle);

                for (int i=0;(line = bufferedReader.readLine()) != null;i++) //for every line
                {
                    //songOrder[i][0]=Byte.parseByte(line);//bullshit
                    line = line.replaceAll(";", "");
                    line = line.replaceAll("-", "");
                    //System.out.println("Line: " + line);
                    lineFragments = line.trim().split("::");
                    for (String lineFragment : lineFragments)
                    {
                        chapterNames[i] = lineFragments[0]; //set chapter name as the 1st thing read
                        //System.out.println("chapter name: " + lineFragments[0]);
                        lineFragmentFragments = lineFragments[1].trim().split(","); //split the second half
                        for (int j = 0; j < lineFragmentFragments.length; j++) //for every line sub-element
                        {
                            songOrder[i][j+1] = Byte.parseByte(lineFragmentFragments[j]);
                            //System.out.println("Song order:" + lineFragmentFragments[j]);
                        }
                    }

//                System.out.println(line);
//                Toast.makeText(context, line, Toast.LENGTH_SHORT).show();
                }
                for(int i=0;i<70;i++)
                {
                    for(int j=0;j<20;j++)
                    {
                        System.out.print(songOrder[i][j]);
                    }
                    System.out.println();
                }
            }
        }
        catch (IOException e)
        {
            Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
        }
        return new SongData(bookTitle, chapterNames, songOrder);
    }
}
