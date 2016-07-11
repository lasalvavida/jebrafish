package com.lasalvavida.jebrafish.app;

import com.lasalvavida.jebrafish.image.Image;
import com.lasalvavida.jebrafish.math.Kernel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/Lenna.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            Image process = new Image(image);
            process.convolve(Kernel.GAUSSIAN3);
            process.convolve(Kernel.GAUSSIAN3);
            process.convolve(Kernel.GAUSSIAN3);
            image = process.toBufferedImage();
        }
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
