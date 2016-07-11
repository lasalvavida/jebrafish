package com.lasalvavida.jebrafish.math;

import java.util.Random;

public class MatrixD extends Matrix<Double> {
    public MatrixD(int rows, int columns, Double... elements) {
        super(rows, columns, elements);
    }

    @Override
    public MatrixD clone() {
        MatrixD clone = new MatrixD(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                clone.set(i, j, get(i, j));
            }
        }
        return clone;
    }

    public void add(Matrix mat) {
        int rows = getRows();
        int columns = getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                set(i, j, getDouble(i, j) + mat.getDouble(i, j));
            }
        }
    }

    public void convolve(Matrix kernel) {
        int rows = getRows();
        int columns = getColumns();
        int kernelRows = kernel.getRows();
        int kernelColumns = kernel.getColumns();
        MatrixD copy = clone();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                double result = 0.0;
                for (int m = -kernelRows/2; m <= kernelRows/2; m++) {
                    for (int n = -kernelColumns/2; n <= kernelColumns/2; n++) {
                        int p = i + m;
                        if (p < 0) {
                            p = 0;
                        } else if (p >= rows) {
                            p = rows - 1;
                        }
                        int q = j + n;
                        if (q < 0) {
                            q = 0;
                        } else if (q >= columns) {
                            q = columns - 1;
                        }
                        result += copy.getDouble(p, q) * kernel.getDouble(m + kernelRows/2, n + kernelColumns/2);
                    }
                }
                set(i, j, result);
            }
        }
    }

    public void boxBlurH(int size) {
        int rows = getRows();
        int columns = getColumns();
        MatrixD copy = clone();
        for (int i = 0; i < rows; i++) {
            double acc = 0;
            acc = copy.get(i, 0) * (size / 2);
            for(int j = 0; j <= size/2; j++) {
                acc += copy.get(i, j);
            }
            for(int j = 0; j < columns; j++) {
                set(i, j, acc / size);
                int n = j - size/2;
                if (n < 0) {
                    n = 0;
                }
                acc -= copy.get(i, n);
                int m = j + size/2 + 1;
                if (m >= columns) {
                    m = columns - 1;
                }
                acc += copy.get(i, m);
            }
        }
    }

    public void boxBlurV(int size) {
        int rows = getRows();
        int columns = getColumns();
        MatrixD copy = clone();
        for (int j = 0; j < columns; j++) {
            double acc = 0;
            acc = copy.get(0, j) * (size / 2);
            for(int i = 0; i <= size/2; i++) {
                acc += copy.get(i, j);
            }
            for(int i = 0; i < rows; i++) {
                set(i, j, acc / size);
                int n = i - size/2;
                if (n < 0) {
                    n = 0;
                }
                acc -= copy.get(n, j);
                int m = i + size/2 + 1;
                if (m >= rows) {
                    m = rows - 1;
                }
                acc += copy.get(m, j);
            }
        }
    }

    public void boxBlur(int size) {
        boxBlurH(size);
        boxBlurV(size);
    }

    public boolean equalsEpsilon(MatrixD matrix, double epsilon) {
        int rows = getRows();
        int columns = getColumns();
        if (rows != matrix.getRows()) {
            return false;
        }
        if (columns != matrix.getColumns()) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Math.abs(getDouble(i, j) - matrix.getDouble(i, j)) > epsilon) {
                    return false;
                }
            }
        }
        return true;
    }

    private final static Random randomGenerator = new Random();
    public static MatrixD random(int rows, int columns, double min, double max) {
        MatrixD mat = new MatrixD(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat.set(i, j, min + (max - min) * randomGenerator.nextDouble());
            }
        }
        return mat;
    }
}
