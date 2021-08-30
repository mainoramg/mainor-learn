package com.mainoramg.buchalka;

public class TeenNumberChecker {

    public static void main(String[] args) {
        System.out.println(hasTeen(9, 99, 19)); // should return true since 19 is in range 13 - 19
        System.out.println(hasTeen(23, 15, 42)); // should return true since 15 is in range 13 - 19
        System.out.println(hasTeen(22, 23, 34)); // should return false since numbers 22, 23, 34 are not in range 13-19
    }

    public static boolean hasTeen(int n1, int n2, int n3) {
        return isTeen(n1) || isTeen(n2) || isTeen(n3);
    }

    public static boolean isTeen(int number) {
        return number >= 13 && number <= 19;
    }
}
