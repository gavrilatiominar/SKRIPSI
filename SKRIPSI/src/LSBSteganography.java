
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

    public LSBSteganography(String secretData, String coverImagePath) throws IOException {
        super(secretData, coverImagePath);
    }

    @Override
    public void hideSecretData() {
        String scrt = this.secretDataToBinary();
        int x = 0;
        int y = 0;
        int[] newrgb = new int[3];
        for (int i = 0; i < scrt.length(); i++) {
            int r = this.coverImage.getRedValue(x, y);
            int g = this.coverImage.getGreenValue(x, y);
            int b = this.coverImage.getBlueValue(x, y);
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
                    this.coverImage.setPixelValue(x, y, newrgb[0], g, b);
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
                    this.coverImage.setPixelValue(x, y, newrgb[0], newrgb[1], b);
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
                    this.coverImage.setPixelValue(x, y, newrgb[0], newrgb[1], newrgb[2]);
                } catch (IOException ex) {
                    Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (x < this.coverImage.getImgWidth()) {
                    x++;
                } else {
                    x = 0;
                    y++;
                }
            }
        }
    }

    @Override
    public void extractSecretData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
