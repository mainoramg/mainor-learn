package com.mainoramg.buchalka;

public class LeapYear {

    public static void main(String[] args) {
        System.out.println(isLeapYear(-1600)); // should return false since the parameter is not in range (1-9999)
        System.out.println(isLeapYear(1600)); // should return true since 1600 is a leap year
        System.out.println(isLeapYear(2017)); // should return false since 2017 is not a leap year
        System.out.println(isLeapYear(2000)); // should return true because 2000 is a leap year
    }

    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        if (year % 4 > 0) {
            return false;
        } else if (year % 100 > 0) {
            return true;
        } else if (year % 400 > 0) {
            return false;
        } else {
            return true;
        }
    }
}
