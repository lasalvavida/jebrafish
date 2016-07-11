package com.lasalvavida.jebrafish.math;

public class Matrix<T extends Number> {
    protected T[] elements;
    protected int rows, columns;

    public Matrix(int rows, int columns, T ... elements) {
        this.rows = rows;
        this.columns = columns;
        if (elements.length == 0) {
            this.elements = (T[])new Number[rows * columns];
        } else {
            this.elements = elements;
        }
    }

    public Matrix(int rows, int columns, T value) {
        this(rows, columns);
        fill(value);
    }

    @Override
    public Matrix<T> clone() {
        Matrix<T> clone = new Matrix<>(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                clone.set(i, j, get(i, j));
            }
        }
        return clone;
    }

    public T get(int row, int column) {
        return elements[row * columns + column];
    }

    public double getDouble(int row, int column) {
        return get(row, column).doubleValue();
    }

    public void set(int row, int column, T value) {
        elements[row * columns + column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public T[] getElements() {
        return elements;
    }

    public void fill(T value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                set(i, j, value);
            }
        }
    }

    public void transpose() {
        int rows = getRows();
        int columns = getColumns();
        T temp;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                temp = get(i, j);
                set(i, j, get(j, i));
                set(j, i, temp);
            }
        }
    }

    public void swapRows(int row1, int row2) {
        int columns = getColumns();
        T temp;
        for (int j = 0; j < columns; j++) {
            temp = get(row1, j);
            set(row1, j, get(row2, j));
            set(row2, j, temp);
        }
    }

    public boolean isSquare() {
        return getRows() == getColumns();
    }

    public double determinant() {
        if(!isSquare()) {
            throw new RuntimeException("Cannot calculate the determinant of a non-square matrix");
        }
        int size = getRows();
        if (size == 2) {
            double a = getDouble(0, 0);
            double b = getDouble(0, 1);
            double c = getDouble(1, 0);
            double d = getDouble(1, 1);
            return a * d - b * c;
        } else if (size == 3) {
            double a = getDouble(0, 0);
            double b = getDouble(0, 1);
            double c = getDouble(0, 2);
            double d = getDouble(1, 0);
            double e = getDouble(1, 1);
            double f = getDouble(1, 2);
            double g = getDouble(2, 0);
            double h = getDouble(2, 1);
            double i = getDouble(2, 2);
            return a * e * i +
                    b * f * g +
                    c * d * h -
                    g * e * c -
                    h * f * a -
                    i * d * b;
        } else {
            double determinant = 0.0;
            for (int r = 0; r < size; r++) {
                SubMatrix<T> subMatrix = new SubMatrix<>(this);
                double scale = subMatrix.getDouble(0, r) * (((r % 2) * -2) + 1);
                subMatrix.excludeRow(0);
                subMatrix.excludeColumn(r);
                determinant += scale * subMatrix.determinant();
            }
            return determinant;
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Matrix && equals((Matrix) o);
    }

    public boolean equals(Matrix m) {
        int rows = getRows();
        int columns = getColumns();
        if (rows != m.getRows()) {
            return false;
        }
        if (columns != m.getColumns()) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!get(i, j).equals(m.get(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Matrix<Double> identity(int size) {
        Double[] elements = new Double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    elements[i * size + j] = 1.0;
                } else {
                    elements[i * size + j] = 0.0;
                }
            }
        }
        return new Matrix<>(size, size, elements);
    }
}
