package com.mainoramg.buchalka.section5;

public class SumOddRange {

    public static void main(String[] args) {
        System.out.println(sumOdd(1, 100));
        System.out.println(sumOdd(-1, 100));
        System.out.println(sumOdd(100, 100));
        System.out.println(sumOdd(13, 13));
        System.out.println(sumOdd(100, -100));
        System.out.println(sumOdd(100, 1000));
    }

    public static int sumOdd(int start, int end) {
        if (start > 0 && end > 0 && start <= end) {
            int sum = 0;
            for (; start <= end; start++) {
                sum += (isOdd(start)) ? start : 0;
            }
            return sum;
        }
        else {
            return -1;
        }
    }

    public static boolean isOdd(int number) {
        if (number > 0) {
            return number % 2 > 0;
        }

        return false;
    }
}
