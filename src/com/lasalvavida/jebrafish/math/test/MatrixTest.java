package com.lasalvavida.jebrafish.math.test;

import com.lasalvavida.jebrafish.math.Matrix;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MatrixTest {
    @Rule public final ExpectedException exception = ExpectedException.none();

    @Test
    public void identity() {
        Matrix<Double> identity = new Matrix<Double>(4, 4,
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0);
        Matrix<Double> genIdentity = Matrix.identity(4);
        assertTrue("identity generates correct identity matrix", identity.equals(genIdentity));
    }

    @Test
    public void transpose() {
        Matrix<Integer> original = new Matrix<Integer>(4, 4,
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15);
        Matrix<Integer> transpose = new Matrix<Integer>(4, 4,
                0, 4, 8, 12,
                1, 5, 9, 13,
                2, 6, 10, 14,
                3, 7, 11, 15);
        original.transpose();
        assertTrue("transpose correctly transposes a matrix", original.equals(transpose));
    }

    @Test
    public void swapRows() {
        Matrix<Integer> original = new Matrix<>(4, 4,
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15);
        Matrix<Integer> swapped = new Matrix<>(4, 4,
                8, 9, 10, 11,
                4, 5, 6, 7,
                12, 13, 14, 15,
                0, 1, 2, 3);
        original.swapRows(0, 3);
        original.swapRows(2, 0);
        assertTrue("swapRows correctly swaps rows", original.equals(swapped));
    }

    @Test public void determinant() {
        Matrix<Integer> nonSquare = new Matrix<>(3, 2);
        boolean threwException = false;
        try {
            nonSquare.determinant();
        } catch (Exception e) {
            threwException = true;
        }
        assertTrue("determinant throws exception for non-square matrices", threwException);

        Matrix<Integer> test2x2 = new Matrix<>(2, 2,
                1, 2,
                3, 4);
        double determinant2x2 = test2x2.determinant();
        assertTrue("determinant calculated correctly for 2x2 matrix", determinant2x2 == -2.0);

        Matrix<Integer> test3x3 = new Matrix<>(3, 3,
                5, 6, 1,
                -1, 3, 2,
                9, -2, 5);
        double determinant3x3 = test3x3.determinant();
        assertTrue("determinant calculated correctly for 3x3 matrix", determinant3x3 == 208.0);

        Matrix<Integer> test4x4 = new Matrix<>(4, 4,
                -12, -5, -6, -4,
                -16, -25, 12, 9,
                -1, -18, 6, -20,
                -19, 16, 4, 0);
        double determinant4x4 = test4x4.determinant();
        assertTrue("determinant calculated correctly for 4x4 matrix", determinant4x4 == 221194.0);

        Matrix<Integer> test5x5 = new Matrix<>(5, 5,
                -24, -16, 17, -18, -21,
                22, -19, -5, 3, 15,
                8, 8, -9, -15, 0,
                7, -2, -13, -1, 19,
                -8, -2, -20, -18, -4);
        double determinant5x5 = test5x5.determinant();
        assertTrue("determinant calculated correctly for 5x5 matrix", determinant5x5 == 4925712.0);
    }
}
