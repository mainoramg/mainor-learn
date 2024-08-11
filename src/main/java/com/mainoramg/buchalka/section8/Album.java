package com.mainoramg.buchalka.section8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) {
            songs.add(new Song(title, duration));
            return true;
        }

        return false;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        for (int i = 0; i < songs.size(); i++) {
            if (trackNumber == (i+1)) {
                playList.add(songs.get(i));
                return true;
            }
        }

        return false;
    }

    public boolean addToPlayList(String songTitle, LinkedList<Song> playList) {
        Song song = findSong(songTitle);
        if (song != null) {
            ListIterator<Song> listIterator = playList.listIterator();
            while (listIterator.hasNext()) {
                if (song == listIterator.next()) {
                    return false;
                }
            }

            playList.add(song);
            return true;
        }

        return false;
    }

    private Song findSong(String title) {
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getTitle().equals(title)) {
                return song;
            }
        }

        return null;
    }

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }
}
