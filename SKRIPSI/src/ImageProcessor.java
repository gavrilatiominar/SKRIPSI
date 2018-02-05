
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

    public BufferedImage coverImage;
    public String coverImagePath;

    public ImageProcessor(String coverImagePath) throws IOException {
        this.coverImagePath = coverImagePath;
        File f = new File(coverImagePath);
        this.coverImage = ImageIO.read(f);
    }

    public int getImgWidth() {
        return coverImage.getWidth();
    }

    public int getImgHeight() {
        return coverImage.getHeight();
    }

    public int getRedValue(int x, int y) {
        int pixel = this.coverImage.getRGB(x, y);
        Color c = new Color(pixel);
//        int r = (pixel >> 16) & 0xff;
        return c.getRed();
    }

    public int getGreenValue(int x, int y) {
        int pixel = this.coverImage.getRGB(x, y);
        Color c = new Color(pixel);
//        int g = (pixel >> 8) & 0xff;
        return c.getGreen();
    }

    public int getBlueValue(int x, int y) {
        int pixel = this.coverImage.getRGB(x, y);
        Color c = new Color(pixel);
//        int b = pixel & 0xff;
        return c.getBlue();
    }

    public void setRedValue(int r, int x, int y) {
        int g = this.getGreenValue(x, y);
        int b = this.getBlueValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.coverImage.setRGB(x, y, pixel);
    }

    public void setGreenValue(int g, int x, int y) {
        int r = this.getRedValue(x, y);
        int b = this.getBlueValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.coverImage.setRGB(x, y, pixel);
    }

    public void setBlueValue(int b, int x, int y) {
        int r = this.getRedValue(x, y);
        int g = this.getGreenValue(x, y);
        int pixel = (r << 16) | (g << 8) | b;
        this.coverImage.setRGB(x, y, pixel);
    }

    public void setPixelValue(int x, int y, int red, int green, int blue) throws IOException {
        int pixel = (red << 16) | (green << 8) | blue;
        this.coverImage.setRGB(x, y, pixel);
    }

    public void createImage() throws IOException {
        File f = new File(this.coverImagePath.substring(0, this.coverImagePath.length() - 4) + "Output.bmp");
        ImageIO.write(coverImage, "bmp", f);
    }

    public int[][][] getPixels(BufferedImage img) {
//        int iArray[] = null; //An optionally pre-allocated int array
//        int[] pixel = img.getRaster().getPixels(0, 0, img.getWidth(), img.getHeight(), iArray);
//        return pixel;

//        int[] pixel;
//        for (int y = 0; y < img.getHeight(); y++) {
//            for (int x = 0; x < img.getWidth(); x++) {
//                pixel = img.getRaster().getPixel(x, y, new int[3]);
//                System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2] + " - " + (img.getWidth() * y + x));
//            }
//        }
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
}
