package com.nan.progra1.lab2;

import com.nan.progra1.lab2.models.Point;
import com.nan.progra1.lab2.models.shapes.Shape;
import com.nan.progra1.lab2.models.shapes.threedimensions.Cube;
import com.nan.progra1.lab2.models.shapes.threedimensions.Cylinder;
import com.nan.progra1.lab2.models.shapes.threedimensions.Sphere;
import com.nan.progra1.lab2.models.shapes.twodimensions.Rectangle;
import com.nan.progra1.lab2.models.shapes.twodimensions.RightTriangle;
import com.nan.progra1.lab2.models.shapes.twodimensions.Square;
import com.nan.progra1.lab2.models.shapes.twodimensions.TwoDimensionsShape;

public class ShapesController {

    public static void showShape(Shape shape) {
        IOManager.showMessage(shape.toString());
        if (shape instanceof TwoDimensionsShape) {
            ((TwoDimensionsShape) shape).draw();
        }
    }

    public static Square createSquare() {
        Point point = requestDataForPoint();
        int sideLength = IOManager.showInputForm("Por favor digite el largo del lado (mayor a cero):", false);

        return new Square(point, sideLength);
    }

    public static Rectangle createRectangle() {
        Point point1 = requestDataForPoint();
        Point point3 = requestDataForPoint();

        while (!validRectanglePoints(point1, point3)) {
            IOManager.showMessage("Punto 3 para el rectangulo invalido. Vuelva a proporcionar un Punto 3.");
            point3 = requestDataForPoint();
        }

        return new Rectangle(point1, point3);
    }

    public static RightTriangle createRightTriangle() {
        Point point1 = requestDataForPoint();
        Point point2 = requestDataForPoint();

        while (!validRightTriangle(point1, point2)) {
            IOManager.showMessage("Punto 2 para el triangulo rectangulo invalido. Vuelva a proporcionar un Punto 2.");
            point2 = requestDataForPoint();
        }

        return new RightTriangle(point1, point2);
    }

    public static Cube createCube() {
        int sideLength = IOManager.showInputForm("Por favor digite el largo del lado (mayor a cero):", false);

        return new Cube(sideLength);
    }

    public static Sphere createSphere() {
        int radius = IOManager.showInputForm("Por favor digite el radio (mayor a cero):", false);

        return new Sphere(radius);
    }

    public static Cylinder createCylinder() {
        int height = IOManager.showInputForm("Por favor digite la altura (mayor a cero):", false);
        int radius = IOManager.showInputForm("Por favor digite el radio (mayor a cero):", false);

        return new Cylinder(height, radius);
    }

    private static boolean validRightTriangle(Point point1, Point point2) {
        return point2.getX() > point1.getX() && point2.getY() > point1.getY();
    }

    private static boolean validRectanglePoints(Point point1, Point point3) {
        if (point3.getX() > point1.getX() && point3.getY() > point1.getY()) {
            return !(point3.getX() - point1.getX() == point3.getY() - point1.getY());
        } else  {
            return false;
        }
    }

    private static Point requestDataForPoint() {
        int x = IOManager.showInputForm("Por favor digite el valor X:", true);
        int y = IOManager.showInputForm("Por favor digite el valor Y:", true);

        return new Point(x, y);
    }
}
