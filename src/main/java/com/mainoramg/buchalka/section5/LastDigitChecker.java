package com.mainoramg.buchalka.section5;

public class LastDigitChecker {

    public static void main(String[] args) {
        System.out.println(hasSameLastDigit (41, 22, 71)); // should return true since 1 is the rightmost digit in numbers 41 and 71
        System.out.println(hasSameLastDigit (23, 32, 42)); // should return true since 2 is the rightmost digit in numbers 32 and 42
        System.out.println(hasSameLastDigit (9, 99, 999)); // should return false since 9 is not within the range of 10-1000
        System.out.println(isValid(10)); // should return true since 10 is within the range of 10-1000
        System.out.println(isValid(468)); // should return true since 468 is within the range of 10-1000
        System.out.println(isValid(1051)); // should return false since 1051 is not within the range of 10-1000
    }

    public static boolean hasSameLastDigit(int n1, int n2, int n3) {
        if (isValid(n1) && isValid(n2) && isValid(n3)) {
            int lastDigitN1 = n1 % 10;
            int lastDigitN2 = n2 % 10;
            int lastDigitN3 = n3 % 10;

            return lastDigitN1 == lastDigitN2
                    || lastDigitN1 == lastDigitN3
                    || lastDigitN2 == lastDigitN3;
        }
        else {
            return false;
        }
    }

    public static boolean isValid(int number) {
        return number >= 10 && number <= 1000;
    }
}
