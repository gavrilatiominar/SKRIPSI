
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
        int r = (pixel >> 16) & 0xff;
        return r;
    }

    public int getGreenValue(int x, int y) {
        int pixel = this.coverImage.getRGB(x, y);
        int g = (pixel >> 8) & 0xff;
        return g;
    }

    public int getBlueValue(int x, int y) {
        int pixel = this.coverImage.getRGB(x, y);
        int b = pixel & 0xff;
        return b;
    }

    public void setPixelValue(int x, int y, int red, int green, int blue) throws IOException {
        int pixel = (red << 16) | (green << 8) | blue;
        this.coverImage.setRGB(x, y, pixel);
    }

    public void createImage() throws IOException {
        File f = new File(this.coverImagePath.substring(0, this.coverImagePath.length() - 4) + "Output.bmp");
        ImageIO.write(coverImage, "bmp", f);
    }

    public int[] getPixels(BufferedImage img) {
        int iArray[] = null; //An optionally pre-allocated int array
        int[] pixel = img.getRaster().getPixels(0, 0, img.getWidth(), img.getHeight(), iArray);
        return pixel;
    }
}
