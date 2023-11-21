package com.example.newnewmusicplayer;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String artist;
    private int path;

    public Song(String title, String artist, int path) {
        this.title = title;
        this.artist = artist;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
