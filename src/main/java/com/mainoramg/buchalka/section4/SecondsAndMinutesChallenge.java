package com.mainoramg.buchalka.section4;

public class SecondsAndMinutesChallenge {

    public static void main(String[] args) {
        System.out.println(getDurationString(61, 59));
        System.out.println(getDurationString(125));
    }

    public static String getDurationString(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59) {
            return "Invalid value";
        }

        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        // "XXh YYm ZZs"
        String result = "";
        result += hours < 10 ? "0" : "";
        result += hours + "h ";
        result += remainingMinutes < 10 ? "0" : "";
        result += remainingMinutes + "m ";
        result += seconds < 10 ? "0" : "";
        result += seconds + "s";

        return result;
    }

    public static String getDurationString(int seconds) {
        if (seconds < 0) {
            return "Invalid value";
        }

        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return getDurationString(minutes, remainingSeconds);
    }
}
