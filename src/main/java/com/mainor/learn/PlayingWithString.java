package com.mainor.learn;

public class PlayingWithString {

    public static String lastFour(String bin, String l4) {
        l4 = l4 == null ? "" : l4;
        bin = bin == null ? "" : bin;
        return bin + "################".substring(l4.length() + bin.length()) + l4;
    }
}
