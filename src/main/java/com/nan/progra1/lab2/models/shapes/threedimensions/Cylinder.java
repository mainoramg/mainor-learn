package com.nan.progra1.lab2.models.shapes.threedimensions;

public class Cylinder extends ThreeDimensionsShape {

    private int height;
    private int radius;

    public Cylinder(int height, int radius) {
        this.height = height;
        this.radius = radius;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    public double calculateVolume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return "Cilindro: altura " + height + ", radio " + radius;
    }

}
