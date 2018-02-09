
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gavrila Sianturi
 */
public class LSBSteganography extends Steganography {

    public LSBSteganography(String secretData, String imagePath) throws IOException {
        super(secretData, imagePath);
    }

    @Override
    public void hideSecretData() {
        String scrt = this.secretDataToBinary();
        int x = 0;
        int y = 0;
        int[] newrgb = new int[3];
        for (int i = 0; i < scrt.length(); i++) {
            int r = this.image.getRedValue(x, y);
            int g = this.image.getGreenValue(x, y);
            int b = this.image.getBlueValue(x, y);
            if (i % 3 == 0) {
                if (r % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        //this.coverImage.setRedValue(r + 1, x, y);
                        newrgb[0] = r + 1;
                    } else {
                        newrgb[0] = r;
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        //this.coverImage.setRedValue(r - 1, x, y);
                        newrgb[0] = r - 1;
                    } else {
                        newrgb[0] = r;
                    }
                }
                try {
                    this.image.setPixelValue(x, y, newrgb[0], g, b);
                } catch (IOException ex) {
                    Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (i % 3 == 1) {
                if (g % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        //this.coverImage.setGreenValue(g + 1, x, y);
                        newrgb[1] = g + 1;
                    } else {
                        newrgb[1] = g;
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        //this.coverImage.setGreenValue(g - 1, x, y);
                        newrgb[1] = g - 1;
                    } else {
                        newrgb[1] = g;
                    }
                }
                try {
                    this.image.setPixelValue(x, y, newrgb[0], newrgb[1], b);
                } catch (IOException ex) {
                    Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (b % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        //this.coverImage.setBlueValue(b + 1, x, y);
                        newrgb[2] = b + 1;
                    } else {
                        newrgb[2] = b;
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        //this.coverImage.setBlueValue(b - 1, x, y);
                        newrgb[2] = b - 1;
                    } else {
                        newrgb[2] = b;
                    }
                }
                try {
                    this.image.setPixelValue(x, y, newrgb[0], newrgb[1], newrgb[2]);
                } catch (IOException ex) {
                    Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;

                    }
                }
            }
        }
    }

    public String extractSecretData(ImageProcessor image, int secretLength) {
        String scrt = "";
        int[][][] pixel = image.getPixels(image.img);
        int count = 0;
        int x = 0;
        int y = 0;
        while (count < (secretLength * 8)) {
            if (count % 3 == 0) {
                if (pixel[y][x][0] % 2 == 0) {
                    scrt = scrt + 0;
                } else {
                    scrt = scrt + 1;
                }
                count = count + 1;
            } else if (count % 3 == 1) {
                if (pixel[y][x][1] % 2 == 0) {
                    scrt = scrt + 0;
                } else {
                    scrt = scrt + 1;
                }
                count = count + 1;
            } else {
                if (pixel[y][x][2] % 2 == 0) {
                    scrt = scrt + 0;

                } else {
                    scrt = scrt + 1;
                }
                count = count + 1;
                if (x < image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < image.getImgHeight() - 1) {
                        x = 0;
                        y++;

                    }
                }
            }

        }
        return this.binaryToSecretData(scrt);
    }
}
