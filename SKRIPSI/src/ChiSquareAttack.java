
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

/**
 * @version 1.0
 * @author Cuneyt Caliskan
 */
public class ChiSquareAttack {

    ImageProcessor ip;
    int[][][] pixelVal;
    long[] XR;
    long[] XG;
    long[] XB;
    double[] YR;
    double[] YG;
    double[] YB;
    double[] ZR;
    double[] ZG;
    double[] ZB;
    double[] chiSquaredR;
    double[] chiSquaredG;
    double[] chiSquaredB;
    double probabilityRate;
    int chunkSize = 128;
    int numOfChunks;
    ChiSquareTest chi;

    public ChiSquareAttack() {
        this.chi = new ChiSquareTest();
        this.XR = new long[64];
        this.XG = new long[64];
        this.XB = new long[64];
        this.YR = new double[64];
        this.YG = new double[64];
        this.YB = new double[64];
        this.ZR = new double[64];
        this.ZG = new double[64];
        this.ZB = new double[64];
        for (int i = 0; i < 64; i++) {
            ZR[i] = 1;
        }
        for (int i = 0; i < 64; i++) {
            ZG[i] = 1;
        }
        for (int i = 0; i < 64; i++) {
            ZB[i] = 1;
        }
    }

    public void setImage(String path) throws IOException {
        this.ip = new ImageProcessor(path);
    }

    public void setPixelValue() {
        this.pixelVal = this.ip.getPixels(this.ip.img);
    }

    public String get2LSB(int x) {
        String binary = Integer.toBinaryString(x);
        String res;
        if (binary.length() < 2) {
            if (x == 0) {
                res = "00";
            } else {
                res = "01";
            }
        } else {
            res = binary.substring(binary.length() - 2);
        }
        return res;
    }

    public double validResult(double x) {
        x = Math.abs(x);
        if (x > 1) {
            return 1;
        }
        return x;
    }

