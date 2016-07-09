package com.lasalvavida.javacv.math.test;

import com.lasalvavida.javacv.math.Point2;
import org.junit.Test;
import static org.junit.Assert.*;

public class Point2Test {
    @Test public void setX() {
        Point2<Double> p = new Point2<>(1.0, 1.0);
        p.setX(2.0);
        assertTrue("setX changes 'x' value", p.getX() == 2.0);
    }

    @Test public void setY() {
        Point2<Double> p = new Point2<>(1.0, 1.0);
        p.setY(2.0);
        assertTrue("setY changes 'y' value", p.getY() == 2.0);
    }

    @Test public void distance() {
        Point2<Double> p = new Point2<>(3.0, 4.0);
        assertTrue("distance from origin correctly calculated", p.distance(Point2.ORIGIN) == 5.0);
    }
}
