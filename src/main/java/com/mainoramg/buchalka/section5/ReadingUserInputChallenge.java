package com.mainoramg.buchalka.section5;

import java.util.Scanner;

public class ReadingUserInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        int sum = 0;
        while (i<=10) {
            System.out.println("Enter number #" + i + ":");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                sum += input;
                i++;
            } else {
                System.out.println("Invalid Number");
                scanner.next();
            }
        }

        System.out.println("The sum  is: " + sum);
        scanner.close();
    }
}
