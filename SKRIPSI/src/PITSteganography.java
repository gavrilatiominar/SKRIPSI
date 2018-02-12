
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
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
public class PITSteganography extends Steganography {

    public PITSteganography(String secretData, String coverImagePath) throws IOException {
        super(secretData, coverImagePath);
    }

    public int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(0, (this.image.getImgHeight() * this.image.getImgWidth()));
    }

    public int[] getFirstXY(int randNumber) {
        int[] xy = new int[2];
        xy[0] = 0;
        xy[1] = 0;
        for (int i = 0; i < randNumber; i++) {
            if (xy[0] < this.image.getImgWidth() - 1) {
                xy[0]++;
            } else {
                if (xy[1] < this.image.getImgHeight() - 1) {
                    xy[0] = 0;
                    xy[1]++;
                }
            }
        }
        return xy;
    }

    public boolean isEven(int secretLength) {
        if (secretLength % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPrime(int secretLength) {
        int count = 0;
        for (int i = 1; i <= secretLength; i++) {
            if (secretLength % i == 0) {
                count++;
            } else {
                count = count;
            }
        }
        if (count == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean oddParity(int secretLength) {
        String size = Integer.toBinaryString(secretLength);
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

    public char getIndicatorChannel(int secretLength) {
        char res;
        if (this.isEven(secretLength)) {
            res = 'R';
        } else if (this.isPrime(secretLength)) {
            res = 'B';
        } else {
            res = 'G';
        }
        return res;
    }

    public char get1stChannel(int secretLength) {
        char res;
        if (this.isEven(secretLength)) {
            if (this.oddParity(secretLength)) {
                res = 'G';
            } else {
                res = 'B';
            }
        } else if (this.isPrime(secretLength)) {
            if (this.oddParity(secretLength)) {
                res = 'R';
            } else {
                res = 'G';
            }
        } else {
            if (this.oddParity(secretLength)) {
                res = 'R';
            } else {
                res = 'B';
            }
        }
        return res;
    }

    public char get2ndChannel(int secretLength) {
        char res;
        if (this.isEven(secretLength)) {
            if (this.oddParity(secretLength)) {
                res = 'B';
            } else {
                res = 'G';
            }
        } else if (this.isPrime(secretLength)) {
            if (this.oddParity(secretLength)) {
                res = 'G';
            } else {
                res = 'R';
            }
        } else {
            if (this.oddParity(secretLength)) {
                res = 'B';
            } else {
                res = 'R';
            }
        }
        return res;
    }

    public String getIndicatorValue(int x, int y, int secretLength) {
        char ic = this.getIndicatorChannel(secretLength);
        String res;
        if (ic == 'R') {
            int r = this.image.getRedValue(x, y);
            String bin = this.intToBinary(r);
            res = bin.substring(bin.length() - 2);
        } else if (ic == 'G') {
            int g = this.image.getGreenValue(x, y);
            String bin = this.intToBinary(g);
            res = bin.substring(bin.length() - 2);
        } else {
            int b = this.image.getBlueValue(x, y);
            String bin = this.intToBinary(b);
            res = bin.substring(bin.length() - 2);
        }
        return res;
    }

    @Override
    public void hideSecretData() {
        String scrt = this.secretDataToBinary();
        int randNumber = this.generateRandomNumber();
        System.out.println(randNumber);
        int[] xy = this.getFirstXY(randNumber);
        int x = xy[0];
        int y = xy[1];
        char ic = this.getIndicatorChannel(this.secretDataLength);
        char fc = this.get1stChannel(this.secretDataLength);
        char sc = this.get2ndChannel(this.secretDataLength);
        int rms = 0;
        while (rms < scrt.length()) {
            String ind = this.getIndicatorValue(x, y, this.secretDataLength);
            int r = this.image.getRedValue(x, y);
            int g = this.image.getGreenValue(x, y);
            int b = this.image.getBlueValue(x, y);
            if (ind.equals("00")) {
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
            } else if (ind.equals("01")) {
                if (sc == 'R') {
                    String bin = this.intToBinary(r);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newR = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, newR, g, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (sc == 'G') {
                    String bin = this.intToBinary(g);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newG = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, newG, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String bin = this.intToBinary(b);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newB = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, g, newB);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            } else if (ind.equals("10")) {
                if (fc == 'R') {
                    String bin = this.intToBinary(r);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newR = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, newR, g, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (fc == 'G') {
                    String bin = this.intToBinary(g);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newG = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, newG, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String bin = this.intToBinary(b);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newB = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, g, newB);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            } else {
                if (fc == 'R') {
                    String bin = this.intToBinary(r);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newR = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, newR, g, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rms = rms + 2;
                    if (rms < scrt.length() - 1) {
                        if (sc == 'G') {
                            String sbin = this.intToBinary(g);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newG = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, newR, newG, b);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            String sbin = this.intToBinary(b);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newB = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, newR, g, newB);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else if (fc == 'G') {
                    String bin = this.intToBinary(g);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newG = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, newG, b);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rms = rms + 2;
                    if (rms < scrt.length() - 1) {
                        if (sc == 'B') {
                            String sbin = this.intToBinary(b);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newB = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, r, newG, newB);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            String sbin = this.intToBinary(r);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newR = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, newR, newG, b);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    String bin = this.intToBinary(b);
                    String newBin = bin.substring(0, bin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                    int newB = this.binaryToInt(newBin);
                    try {
                        this.image.setPixelValue(x, y, r, g, newB);
                    } catch (IOException ex) {
                        Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rms = rms + 2;
                    if (rms < scrt.length() - 1) {
                        if (sc == 'R') {
                            String sbin = this.intToBinary(r);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newR = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, newR, g, newB);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            String sbin = this.intToBinary(g);
                            String newSBin = sbin.substring(0, sbin.length() - 2) + scrt.charAt(rms) + scrt.charAt(rms + 1);
                            int newG = this.binaryToInt(newSBin);
                            try {
                                this.image.setPixelValue(x, y, r, newG, newB);
                            } catch (IOException ex) {
                                Logger.getLogger(PITSteganography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            }
        }

    }

    public String extractSecretData(ImageProcessor image, int randomNumber, int secretLength) {
        String res = "";
        int[] xy = this.getFirstXY(randomNumber);
        int x = xy[0];
        int y = xy[1];
        char ic = this.getIndicatorChannel(secretLength);
        char fc = this.get1stChannel(secretLength);
        char sc = this.get2ndChannel(secretLength);
        int bitLength = secretLength * 8;
        int rms = 0;
        while (rms < bitLength) {
            String ind = this.getIndicatorValue(x, y, secretLength);
            int r = this.image.getRedValue(x, y);
            int g = this.image.getGreenValue(x, y);
            int b = this.image.getBlueValue(x, y);
            if (ind.equals("00")) {
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
            } else if (ind.equals("01")) {
                if (sc == 'R') {
                    String bin = this.intToBinary(r);
                    res = res + bin.substring(bin.length() - 2);
                } else if (sc == 'G') {
                    String bin = this.intToBinary(g);
                    res = res + bin.substring(bin.length() - 2);
                } else {
                    String bin = this.intToBinary(b);
                    res = res + bin.substring(bin.length() - 2);
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            } else if (ind.equals("10")) {
                if (fc == 'R') {
                    String bin = this.intToBinary(r);
                    res = res + bin.substring(bin.length() - 2);
                } else if (fc == 'G') {
                    String bin = this.intToBinary(g);
                    res = res + bin.substring(bin.length() - 2);
                } else {
                    String bin = this.intToBinary(b);
                    res = res + bin.substring(bin.length() - 2);
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            } else {
                if (fc == 'R') {
                    String bin = this.intToBinary(r);
                    res = res + bin.substring(bin.length() - 2);
                    rms = rms + 2;
                    if (rms < bitLength - 1) {
                        if (sc == 'G') {
                            String sbin = this.intToBinary(g);
                            res = res + sbin.substring(sbin.length() - 2);
                        } else {
                            String sbin = this.intToBinary(b);
                            res = res + sbin.substring(sbin.length() - 2);
                        }
                    }
                } else if (fc == 'G') {
                    String bin = this.intToBinary(g);
                    res = res + bin.substring(bin.length() - 2);
                    rms = rms + 2;
                    if (rms < bitLength - 1) {
                        if (sc == 'B') {
                            String sbin = this.intToBinary(b);
                            res = res + sbin.substring(sbin.length() - 2);
                        } else {
                            String sbin = this.intToBinary(r);
                            res = res + sbin.substring(sbin.length() - 2);
                        }
                    }
                } else {
                    String bin = this.intToBinary(b);
                    res = res + bin.substring(bin.length() - 2);
                    rms = rms + 2;
                    if (rms < bitLength - 1) {
                        if (sc == 'R') {
                            String sbin = this.intToBinary(r);
                            res = res + sbin.substring(sbin.length() - 2);
                        } else {
                            String sbin = this.intToBinary(g);
                            res = res + sbin.substring(sbin.length() - 2);
                        }
                    }
                }
                if (x < this.image.getImgWidth() - 1) {
                    x++;
                } else {
                    if (y < this.image.getImgHeight() - 1) {
                        x = 0;
                        y++;
                    } else {
                        x = 0;
                        y = 0;
                    }
                }
                rms = rms + 2;
            }
        }
        return this.binaryToSecretData(res);
    }

}
