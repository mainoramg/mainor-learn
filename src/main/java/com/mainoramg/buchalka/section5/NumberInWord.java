package com.mainoramg.buchalka.section5;

public class NumberInWord {

    public static void main(String[] args) {
        printNumberInWord(0);
        printNumberInWord(5);
        printNumberInWord(-3);
    }

    public static void printNumberInWord(int number){
        String numberName = "";
        switch (number){
            case 0:
                numberName = "ZERO";
                break;
            case 1:
                numberName = "ONE";
                break;
            case 2:
                numberName = "TWO";
                break;
            case 3:
                numberName = "THREE";
                break;
            case 4:
                numberName = "FOUR";
                break;
            case 5:
                numberName = "FIVE";
                break;
            case 6:
                numberName = "SIX";
                break;
            case 7:
                numberName = "SEVEN";
                break;
            case 8:
                numberName = "EIGHT";
                break;
            case 9:
                numberName = "NINE";
                break;
            default:
                numberName = "OTHER";
        }
        System.out.println(numberName);
    }
}
