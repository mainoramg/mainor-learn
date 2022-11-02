package com.mainoramg.buchalka.section8;

import java.util.Scanner;

public class SortedArray {
    public static void main(String[] args) {
        int[] array = getIntegers(5);
        int[] sortedArray = sortIntegers(array);
        printArray(sortedArray);
    }

    public static int[] getIntegers(int arraySize) {
        int[] array = new int[arraySize];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    public static int[] sortIntegers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int temp = 0;
                if (array[i] < array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
