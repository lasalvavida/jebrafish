package com.lasalvavida.jebrafish.math;

public class Point2<T extends Number> extends Point {
    public static Point2<Double> ORIGIN = new Point2(0.0, 0.0);

    public Point2(T x, T y) {
        super(x, y);
    }

    public void set(T x, T y) {
        elements[0] = x;
        elements[1] = y;
    }

    public void setX(T x) {
        elements[0] = x;
    }

    public void setY(T y) {
        elements[1] = y;
    }

    public T getX() {
        return (T)elements[0];
    }

    public T getY() {
        return (T)elements[1];
    }
}
