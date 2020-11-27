package com.nan.progra1.timbiriche;

public class Engine {

    public static void main(String[] args) {
        int[] matrix = new int[10];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = 0;
        }

        printMatrix(engine(matrix, 1, 2));
    }

    static void printMatrix(int[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
        System.out.println();
    }

    static boolean isMatrixFull(int[] matrix) {
        boolean full = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == 0) {
                full = false;
                break;
            }
        }
        return full;
    }

    static int[] engine(int[] matrix, int playerMoving, int playerWaiting) {
        if (isMatrixFull(matrix)) {
            return matrix;
        }
        boolean playerScored = performMove(matrix, playerMoving);
        printMatrix(matrix);
        return playerScored ? engine(matrix, playerMoving, playerWaiting) : engine(matrix, playerWaiting, playerMoving);
    }

    private static boolean performMove(int[] matrix, int playerMoving) {
        boolean result = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == 0) {
                matrix[i] = playerMoving;
                if (i == 2 || i == 5 || i == 8) {
                    result = true;
                }
                break;
            }
        }
        return result;
    }
}
