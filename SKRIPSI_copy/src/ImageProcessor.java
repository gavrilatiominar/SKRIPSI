
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gavrila Sianturi
 */
public class ImageProcessor {

    public BufferedImage img;
    public String imgPath;

    public ImageProcessor(String imgPath) throws IOException {
        this.imgPath = imgPath;
        File f = new File(imgPath);
        this.img = ImageIO.read(f);
    }

    public int getImgWidth() {
        return img.getWidth();
    }

    public int getImgHeight() {
        return img.getHeight();
    }

    public int getRedValue(int x, int y) {
        int pixel = this.img.getRGB(x, y);
        Color c = new Color(pixel);
//        int r = (pixel >> 16) & 0xff;
        return c.getRed();
    }

    public int getGreenValue(int x, int y) {
        int pixel = this.img.getRGB(x, y);
        Color c = new Color(pixel);
//        int g = (pixel >> 8) & 0xff;
        return c.getGreen();
    }

    public int getBlueValue(int x, int y) {
        int pixel = this.img.getRGB(x, y);
        Color c = new Color(pixel);
//        int b = pixel & 0xff;
        return c.getBlue();
    }

    public void setRedValue(int r, int x, int y) {
        int g = this.getGreenValue(x, y);
        int b = this.getBlueValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.img.setRGB(x, y, pixel);
    }

    public void setGreenValue(int g, int x, int y) {
        int r = this.getRedValue(x, y);
        int b = this.getBlueValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.img.setRGB(x, y, pixel);
    }

    public void setBlueValue(int b, int x, int y) {
        int r = this.getRedValue(x, y);
        int g = this.getGreenValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.img.setRGB(x, y, pixel);
    }

    public void setPixelValue(int x, int y, int red, int green, int blue) throws IOException {
        int pixel = (red << 16) | (green << 8) | blue;
        this.img.setRGB(x, y, pixel);
    }

    public void createImage() throws IOException {
        File f = new File(this.imgPath.substring(0, this.imgPath.length() - 4) + "Output.bmp");
        ImageIO.write(img, "bmp", f);
    }

    public int[][][] getPixels(BufferedImage img) {
        int[][][] pixel = new int[img.getHeight()][img.getWidth()][3];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pxl = img.getRGB(x, y);
                Color c = new Color(pxl);
                pixel[y][x][0] = c.getRed();
                pixel[y][x][1] = c.getGreen();
                pixel[y][x][2] = c.getBlue();
            }
        }
        return pixel;
    }

    public double calculatePSNR(ImageProcessor stegoImage) {
        double sumR = 0;
        double sumG = 0;
        double sumB = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pxl1 = img.getRGB(x, y);
                int pxl2 = stegoImage.img.getRGB(x, y);
                Color c1 = new Color(pxl1);
                Color c2 = new Color(pxl2);
                sumR = sumR + Math.pow(c1.getRed() - c2.getRed(), 2);
                sumG = sumG + Math.pow(c1.getGreen() - c2.getGreen(), 2);
                sumB = sumB + Math.pow(c1.getBlue() - c2.getBlue(), 2);
            }
        }
        double mse = (sumR+sumG+sumB)/(3*img.getWidth()*img.getHeight());
        double psnr = 10*(Math.log10(Math.pow(255, 2)/mse));
        return psnr;
    }
}
