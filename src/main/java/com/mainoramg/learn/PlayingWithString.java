package com.mainoramg.learn;

public class PlayingWithString {

    public static String lastFour(String bin, String l4) {
        l4 = l4 == null ? "" : l4;
        bin = bin == null ? "" : bin;
        return bin + "################".substring(l4.length() + bin.length()) + l4;
    }

    public static void main(String[] args) {
        System.out.println( "Test for lastFour(456789, 1254): " + PlayingWithString.lastFour("456789", "1254") );
        System.out.println( "Test for lastFour(456789, ): " + PlayingWithString.lastFour("456789", "") );
        System.out.println( "Test for lastFour(456789, null): " + PlayingWithString.lastFour("456789", null) );

        String downloads = "[test]";
        downloads = downloads.substring(1);
        downloads = downloads.substring(0, downloads.length() - 1);
        System.out.println("downloads="+downloads);
    }
}
