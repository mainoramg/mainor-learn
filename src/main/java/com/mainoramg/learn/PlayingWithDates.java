package com.mainoramg.learn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlayingWithDates {

    LocalDate localDate;

    public PlayingWithDates(LocalDate localDate) {
        this.localDate = localDate;
    }

    public PlayingWithDates() {
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setLocalDateFromString(String date) {
        this.localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getMonth() {
        return String.format("%02d", this.localDate.getMonthValue());
    }

    public String getDay() {
        return String.format("%02d", this.localDate.getDayOfMonth());
    }

    public String getYear() {
        return Integer.toString(this.localDate.getYear());
    }

    public static void main(String[] args) {
        PlayingWithDates playingWithDates = new PlayingWithDates();
        String date = "05/02/1970";
        playingWithDates.setLocalDateFromString(date);
        System.out.println("Original date: "+date);
        System.out.println("Month: "+playingWithDates.getMonth());
        System.out.println("Day: "+playingWithDates.getDay());
        System.out.println("Year: "+playingWithDates.getYear());
    }
}
