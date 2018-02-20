
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
    public ImageProcessor image;
    public int secretDataLength;

    public Steganography() {
    }

    public void setSecretData(String secretData) {
        this.secretData = secretData;
        this.secretDataLength = this.secretData.length();
    }
    
    public void setCoverImage(ImageProcessor image){
        this.image = image;
    }

    public String secretDataToBinary() {
        String res = "";
        int[] ascii = new int[this.secretDataLength];
        for (int i = 0; i < ascii.length; i++) {
            ascii[i] = (int) this.secretData.charAt(i);
        }
        for (int i = 0; i < ascii.length; i++) {
            String binary = Integer.toBinaryString(ascii[i]);
            for (int j = binary.length(); j < 8; j++) {
                binary = "0" + binary;
            }
            res = res + binary;
//            if (ascii[i] < 64) {
//                res = res + "00" + Integer.toBinaryString(ascii[i]);
//            } else {
//                res = res + "0" + Integer.toBinaryString(ascii[i]);
//            }
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

    public String intToBinary(int val) {
        String res = Integer.toBinaryString(val);
        if (res.length() < 2) {
            res = "00" + res;
        }
        return res;
    }

    public int binaryToInt(String bin) {
        return Integer.parseInt(bin, 2);
    }

}
