package com.mainoramg.buchalka.section6;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public void add(double real, double imaginary) {
        this.real = this.real + real;
        this.imaginary = this.imaginary + imaginary;
    }

    public void add(ComplexNumber complexNumber) {
        this.real = this.real + complexNumber.getReal();
        this.imaginary = this.imaginary + complexNumber.getImaginary();
    }

    public void subtract(double real, double imaginary) {
        this.real = this.real - real;
        this.imaginary = this.imaginary - imaginary;
    }

    public void subtract(ComplexNumber complexNumber) {
        this.real = this.real - complexNumber.getReal();
        this.imaginary = this.imaginary - complexNumber.getImaginary();
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }
}
