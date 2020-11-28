package com.mainoramg.learn;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DatesPlayground {

    LocalDate localDate;

    public static void main(String[] args) {
        getMonthDayYearFromLocalDate();
        compareTwoInstant();
    }

    /**
     * Comparing two different Instant
     */
    static void compareTwoInstant() {
        Instant fromDb = LocalDateTime.parse(
                "2020-10-05 21:49:42",
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss", Locale.US))
                .atZone(ZoneId.of("-05:00"))
                .toInstant();

        Instant now = Instant.now();
        System.out.println("Instant from DB: "+fromDb.toString());
        System.out.println("Instant now: "+now.toString());

        boolean sameDate = fromDb.truncatedTo(ChronoUnit.DAYS).equals(now.truncatedTo(ChronoUnit.DAYS));
        System.out.println("It is the same date: "+sameDate);

        boolean sameDate2 = fromDb.atZone(ZoneId.of("-05:00")).truncatedTo(ChronoUnit.DAYS).isEqual(now.atZone(ZoneId.of("-05:00")).truncatedTo(ChronoUnit.DAYS));
        System.out.println("It is the same date: "+sameDate2);
    }

    /**
     * Getting month, day and year from LocalDate
     */
    static void getMonthDayYearFromLocalDate() {
        DatesPlayground datesPlayground = new DatesPlayground();
        String date = "05/02/1970";
        datesPlayground.setLocalDateFromString(date);
        System.out.println("Original date: "+date);
        System.out.println("Month: "+ datesPlayground.getMonth());
        System.out.println("Day: "+ datesPlayground.getDay());
        System.out.println("Year: "+ datesPlayground.getYear());
    }

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
}