    public double[] chiAnalysis() {
        int w = this.ip.getImgWidth();
        int h = this.ip.getImgHeight();
        this.numOfChunks = (int) (Math.floor((w * h * 3) / chunkSize) + 1);
        this.chiSquaredR = new double[numOfChunks];
        this.chiSquaredG = new double[numOfChunks];
        this.chiSquaredB = new double[numOfChunks];
        int byteRead = 0;
        int x = 0;
        int y = 0;
        while (byteRead < (w * h * 3)) {
            if (byteRead % 3 == 0) {
                int red = this.ip.getRedValue(x, y);
                int indexOfEPoV = red / 4;
                String twoLSB = get2LSB(red);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XR[indexOfEPoV]++;
                } else {
                    this.YR[indexOfEPoV]++;
                }
                byteRead++;
                if (byteRead % chunkSize == 0) {
                    for (int i = 0; i < 64; i++) {
                        if (this.XR[i] != 0 && this.YR[i] != 0) {
                            this.ZR[i] = Math.round((this.XR[i] + this.YR[i]) / 2.0);
                        }
                    }
                    this.chiSquaredR[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZR, XR);
                    for (int i = 0; i < 64; i++) {
                        if (this.XG[i] != 0 && this.YG[i] != 0) {
                            this.ZG[i] = Math.round((this.XG[i] + this.YG[i]) / 2.0);
                        }
                    }
                    this.chiSquaredG[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZG, XG);
                    for (int i = 0; i < 64; i++) {
                        if (this.XB[i] != 0 && this.YB[i] != 0) {
                            this.ZB[i] = Math.round((this.XB[i] + this.YB[i]) / 2.0);
                        }
                    }
                    this.chiSquaredB[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZB, XB);
                    for (int i = 0; i < 64; i++) {
                        this.XR[i] = 0;
                        this.YR[i] = 0;
                        this.ZR[i] = 1;
                        this.XG[i] = 0;
                        this.YG[i] = 0;
                        this.ZG[i] = 1;
                        this.XB[i] = 0;
                        this.YB[i] = 0;
                        this.ZB[i] = 1;
                    }
                }
            } else if (byteRead % 3 == 1) {
                int green = this.ip.getGreenValue(x, y);
                int indexOfEPoV = green / 4;
                String twoLSB = get2LSB(green);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XG[indexOfEPoV]++;
                } else {
                    this.YG[indexOfEPoV]++;
                }
                byteRead++;
                if (byteRead % chunkSize == 0) {
                    for (int i = 0; i < 64; i++) {
                        if (this.XR[i] != 0 && this.YR[i] != 0) {
                            this.ZR[i] = Math.round((this.XR[i] + this.YR[i]) / 2.0);
                        }
                    }
                    this.chiSquaredR[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZR, XR);
                    for (int i = 0; i < 64; i++) {
                        if (this.XG[i] != 0 && this.YG[i] != 0) {
                            this.ZG[i] = Math.round((this.XG[i] + this.YG[i]) / 2.0);
                        }
                    }
                    this.chiSquaredG[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZG, XG);
                    for (int i = 0; i < 64; i++) {
                        if (this.XB[i] != 0 && this.YB[i] != 0) {
                            this.ZB[i] = Math.round((this.XB[i] + this.YB[i]) / 2.0);
                        }
                    }
                    this.chiSquaredB[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZB, XB);
                    for (int i = 0; i < 64; i++) {
                        this.XR[i] = 0;
                        this.YR[i] = 0;
                        this.ZR[i] = 1;
                        this.XG[i] = 0;
                        this.YG[i] = 0;
                        this.ZG[i] = 1;
                        this.XB[i] = 0;
                        this.YB[i] = 0;
                        this.ZB[i] = 1;
                    }
                }
            } else {
                int blue = this.ip.getBlueValue(x, y);
                int indexOfEPoV = blue / 4;
                String twoLSB = get2LSB(blue);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XB[indexOfEPoV]++;
                } else {
                    this.YB[indexOfEPoV]++;
                }
                byteRead++;
                if (byteRead % chunkSize == 0) {
                    for (int i = 0; i < 64; i++) {
                        if (this.XR[i] != 0 && this.YR[i] != 0) {
                            this.ZR[i] = Math.round((this.XR[i] + this.YR[i]) / 2.0);
                        }
                    }
                    this.chiSquaredR[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZR, XR);
                    for (int i = 0; i < 64; i++) {
                        if (this.XG[i] != 0 && this.YG[i] != 0) {
                            this.ZG[i] = Math.round((this.XG[i] + this.YG[i]) / 2.0);
                        }
                    }
                    this.chiSquaredG[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZG, XG);
                    for (int i = 0; i < 64; i++) {
                        if (this.XB[i] != 0 && this.YB[i] != 0) {
                            this.ZB[i] = Math.round((this.XB[i] + this.YB[i]) / 2.0);
                        }
                    }
                    this.chiSquaredB[(byteRead / chunkSize) - 1] = chi.chiSquareTest(ZB, XB);
                    for (int i = 0; i < 64; i++) {
                        this.XR[i] = 0;
                        this.YR[i] = 0;
                        this.ZR[i] = 1;
                        this.XG[i] = 0;
                        this.YG[i] = 0;
                        this.ZG[i] = 1;
                        this.XB[i] = 0;
                        this.YB[i] = 0;
                        this.ZB[i] = 1;
                    }
                }
                if (x < w - 1) {
                    x++;
                } else {
                    if (y < h - 1) {
                        x = 0;
                        y++;
                    }
                }
                if (byteRead == w * h * 3) {
                    for (int i = 0; i < 64; i++) {
                        if (this.XR[i] != 0 && this.YR[i] != 0) {
                            this.ZR[i] = Math.round((this.XR[i] + this.YR[i]) / 2.0);
                        }
                    }
                    this.chiSquaredR[(byteRead / chunkSize)] = chi.chiSquareTest(ZR, XR);
                    for (int i = 0; i < 64; i++) {
                        if (this.XG[i] != 0 && this.YG[i] != 0) {
                            this.ZG[i] = Math.round((this.XG[i] + this.YG[i]) / 2.0);
                        }
                    }
                    this.chiSquaredG[(byteRead / chunkSize)] = chi.chiSquareTest(ZG, XG);
                    for (int i = 0; i < 64; i++) {
                        if (this.XB[i] != 0 && this.YB[i] != 0) {
                            this.ZB[i] = Math.round((this.XB[i] + this.YB[i]) / 2.0);
                        }
                    }
                    this.chiSquaredB[(byteRead / chunkSize)] = chi.chiSquareTest(ZB, XB);
                }
            }
        }
        double tempR = 0;
        double tempG = 0;
        double tempB = 0;
        for (int i = 0; i < numOfChunks; i++) {
            tempR += chiSquaredR[i];
            tempG += chiSquaredG[i];
            tempB += chiSquaredB[i];
        }
        double[] probabilityRate = new double[3];
        probabilityRate[0] = validResult(tempR);
        probabilityRate[1] = validResult(tempG);
        probabilityRate[2] = validResult(tempB);
        return probabilityRate;
    }
}
