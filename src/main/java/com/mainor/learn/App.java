package com.mainor.learn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( "Test for lastFour(456789, 1254): " + PlayingWithString.lastFour("456789", "1254") );
        System.out.println( "Test for lastFour(456789, ): " + PlayingWithString.lastFour("456789", "") );
        System.out.println( "Test for lastFour(456789, null): " + PlayingWithString.lastFour("456789", null) );
    }
}
