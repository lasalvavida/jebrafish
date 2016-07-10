package com.lasalvavida.jebrafish.math;

public class Point3<T extends Number> extends Point {
    public static Point3<Double> ORIGIN = new Point3(0.0, 0.0, 0.0);

    public Point3(T x, T y, T z) {
        super(x, y, z);
    }

    public void set(T x, T y, T z) {
        elements[0] = x;
        elements[1] = y;
        elements[2] = z;
    }

    public void setX(T x) {
        elements[0] = x;
    }

    public void setY(T y) {
        elements[1] = y;
    }

    public void setZ(T z) { elements[2] = z; }

    public T getX() {
        return (T)elements[0];
    }

    public T getY() {
        return (T)elements[1];
    }

    public T getZ() { return (T)elements[2];}
}
