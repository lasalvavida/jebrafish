package com.lasalvavida.jebrafish.math.test;

import com.lasalvavida.jebrafish.math.Point3;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Point3Test {
    @Test
    public void setX() {
        Point3<Double> p = new Point3<>(1.0, 1.0, 1.0);
        p.setX(2.0);
        assertTrue("setX changes 'x' value", p.getX() == 2.0);
    }

    @Test public void setY() {
        Point3<Double> p = new Point3<>(1.0, 1.0, 1.0);
        p.setY(2.0);
        assertTrue("setY changes 'y' value", p.getY() == 2.0);
    }

    @Test public void setZ() {
        Point3<Double> p = new Point3<>(1.0, 1.0, 1.0);
        p.setZ(2.0);
        assertTrue("setZ changes 'z' value", p.getZ() == 2.0);
    }

    @Test public void distance() {
        Point3<Double> p = new Point3<>(2.0, 3.0, 6.0);
        assertTrue("distance from origin correctly calculated", p.distance(Point3.ORIGIN) == 7.0);
    }
}
