package com.mainoramg.buchalka.section5;

public class NumberPalindrome {

    public static void main(String[] args) {
        isPalindrome(0);
        isPalindrome(3);
        isPalindrome(10);
        isPalindrome(12);
        isPalindrome(99);
        isPalindrome(100);
        isPalindrome(121);
        isPalindrome(1024);
        isPalindrome(3993);
        isPalindrome(3998);
        isPalindrome(45654);
    }

    public static boolean isPalindrome(int number) {
        number = number < 0 ? number * -1 : number;
        int reversed = 0;
        int n = number;
        for (; n >= 10; n = n / 10) {
            int lastDigit = n % 10;
            reversed = (reversed * 10) + lastDigit;
        }
        reversed = (reversed * 10) + n;

        return number == reversed;
    }
}
