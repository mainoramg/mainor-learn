package com.mainoramg.buchalka.section5;

public class FirstLastDigitSum {

    public static void main(String[] args) {
        System.out.println(sumFirstAndLastDigit(252)); // 4
        System.out.println(sumFirstAndLastDigit(257)); // 9
        System.out.println(sumFirstAndLastDigit(0)); // 0
        System.out.println(sumFirstAndLastDigit(5)); // 10
        System.out.println(sumFirstAndLastDigit(-10)); // -1
    }

    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1;
        } else if (number < 10) {
            return number + number;
        } else {
            int n = number;
            for (;n >= 10; n = n / 10);

            return n + (number % 10);
        }
    }
}
