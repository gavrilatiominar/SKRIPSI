/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author Gavrila Sianturi
 */
public class ImageProcessorIrvan {
    public BufferedImage image;
    private int imageHeight;
    private int imageWidth;
    
    public ImageProcessorIrvan(){
        
    }
    
    public BufferedImage loadImage(String path){
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessorIrvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.imageHeight = image.getHeight();
        this.imageWidth = image.getWidth();
        return image;
    }
    
    public int getHeight(){
        return imageHeight;
    }
    
    public int getWidth(){
        return imageWidth;
    }
    
    public int[][] convertTo2DPixelValues(){
        int[][] imgArr = null;
        this.imageHeight = image.getHeight();
        this.imageWidth = image.getWidth();
        imgArr = new int[imageHeight][imageWidth];
        Raster raster = image.getData();
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                imgArr[i][j] = raster.getSample(j, i, 0);
            }
        }
        return imgArr;
    }
    
    public BufferedImage copyImage(BufferedImage image){
        BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = newImg.createGraphics();
        graphics.drawRenderedImage(image, null);
        graphics.dispose();
        return newImg;
    }
    
    public void saveImage(int[] pixels, BufferedImage image, String format, File file) throws IOException {
        WritableRaster raster = (WritableRaster) image.getRaster();
        raster.setPixels(0, 0, this.image.getHeight(), this.image.getWidth(), pixels);
        ImageIO.write(image, format, file);
    }   
    
    public int[] getPixels(BufferedImage image){
        int[] data = null;
        int[] pixel = image.getRaster().getPixels(0, 0, image.getHeight(), image.getWidth(), data);
        return pixel;
    }
}
