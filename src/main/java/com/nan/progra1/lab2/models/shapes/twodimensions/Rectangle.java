package com.nan.progra1.lab2.models.shapes.twodimensions;

import com.nan.progra1.lab2.DrawingPanel;
import com.nan.progra1.lab2.models.Point;

/**
 * A rectangle is the union between four points in the coordinate space that
 * form an rectangle shape.
 *
 * @author Wendy Ram&iacute;rez
 */
public class Rectangle extends TwoDimensionsShape {

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    public Rectangle(Point point1, Point point3) {
        this.point1 = point1;
        this.point2 = new Point(point3.getX(), point1.getY());
        this.point3 = point3;
        this.point4 = new Point(point1.getX(), point3.getY());
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

    public Point getPoint4() {
        return point4;
    }

    public void setPoint4(Point point4) {
        this.point4 = point4;
    }

    /**
     * Calculates the area of ​​the rectangle using the formula 'width *
     * height', where 'width' corresponds to the distance between point1 and
     * point2 and where 'height' corresponds to the distance between point1 and
     * point3
     *
     * @return the area of ​​the rectangle
     */
    @Override
    public double calculateArea() {
        return Point.calculetePointsDistance(point1, point2)
                * Point.calculetePointsDistance(point2, point3);
    }

    /**
     * Calculates the perimeter of the rectangle by adding the distance between
     * its four point attributes.
     *
     * @return the perimeter of ​​the rectangle
     */
    @Override
    public double calculatePerimeter() {
        double width = Point.calculetePointsDistance(point1, point2);
        double height = Point.calculetePointsDistance(point2, point3);
        return width * 2 + height * 2;
    }

    @Override
    public String toString() {
        return "Rect\u00E1ngulo: punto 1 " + point1 + ", punto 2 " + point2
                + ", punto 3 " + point3 + ", punto 4 " + point4;
    }

    @Override
    public void draw() {
        DrawingPanel.draw(point1, point2, point3, point4);
    }

}
