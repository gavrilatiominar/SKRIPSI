/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

/**
 *
 * @author Gavrila Sianturi
 */
public class ChiSquareIrvan {

    ImageProcessorIrvan ip;
    int[][] pixelVal;
    long[] X;
    double[] Y;
    double[] Z;
    double[] chiSquared;
    double probabilityRate;
    int chuckSize = 128;
    int numOfChunks = 0;
    ChiSquareTest chi;

    public ChiSquareIrvan() {
        this.ip = new ImageProcessorIrvan();
        this.chi = new ChiSquareTest();
        this.X = new long[64];
        this.Y = new double[64];
        this.Z = new double[64];
        for (int i = 0; i < 64; i++) {
            Z[i] = 1;
        }
        this.probabilityRate = 0;
        this.probabilityRate = 0;
        this.pixelVal = null;
    }

    public void setImage(String path) {
        ip.loadImage(path);
    }

    public void setPixelImage() {
        this.pixelVal = ip.convertTo2DPixelValues();
    }

    public double chiAnalysis() {
        int indexOfEPoV = 0;
        this.numOfChunks = (int) (Math.floor((this.pixelVal.length * this.pixelVal.length * 3) / chuckSize) + 1);
        this.chiSquared = new double[numOfChunks];
        int byteRead = 0;
        double temp = 0;
        for (int i = 0; i < this.pixelVal.length; i++) {
            for (int j = 0; j < this.pixelVal.length; j++) {
                indexOfEPoV = (pixelVal[i][j] / 4);
                String twoLSB = get2LSBPixelImage(pixelVal[i][j]);
                if (twoLSB.equalsIgnoreCase("00") || twoLSB.equalsIgnoreCase("11")) {
                    this.X[indexOfEPoV]++;
                } else {
                    this.Y[indexOfEPoV]++;
                }
                this.Z[indexOfEPoV] = Math.round((this.X[indexOfEPoV] + this.Y[indexOfEPoV]) / 2.0);
                byteRead++;
                if (byteRead % chuckSize == 0) {
                    runTest(byteRead);
                }
            }
        }
        this.chiSquared[numOfChunks - 1] = chi.chiSquareTest(Z, X);
        for (int i = 0; i < chiSquared.length; i++) {
            temp += chiSquared[i];
        }
        probabilityRate = validResult(temp / chuckSize);
        return probabilityRate;
    }

    private void runTest(int bytesRead) {
        try {
            chiSquared[(bytesRead / chuckSize) - 1] = chi.chiSquareTest(Z, X);
        } catch (Exception ex) {

        }
    }
    
    public String get2LSBPixelImage(int x){
        char[] temp = Integer.toBinaryString(x).toCharArray();
        String res = temp[temp.length-1] + "" + temp[temp.length-2];
        return res;
    }
    
    private static double validResult(double x){
        x = Math.abs(x);
        if(x > 1){
            return 1;
        }
        return x;
    }
}
