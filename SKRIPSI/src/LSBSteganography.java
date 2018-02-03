
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
            byte r = (byte) this.coverImage.getRedValue(x, y);
            byte g = (byte) this.coverImage.getGreenValue(x, y);
            byte b = (byte) this.coverImage.getBlueValue(x, y);
            int bit1 = (int) scrt.charAt(i);
            int bit2 = (int) scrt.charAt(i + 1);
            int bit3 = (int) scrt.charAt(i + 2);
            r = (byte) ((r & 0xFE) | bit1);
            g = (byte) ((g & 0xFE) | bit2);
            b = (byte) ((b & 0xFE) | bit3);
            try {
                this.coverImage.setPixelValue(x, y, r, g, b);
            } catch (IOException ex) {
                Logger.getLogger(LSBSteganography.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (x < this.coverImage.getImgWidth()) {
                x++;
            } else {
                x = 0;
                y++;
            }
            i += 3;
        }
    }

    @Override
    public void extractSecretData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
