package com.lasalvavida.jebrafish.math;

public class Kernel {
    public final static MatrixD BOX3 = new MatrixD(3, 3,
            1.0/9.0, 1.0/9.0, 1.0/9.0,
            1.0/9.0, 1.0/9.0, 1.0/9.0,
            1.0/9.0, 1.0/9.0, 1.0/9.0);

    public final static MatrixD GAUSSIAN3 = new MatrixD(3, 3,
            1.0/16.0, 1.0/8.0, 1.0/16.0,
            1.0/8.0, 1.0/4.0, 1.0/8.0,
            1.0/16.0, 1.0/8.0, 1.0/16.0);
}
