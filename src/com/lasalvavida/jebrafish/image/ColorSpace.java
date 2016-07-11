package com.lasalvavida.jebrafish.image;

public class ColorSpace {
    public final static int RGB = 0;
    public final static int HSV = 1;
    public final static int LAB = 2;
    public final static int XYZ = 3;

    public static int[] splitRgb(int rgb) {
        int[] split = new int[3];
        split[0] = (rgb>>16)&0xFF;
        split[1] = (rgb>>8)&0xFF;
        split[2] = rgb&0xFF;
        return split;
    }

    public static int combineRgb(int[] rgb) {
        int value = rgb[0];
        value = (value << 8) + rgb[1];
        value = (value << 8) + rgb[2];
        return value;
    }

    public static double[] rgb2Xyz(int[] rgb) {
        double[] xyz = new double[3];
        for (int i = 0; i < rgb.length; i++) {
            double value = rgb[i] / 255.0;
            if (value > 0.04045) {
                value = Math.pow((value + 0.055)/1.055, 2.4);
            } else {
                value = value / 12.92;
            }
            value = value * 100;
            xyz[i] = value;
        }
        double x = xyz[0] * 0.4124 + xyz[1] * 0.3576 + xyz[2] * 0.1805;
        double y = xyz[0] * 0.2126 + xyz[1] * 0.7152 + xyz[2] * 0.0722;
        double z = xyz[0] * 0.0193 + xyz[1] * 0.1192 + xyz[2] * 0.9505;
        xyz[0] = x;
        xyz[1] = y;
        xyz[2] = z;
        return xyz;
    }

    static double[] XYZ_REF = {95.047, 100.0, 108.883};
    public static double[] xyz2Lab(double[] xyz) {
        double[] lab = new double[3];
        for (int i = 0; i < xyz.length; i++) {
            double value = xyz[i] / XYZ_REF[i];
            if (value > 0.008856) {
                value = Math.pow(value, 1.0/3.0);
            } else {
                value = 7.787*value + 16.0/116.0;
            }
            lab[i] = value;
        }
        double l = 116 * lab[1] - 16;
        double a = 500 * (lab[0] - lab[1]);
        double b = 200 * (lab[1] - lab[2]);
        lab[0] = l;
        lab[1] = a;
        lab[2] = b;
        return lab;
    }

    public static double[] rgb2Lab(int[] rgb) {
        return xyz2Lab(rgb2Xyz(rgb));
    }

    public static double[] lab2Xyz(double[] lab) {
        double[] xyz = new double[3];
        xyz[1] = (lab[0] + 16) / 116.0;
        xyz[0] = lab[1] / 500.0 + xyz[1];
        xyz[2] = xyz[1] - lab[2] / 200.0;
        for (int i = 0; i < xyz.length; i++) {
            double value = xyz[i];
            if (Math.pow(value, 3) > 0.008856) {
                value = Math.pow(value, 3);
            } else {
                value = (value - 16.0 / 116.0) / 7.787;
            }
            value *= XYZ_REF[i];
            xyz[i] = value;
        }
        return xyz;
    }

    public static void xyz2Rgb(double[] xyz) {
        double x = xyz[0] / 100.0;
        double y = xyz[1] / 100.0;
        double z = xyz[2] / 100.0;
        double r = x * 3.2406 + y * -1.5372 + z * -0.4986;
        double g = x * -0.9689 + y * 1.8758 + z * 0.0415;
        double b = x * 0.0557 + y * -0.2050 + z * 1.0570;
        if (r > 0.0031308) {
            r = 1.055 * Math.pow(r, 1.0 / 2.4) - 0.055;
        } else {
            r *= 12.92;
        }
        if (g > 0.0031308) {
            g = 1.055 * Math.pow(g, 1.0 / 2.4) - 0.055;
        } else {
            g *= 12.92;
        }
        if (b > 0.0031308) {
            b = 1.055 * Math.pow(b, 1.0 / 2.4) - 0.055;
        } else {
            b *= 12.92;
        }
        xyz[0] = r;
        xyz[1] = g;
        xyz[2] = b;
    }

    public static void lab2Rgb(double[] lab) {
        lab2Xyz(lab);
        lab2Rgb(lab);
    }

    public static void convertColor(double[] color, int fromColorSpace, int toColorSpace) {
        if (fromColorSpace != toColorSpace) {
            if (fromColorSpace == XYZ && toColorSpace == RGB) {
                xyz2Rgb(color);
            }
            if (fromColorSpace == LAB && toColorSpace == RGB) {
                lab2Rgb(color);
            }
        }
    }
}
