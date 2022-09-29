package com.mainoramg.buchalka.section5;

import java.util.Scanner;

public class InputCalculator {

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        double count = 0;
        long roundedAvg = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                sum += input;
                count++;
            } else {
                break;
            }
        }

        if (count > 0) {
            double avg = sum/count;
            roundedAvg = Math.round(avg);
        }

        System.out.println("SUM = " + sum + " AVG = " + roundedAvg);

        scanner.close();
    }
}
