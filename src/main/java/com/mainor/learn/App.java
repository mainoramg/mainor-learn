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
//        System.out.println( "Test for lastFour(456789, 1254): " + PlayingWithString.lastFour("456789", "1254") );
//        System.out.println( "Test for lastFour(456789, ): " + PlayingWithString.lastFour("456789", "") );
//        System.out.println( "Test for lastFour(456789, null): " + PlayingWithString.lastFour("456789", null) );

//        PlayingWithEnum test = new PlayingWithEnum();
//        System.out.println( "isVoid true: " + test.letsChangeTheType(true, PlayingWithEnum.Type.VOID).toString() );
//        System.out.println( "isVoid false: " + test.letsChangeTheType(false, PlayingWithEnum.Type.VOID).toString() );

        MapToHtml mapToHtml = new MapToHtml();
        System.out.println(mapToHtml.mapToHtml(mapToHtml.getData()));

        String downloads = "[test]";
        downloads = downloads.substring(1);
        downloads = downloads.substring(0, downloads.length() - 1);
        System.out.println("downloads="+downloads);
    }
}
