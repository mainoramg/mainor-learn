package com.nan.progra1.lab2.models.shapes.threedimensions;

/**
 *
 * @author Wendy Ram&iacute;rez
 */
public class Sphere extends ThreeDimensionsShape {

    private int radius;

    public Sphere(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double calculateVolume() {
        return (4 / 3) * Math.PI * radius * radius * radius;
    }

    @Override
    public String toString() {
        return "Esfera: radio " + radius;
    }

}
