package com.lasalvavida.jebrafish.image.test;

import com.lasalvavida.jebrafish.image.Image;
import com.lasalvavida.jebrafish.math.Kernel;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;
import java.io.File;

public class ImageTest {
    Image lenna;
    public ImageTest() {
        try {
            lenna = new Image(ImageIO.read(new File("images/Lenna.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void boxBlur() {
        Image accBoxBlur = lenna.clone();
        accBoxBlur.boxBlur(3);
        Image kernelBoxBlur = lenna.clone();
        kernelBoxBlur.convolve(Kernel.BOX3);
        assertTrue("accumulated and kernel box blurs produce the same image", accBoxBlur.equalsEpsilon(kernelBoxBlur, 1e-6));
    }
}
