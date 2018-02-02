
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
        int i = 0;
        int x = 0;
        int y = 0;
        int[] pixel = this.coverImage.getPixels(this.coverImage.coverImage);
        while (i < scrt.length()-2) {
            //red
            if (pixel[i] % 2 == 0) {
                if (scrt.charAt(i) == '1') {
                    pixel[i] = pixel[i] + 1;
                } else {
                    pixel[i] = pixel[i];
                }
            } else {
                if (scrt.charAt(i) == '0') {
                    pixel[i] = pixel[i] - 1;
                } else {
                    pixel[i] = pixel[i];
                }
            }
            //green
            if (pixel[i + 1] % 2 == 0) {
                if (scrt.charAt(i + 1) == '1') {
                    pixel[i + 1] = pixel[i + 1] + 1;
                } else {
                    pixel[i + 1] = pixel[i + 1];
                }
            } else {
                if (scrt.charAt(i + 1) == '0') {
                    pixel[i + 1] = pixel[i + 1] - 1;
                } else {
                    pixel[i + 1] = pixel[i + 1];
                }
            }
            //blue
            if (pixel[i + 2] % 2 == 0) {
                if (scrt.charAt(i + 2) == '1') {
                    pixel[i + 2] = pixel[i + 2] + 1;
                } else {
                    pixel[i + 2] = pixel[i + 2];
                }
            } else {
                if (scrt.charAt(i + 2) == '0') {
                    pixel[i + 2] = pixel[i + 2] - 1;
                } else {
                    pixel[i + 2] = pixel[i + 2];
                }
            }

            if (x > this.coverImage.getImgWidth()) {
                x = 0;
                y += 1;
            } else {
                x += 1;
            }
            
            try {
                this.coverImage.setPixelValue(x, y, pixel[i], pixel[i+1], pixel[i+2]);
            } catch (IOException ex) {
                Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
            }            
            i += 3;            
        }

    }

    @Override
    public void extractSecretData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
