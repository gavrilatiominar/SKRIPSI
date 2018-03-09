
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
    long[] X;
    double[] Y;
    double[] Z;
    double[] chiSquared;
    double probabilityRate;
    int chuckSize = 128;
    int numOfChunks = 0;
    ChiSquareTest chi;

    public ChiSquare(String filePath) throws IOException {
        this.ip = new ImageProcessor(filePath);
        this.chi = new ChiSquareTest();
        this.X = new long[64];
        
    }
}
