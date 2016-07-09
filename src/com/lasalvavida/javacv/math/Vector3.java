package com.lasalvavida.javacv.math;

public class Vector3<T extends Number> extends Vector {
    public static Vector3<Double> UNIT_I = new Vector3(1.0, 0.0, 0.0);
    public static Vector3<Double> UNIT_J = new Vector3(0.0, 1.0, 0.0);
    public static Vector3<Double> UNIT_K = new Vector3(0.0, 0.0, 1.0);

    public Vector3(T i, T j, T k) { super(i, j, k); }
}
