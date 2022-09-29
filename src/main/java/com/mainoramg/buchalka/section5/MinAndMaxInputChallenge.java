package com.mainoramg.buchalka.section5;

import java.util.Scanner;

public class MinAndMaxInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 0; // better value: Integer.MAX_VALUE;
        int max = 0; // better value: Integer.MIN_VALUE
        boolean first = true;
        while (true) {
            System.out.println("Enter number:");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (first) {
                    min = input;
                    max = input;
                    first = false;
                } else {
                    min = input < min ? input : min;
                    max = input > max ? input : max;
                }
            } else {
                break;
            }
        }
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        scanner.close();
    }
}
