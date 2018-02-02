
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gavrila Sianturi
 */
public class PITSteganography extends Steganography {

    public PITSteganography(String secretData, String coverImagePath) throws IOException {
        super(secretData, coverImagePath);
    }
    
    public boolean oddParity() {
        String size = Integer.toBinaryString(secretDataLength);
        int count = 0;
        for (int i = 0; i < size.length(); i++) {
            if (size.charAt(i) == '1') {
                count += 1;
            }
        }
        if (count % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void hideSecretData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void extractSecretData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
