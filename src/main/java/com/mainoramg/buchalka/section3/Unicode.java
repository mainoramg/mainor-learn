package com.mainoramg.buchalka.section3;

public class Unicode {

    public static void main(String[] args) {
        char myChar = 'D';
        char myUnicodeChar = '\u0044';
        System.out.println(myChar);
        System.out.println(myUnicodeChar);
        char myCopyrightChar = '\u00A9';
        System.out.println(myCopyrightChar);
        String myCopyrightString = " \u00A9 2022";
        System.out.println(myCopyrightString);
    }
}
