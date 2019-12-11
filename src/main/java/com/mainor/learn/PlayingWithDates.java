package com.mainor.learn;

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
}
