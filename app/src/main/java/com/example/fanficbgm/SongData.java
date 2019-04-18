package com.example.fanficbgm;

final class SongData
{
    private String bookTitle;
    private String[] chapterNames;
    private byte[][] songOrder;

    String getBookTitle()
    {
        return bookTitle;
    }

    String[] getChapterNames()
    {
        return chapterNames;
    }

    byte[][] getSongOrder()
    {
        return songOrder;
    }

    SongData(String bookTitle, String[] chapterNames, byte[][] songOrder)
    {
        this.bookTitle = bookTitle;
        this.chapterNames = chapterNames;
        this.songOrder = songOrder;
    }
}