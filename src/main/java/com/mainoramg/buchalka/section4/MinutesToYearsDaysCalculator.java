package com.mainoramg.buchalka.section4;

public class MinutesToYearsDaysCalculator {

    public static void main(String[] args) {
        printYearsAndDays(525600); // should print "525600 min = 1 y and 0 d"
        printYearsAndDays(1051200); // should print "1051200 min = 2 y and 0 d"
        printYearsAndDays(561600); // should print "561600 min = 1 y and 25 d"
    }

    private static final String INVALID_VALUE = "Invalid Value";

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println(INVALID_VALUE);
            return;
        }

        long hours = minutes / 60;
        long days = hours / 24;
        long years = days / 365;
        long remainingDays = days % 365;

        // "XX min = YY y and ZZ d"
        System.out.println(minutes + " min = " + years + " y and " + remainingDays + " d");
    }
}
