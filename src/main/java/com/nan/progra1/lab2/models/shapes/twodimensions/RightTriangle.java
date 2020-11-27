package com.nan.progra1.lab2.models.shapes.twodimensions;

import com.nan.progra1.lab2.DrawingPanel;
import com.nan.progra1.lab2.models.Point;

/**
 * A right triangle is the union between three points in the coordinate space
 * that form an right triangle shape.
 *
 * @author Wendy Ram&iacute;rez
 */
public class RightTriangle extends TwoDimensionsShape {

    private Point point1;
    private Point point2;
    private Point point3;

    public RightTriangle(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = new Point(point1.getX(), point2.getY());
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    /**
     * Calculates the area of ​​the right triangle using the formula 'base *
     * height', where 'base' corresponds to the distance between point2 and
     * point3, and where 'height' corresponds to the distance between point1 and
     * point2
     *
     * @return the area of ​​the right triangle
     */
    @Override
    public double calculateArea() {
        double height = Point.calculetePointsDistance(point1, point3);
        double base = Point.calculetePointsDistance(point2, point3);
        return height * base;
    }

    /**
     * Calculates the perimeter of the right triangle by adding the distance
     * between its three point attributes.
     *
     * @return the perimeter of ​​the right triangle
     */
    @Override
    public double calculatePerimeter() {
        return Point.calculetePointsDistance(point1, point2)
                + Point.calculetePointsDistance(point2, point3)
                + Point.calculetePointsDistance(point3, point1);
    }

    @Override
    public String toString() {
        return "Tri\u00E1ngulo rect\u00E1ngulo: punto 1 " + point1 + ", punto 2 " + point2
                + ", punto 3 " + point3;
    }

    @Override
    public void draw() {
        DrawingPanel.draw(point1, point2, point3);
    }

}
