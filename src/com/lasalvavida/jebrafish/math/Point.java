package com.lasalvavida.jebrafish.math;

public class Point<T extends Number> {
    protected T[] elements;
    public Point(T ... elements) {
        this.elements = elements;
    }

    public Point(int size) {
        elements = (T[])new Number[size];
    }

    public int size() {
        return elements.length;
    }

    public T[] getElements() {
        return elements;
    }

    public Point<T> clone() {
        return new Point<T>(elements);
    }

    public double distance(Point p) {
        double sum = 0.0;
        double diff;
        Number[] elements = p.getElements();
        for (int i = 0; i < elements.length; i++) {
            diff = this.elements[i].doubleValue() - elements[i].doubleValue();
            diff *= diff;
            sum += diff;
        }
        return Math.sqrt(sum);
    }
}
