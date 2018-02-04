
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
        for (int i = 0; i < scrt.length(); i++) {
            if (i % 3 == 0) {
                int r = this.coverImage.getRedValue(x, y);
                if (r % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        this.coverImage.setRedValue(r + 1, x, y);
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        this.coverImage.setRedValue(r - 1, x, y);
                    }
                }
            } else if (i % 3 == 1) {
                int g = this.coverImage.getGreenValue(x, y);
                if (g % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        this.coverImage.setGreenValue(g + 1, x, y);
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        this.coverImage.setGreenValue(g - 1, x, y);
                    }
                }
            } else {
                int b = this.coverImage.getBlueValue(x, y);
                if (b % 2 == 0) {
                    if (scrt.charAt(i) == '1') {
                        this.coverImage.setBlueValue(b + 1, x, y);
                    }
                } else {
                    if (scrt.charAt(i) == '0') {
                        this.coverImage.setBlueValue(b - 1, x, y);
                    }
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
