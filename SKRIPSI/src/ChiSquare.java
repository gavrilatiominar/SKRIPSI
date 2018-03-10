
import java.io.IOException;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gavrila Sianturi
 */
public class ChiSquare {

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
    double probabilityRateR;
    double probabilityRateG;
    double probabilityRateB;
    int chunkSizeR = 128;
    int chunkSizeG = 128;
    int chunkSizeB = 128;
    int numOfChunksR = 0;
    int numOfChunksG = 0;
    int numOfChunksB = 0;
    ChiSquareTest chi;

    public ChiSquare() throws IOException {
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
    }

    public void setImage(String path) throws IOException {
        this.ip = new ImageProcessor(path);
    }

    public void setPixelVal() {
        this.pixelVal = this.ip.getPixels(this.ip.img);
    }

    public void runTestR(int bytesRead) {
        try {
            chiSquaredR[(bytesRead / chunkSizeR) - 1] = chi.chiSquareTest(ZR, XR);
        } catch (Exception ex) {

        }
    }

    public void runTestG(int bytesRead) {
        try {
            chiSquaredG[(bytesRead / chunkSizeG) - 1] = chi.chiSquareTest(ZG, XG);
        } catch (Exception ex) {

        }
    }

    public void runTestB(int bytesRead) {
        try {
            chiSquaredB[(bytesRead / chunkSizeB) - 1] = chi.chiSquareTest(ZB, XB);
        } catch (Exception ex) {

        }
    }

    public double chiAnalysisR() {
        int indexOfEPoV = 0;
        this.numOfChunksR = (int) (Math.floor((this.pixelVal.length * this.pixelVal[0].length * 3) / chunkSizeR) + 1);
        this.chiSquaredR = new double[numOfChunksR];
        int byteRead = 0;
        double temp = 0;
        for (int i = 0; i < this.pixelVal.length; i++) {
            for (int j = 0; j < this.pixelVal[i].length; j++) {
                indexOfEPoV = (pixelVal[i][j][0] / 4);
                String twoLSB = get2LSB(pixelVal[i][j][0]);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XR[indexOfEPoV]++;
                } else {
                    this.YR[indexOfEPoV]++;
                }
                this.ZR[indexOfEPoV] = Math.round((this.XR[indexOfEPoV] + this.YR[indexOfEPoV]) / 2.0);
                byteRead++;
                if (byteRead % chunkSizeR == 0) {
                    runTestR(byteRead);
                }
            }
        }
        this.chiSquaredR[numOfChunksR - 1] = chi.chiSquareTest(ZR, XR);
        for (int i = 0; i < chiSquaredR.length; i++) {
            temp += chiSquaredR[i];
        }
        probabilityRateR = validResultR(temp / chunkSizeR);
        return probabilityRateR;
    }

    public double chiAnalysisG() {
        int indexOfEPoV = 0;
        this.numOfChunksG = (int) (Math.floor((this.pixelVal.length * this.pixelVal[0].length * 3) / chunkSizeG) + 1);
        this.chiSquaredG = new double[numOfChunksG];
        int byteRead = 0;
        double temp = 0;
        for (int i = 0; i < this.pixelVal.length; i++) {
            for (int j = 0; j < this.pixelVal[i].length; j++) {
                indexOfEPoV = (pixelVal[i][j][1] / 4);
                String twoLSB = get2LSB(pixelVal[i][j][1]);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XG[indexOfEPoV]++;
                } else {
                    this.YG[indexOfEPoV]++;
                }
                this.ZG[indexOfEPoV] = Math.round((this.XG[indexOfEPoV] + this.YG[indexOfEPoV]) / 2.0);
                byteRead++;
                if (byteRead % chunkSizeG == 0) {
                    runTestG(byteRead);
                }
            }
        }
        this.chiSquaredG[numOfChunksG - 1] = chi.chiSquareTest(ZG, XG);
        for (int i = 0; i < chiSquaredG.length; i++) {
            temp += chiSquaredG[i];
        }
        probabilityRateG = validResultG(temp / chunkSizeG);
        return probabilityRateG;
    }

    public double chiAnalysisB() {
        int indexOfEPoV = 0;
        this.numOfChunksB = (int) (Math.floor((this.pixelVal.length * this.pixelVal[0].length * 3) / chunkSizeB) + 1);
        this.chiSquaredB = new double[numOfChunksB];
        int byteRead = 0;
        double temp = 0;
        for (int i = 0; i < this.pixelVal.length; i++) {
            for (int j = 0; j < this.pixelVal[i].length; j++) {
                indexOfEPoV = (pixelVal[i][j][2] / 4);
                String twoLSB = get2LSB(pixelVal[i][j][2]);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.XB[indexOfEPoV]++;
                } else {
                    this.YB[indexOfEPoV]++;
                }
                this.ZB[indexOfEPoV] = Math.round((this.XB[indexOfEPoV] + this.YB[indexOfEPoV]) / 2.0);
                byteRead++;
                if (byteRead % chunkSizeB == 0) {
                    runTestB(byteRead);
                }
            }
        }
        this.chiSquaredB[numOfChunksB - 1] = chi.chiSquareTest(ZB, XB);
        for (int i = 0; i < chiSquaredB.length; i++) {
            temp += chiSquaredB[i];
        }
        probabilityRateB = validResultB(temp / chunkSizeB);
        return probabilityRateB;
    }

    public String get2LSB(int x) {
        String bin = Integer.toBinaryString(x);
        String res = bin.substring(bin.length() - 2);
        return res;
    }

    public static double validResultR(double x) {
        x = Math.abs(x);
        if (x > 1) {
            return 1;
        }
        return x;
    }

    public static double validResultG(double x) {
        x = Math.abs(x);
        if (x > 1) {
            return 1;
        }
        return x;
    }

    public static double validResultB(double x) {
        x = Math.abs(x);
        if (x > 1) {
            return 1;
        }
        return x;
    }
}
