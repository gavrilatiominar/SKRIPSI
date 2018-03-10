
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
    int[][] pixelVal;
    long[] XR;
    long[] XG;
    long[] XB;
    double[] YR;
    double[] YG;
    double[] YB;
    double[] ZR;
    double[] ZG;
    double[] ZB;
    double[] chiSquared;
    double probabilityRate;
    int chunkSize = 128;
    int numOfChunks = 0;
    ChiSquareTest chi;

    public ChiSquare(String filePath) throws IOException {
        this.ip = new ImageProcessor(filePath);
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
}
