package com.mainoramg.buchalka.section5;

public class SharedDigit {

    public static void main(String[] args) {
        System.out.println(hasSharedDigit(12, 23)); // should return true since the digit 2 appears in both numbers
        System.out.println(hasSharedDigit(9, 99)); // should return false since 9 is not within the range of 10-99
        System.out.println(hasSharedDigit(15, 55)); // should return true since the digit 5 appears in both numbers
    }

    public static boolean hasSharedDigit(int n1, int n2) {
        if (n1 < 10 || n1 > 99 || n2 < 10 || n2 > 99) {
            return false;
        }

        int firstDigitN1 = n1 / 10;
        int lastDigitN1 = n1 % 10;
        int firstDigitN2 = n2 / 10;
        int lastDigitN2 = n2 % 10;

        return firstDigitN1 == firstDigitN2
                || firstDigitN1 == lastDigitN2
                || lastDigitN1 == firstDigitN2
                || lastDigitN1 == lastDigitN2;
    }
}
