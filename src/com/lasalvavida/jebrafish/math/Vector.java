package com.lasalvavida.jebrafish.math;

public class Vector<T extends Number> extends Point {
    public Vector(T ... elements) {
        super(elements);
    }

    @Override
    public Vector<T> clone() {
        return new Vector<T>((T[])elements);
    }

    public double magnitude() throws Exception {
        return this.distance(new Point(this.size()));
    }

    public double dot(Vector<Number> v) throws Exception {
        if (v.size() != this.size()) {
            throw new Exception("Cannot find dot product of two vectors with different dimensions.");
        }
        double dotProduct = 0.0;
        for (int i = 0; i < elements.length; i++) {
            dotProduct += elements[i].doubleValue() * v.elements[i].doubleValue();
        }
        return dotProduct;
    }

    public void scale(double scale) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].doubleValue() * scale;
        }
    }

    public void multiply(Vector v) {
        for(int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].doubleValue() * v.elements[i].doubleValue();
        }
    }
}
