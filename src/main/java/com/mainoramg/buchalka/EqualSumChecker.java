package com.mainoramg.buchalka;

public class EqualSumChecker {

    public static void main(String[] args) {
        System.out.println(hasEqualSum(1, 1, 1)); // should return false since 1 + 1 is not equal to 1
        System.out.println(hasEqualSum(1, 1, 2)); // should return true since 1 + 1 is equal to 2
        System.out.println(hasEqualSum(1, -1, 0)); // should return true since 1 + (-1) is 1 - 1 and is equal to 0
    }

    public static boolean hasEqualSum(int n1, int n2, int n3) {
        int n1PlusN2 = n1 + n2;
        return n1PlusN2 == n3;
    }
}
