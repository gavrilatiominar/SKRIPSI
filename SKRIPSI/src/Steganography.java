
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
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
public abstract class Steganography {

    public String secretData;
    public ImageProcessor coverImage;
    public int secretDataLength;

    public Steganography(String secretData, String coverImagePath) throws IOException { //param coverimage
        this.secretData = secretData;
        this.coverImage = new ImageProcessor(coverImagePath);
        this.secretDataLength = secretData.length();
    }

    public String secretDataToBinary() {
        String res = "";
        byte[] bytes = this.secretData.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            res = res + 0 + Integer.toBinaryString(bytes[i]);
        }
        return res;
    }

    public String binaryToSecretData(String binary) {
        String result = "";
        int length = 0;
        while (length < binary.length()) {
            int ascii = Integer.parseInt(binary.substring(length, length + 8), 2);
            result += (char) (ascii);
            length = length + 8;
        }
        return result;
    }

    public abstract void hideSecretData();

    public abstract void extractSecretData();
}
