package com.lasalvavida.jebrafish.math;

public class Vector2<T extends Number> extends Vector {
    public static Vector2<Double> UNIT_I = new Vector2(1.0, 0.0);
    public static Vector2<Double> UNIT_J = new Vector2(0.0, 1.0);

    public Vector2(T i, T j) { super(i, j); }
}
