package com.mainoramg.buchalka.section8;

public class Song {
    private String title;
    private double duration;

    @Override
    public String toString() {
        return title + ": " + duration;
    }

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }
}
