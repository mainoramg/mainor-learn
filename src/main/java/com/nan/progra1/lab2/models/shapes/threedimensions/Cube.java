package com.nan.progra1.lab2.models.shapes.threedimensions;

public class Cube extends ThreeDimensionsShape {

    private int side;

    public Cube(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return 6 * side * side;
    }

    @Override
    public double calculateVolume() {
        return side * side * side;
    }

    @Override
    public String toString() {
        return "Cubo: lado " + side;
    }

}
