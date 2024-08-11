package com.mainoramg.buchalka.section5;

public class EvenDigitSum {

    public static void main(String[] args) {
        System.out.println(getEvenDigitSum(123456789)); // should return 20 since 2 + 4 + 6 + 8 = 20
        System.out.println(getEvenDigitSum(252)); // should return 4 since 2 + 2 = 4
        System.out.println(getEvenDigitSum(-22)); // should return -1 since the number is negative
    }

    public static int getEvenDigitSum(int number) {
        if (number < 0) {
            return -1;
        } else if (number < 10) {
            return number % 2 == 0 ? number : 0;
        } else {
            int sum = 0;
            int n = number;
            for (;n >= 10; n = n / 10) {
                int lastDigit = n % 10;

                sum += lastDigit % 2 == 0 ? lastDigit : 0;
            }

            return n % 2 == 0 ? sum + n : sum;
        }
    }
}
