package com.mainoramg.learn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesPlayground {

    LocalDate localDate;

    public DatesPlayground(LocalDate localDate) {
        this.localDate = localDate;
    }

    public DatesPlayground() {
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
        DatesPlayground datesPlayground = new DatesPlayground();
        String date = "05/02/1970";
        datesPlayground.setLocalDateFromString(date);
        System.out.println("Original date: "+date);
        System.out.println("Month: "+ datesPlayground.getMonth());
        System.out.println("Day: "+ datesPlayground.getDay());
        System.out.println("Year: "+ datesPlayground.getYear());
    }
}
