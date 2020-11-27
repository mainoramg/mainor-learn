package com.nan.progra1.lab2.models;

/**
 * A point representing a location in the coordinate space (x, y). It can be
 * used to form different figures.
 *
 * @author Wendy Ram&iacute;rez
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Calculates the distance between two points using Pythagoras' formula
     *
     * @param point1 the first point of the location
     * @param point2 the second point of the location
     * @return the distance in pixels between point1 and point2
     */
    public static double calculetePointsDistance(Point point1, Point point2) {
        return Math.hypot((point1.x - point2.x), (point1.y - point2.y));
    }
}
