package com.lasalvavida.jebrafish.image;

import com.lasalvavida.jebrafish.math.MatrixD;

import java.awt.image.BufferedImage;

public class Image {
    private int colorspace;
    protected MatrixD[] channels;
    private int width, height;

    public Image(int width, int height) {
        channels = new MatrixD[3];
        this.width = width;
        this.height = height;
    }

    public Image(BufferedImage image) {
        this(image.getWidth(), image.getHeight());
        MatrixD red = new MatrixD(height, width);
        MatrixD green = new MatrixD(height, width);
        MatrixD blue = new MatrixD(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int[] rgb = ColorSpace.splitRgb(image.getRGB(i, j));
                red.set(i, j, rgb[0] / 255.0);
                green.set(i, j, rgb[1] / 255.0);
                blue.set(i, j, rgb[2] / 255.0);
            }
        }
        channels[0] = red;
        channels[1] = green;
        channels[2] = blue;
        colorspace = ColorSpace.RGB;
    }

    @Override
    public Image clone() {
        Image clone = new Image(width, height);
        for (int channel = 0; channel < 3; channel++) {
            clone.channels[channel] = channels[channel].clone();
        }
        return clone;
    }

    public MatrixD getChannel(int channel) {
        return channels[channel];
    }

    public int getColorspace() {
        return colorspace;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void boxBlur(int size) {
        for (int channel = 0; channel < 3; channel++) {
            channels[channel].boxBlur(size);
        }
    }

    public void convolve(MatrixD kernel) {
        for (int channel = 0; channel < 3; channel++) {
            channels[channel].convolve(kernel);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Image) {
            Image image = (Image)o;
            if (image.getWidth() != getWidth() || image.getHeight() != getHeight()) {
                return false;
            }
            for (int channel = 0; channel < 3; channel++) {
                if (!getChannel(channel).equals(image.getChannel(channel))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean equalsEpsilon(Image image, double epsilon) {
        if (image.getWidth() != getWidth() || image.getHeight() != getHeight()) {
            return false;
        }
        for (int channel = 0; channel < 3; channel++) {
            if (!getChannel(channel).equalsEpsilon(image.getChannel(channel), epsilon)) {
                return false;
            }
        }
        return true;
    }

    public BufferedImage toBufferedImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        double[] color = new double[3];
        int[] intColor = new int[3];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int channel = 0; channel < 3; channel++) {
                    color[channel] = channels[channel].getDouble(i, j);
                }
                ColorSpace.convertColor(color, colorspace, ColorSpace.RGB);
                intColor[0] = (int)(color[0] * 255);
                intColor[1] = (int)(color[1] * 255);
                intColor[2] = (int)(color[2] * 255);
                image.setRGB(i, j, ColorSpace.combineRgb(intColor));
            }
        }
        return image;
    }
}
