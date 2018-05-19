
import com.sun.imageio.plugins.common.I18N;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gavrila Sianturi
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        //lsb tester
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Secret Data: ");
//        String secret = sc.nextLine();
//        System.out.print("Path: ");
//        String filePath = sc.nextLine();
//        System.out.println();
//
//        LSBSteganography lsb = new LSBSteganography();
//        lsb.setSecretData(secret);
//        ImageProcessor image = new ImageProcessor(filePath);
//        lsb.setCoverImage(image);
//        System.out.println("width: " + lsb.image.getImgWidth());
//        System.out.println("height: " + lsb.image.getImgHeight());
//        System.out.println();
//
//        lsb.image.getPixels(lsb.image.img);
//        int[][][] pixelAwal = lsb.image.getPixels(lsb.image.img);
//        int nopixel = 0;
//        for (int y = 0; y < lsb.image.getImgHeight(); y++) {
//            for (int x = 0; x < lsb.image.getImgWidth(); x++) {
//                System.out.println(nopixel);
//                System.out.println("red: " + pixelAwal[y][x][0]);
//                System.out.println("green: " + pixelAwal[y][x][1]);
//                System.out.println("blue: " + pixelAwal[y][x][2]);
//                System.out.println();
//                nopixel++;
//            }
//        }
//        System.out.println();
//
//        lsb.hideSecretData();
//        lsb.image.createImage();
//
//        System.out.print("Path: ");
//        String filePathAfter = sc.nextLine();
//        System.out.println();
//
//        ImageProcessor img = new ImageProcessor(filePathAfter);
//        int[][][] pixel = img.getPixels(img.img);
//        int nopixel1 = 0;       
//        for (int y1 = 0; y1 < img.getImgHeight(); y1++) {
//            for (int x1 = 0; x1 < img.getImgWidth(); x1++) {
//                System.out.println(nopixel1);
//                System.out.println("red: " + pixel[y1][x1][0]);
//                System.out.println("green: " + pixel[y1][x1][1]);
//                System.out.println("blue: " + pixel[y1][x1][2]);
//                System.out.println();
//                nopixel1++;
//            }
//        }
//        String secretData = lsb.extractSecretData(img, secret.length());
//        System.out.println(secretData);

        //mpit tester
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Secret Data: ");
//        String secret = sc.nextLine();
//        System.out.print("Path: ");
//        String filePath = sc.nextLine();
//        System.out.println();
//        MPITSteganography mpit = new MPITSteganography();
//        mpit.setSecretData(secret);
//        ImageProcessor img = new ImageProcessor(filePath);
//        mpit.setCoverImage(img);
//        System.out.println("width: " + mpit.image.getImgWidth());
//        System.out.println("height: " + mpit.image.getImgHeight());
//        System.out.println();
//
//        mpit.image.getPixels(mpit.image.img);
//        int[][][] pixelAwal = mpit.image.getPixels(mpit.image.img);
//        int nopixel = 0;
//        for (int y = 0; y < mpit.image.getImgHeight(); y++) {
//            for (int x = 0; x < mpit.image.getImgWidth(); x++) {
//                System.out.println(nopixel);
//                System.out.println("red: " + pixelAwal[y][x][0]);
//                System.out.println("green: " + pixelAwal[y][x][1]);
//                System.out.println("blue: " + pixelAwal[y][x][2]);
//                System.out.println();
//                nopixel++;
//            }
//        }
//        System.out.println("random number: "+mpit.generateRandomNumber());
//        
//        
//        mpit.hideSecretData(mpit.randNumber);        
//        mpit.image.createImage();
//        System.out.println();
//        System.out.print("Path: ");
//        String filePathAfter = sc.nextLine();
//        System.out.println();
//                
//        ImageProcessor img1 = new ImageProcessor(filePathAfter);
//        mpit.setCoverImage(img1);
//        int[][][] pixel = img1.getPixels(img1.img);
//        int nopixel1 = 0;
//        for (int y1 = 0; y1 < img1.getImgHeight(); y1++) {
//            for (int x1 = 0; x1 < img1.getImgWidth(); x1++) {
//                System.out.println(nopixel1);
//                System.out.println("red: " + pixel[y1][x1][0]);
//                System.out.println("green: " + pixel[y1][x1][1]);
//                System.out.println("blue: " + pixel[y1][x1][2]);
//                System.out.println();
//                nopixel1++;
//            }
//        }
//        System.out.print("random input: ");
//        int rand = sc.nextInt();
//        System.out.println();
//        System.out.print("secret length: ");
//        int sl = sc.nextInt();
//        System.out.println(mpit.extractSecretData(img1, rand, sl));        
//        System.out.println("The End");
        //pit tester
        Scanner sc = new Scanner(System.in);
        System.out.print("Secret Data: ");
        String secret = sc.nextLine();
        System.out.print("Path: ");
        String filePath = sc.nextLine();
        System.out.println();
        PITSteganography pit = new PITSteganography();
        pit.setSecretData(secret);
        ImageProcessor img = new ImageProcessor(filePath);
        pit.setCoverImage(img);
        System.out.println("width: " + pit.image.getImgWidth());
        System.out.println("height: " + pit.image.getImgHeight());
        System.out.println();
        
        System.out.println(pit.getIndicatorChannel(pit.secretDataLength));
        System.out.println(pit.get1stChannel(pit.secretDataLength));
        System.out.println(pit.get2ndChannel(pit.secretDataLength));

        pit.image.getPixels(pit.image.img);
        int[][][] pixelAwal = pit.image.getPixels(pit.image.img);
        int nopixel = 0;
        for (int y = 0; y < pit.image.getImgHeight(); y++) {
            for (int x = 0; x < pit.image.getImgWidth(); x++) {
                System.out.println(nopixel);
                System.out.println("red: " + pixelAwal[y][x][0]);
                System.out.println("green: " + pixelAwal[y][x][1]);
                System.out.println("blue: " + pixelAwal[y][x][2]);
                System.out.println();
                nopixel++;
            }
        }

        pit.hideSecretData();
        pit.image.createImage();
        System.out.println();
        System.out.print("Path: ");
        String filePathAfter = sc.nextLine();
        System.out.println();

        ImageProcessor img1 = new ImageProcessor(filePathAfter);
        pit.setCoverImage(img1);
        int[][][] pixel = img1.getPixels(img1.img);
        int nopixel1 = 0;
        for (int y1 = 0; y1 < img1.getImgHeight(); y1++) {
            for (int x1 = 0; x1 < img1.getImgWidth(); x1++) {
                System.out.println(nopixel1);
                System.out.println("red: " + pixel[y1][x1][0]);
                System.out.println("green: " + pixel[y1][x1][1]);
                System.out.println("blue: " + pixel[y1][x1][2]);
                System.out.println();
                nopixel1++;
            }
        }
        System.out.println(pit.extractSecretData(img1));
        //image processor tester
//        Scanner sc = new Scanner(System.in);
//        System.out.print("original image path: ");
//        String path1 = sc.nextLine();
//        ImageProcessor img1 = new ImageProcessor(path1);
//        System.out.println();
//        System.out.print("stego image path: ");
//        String path2 = sc.nextLine();
//        ImageProcessor img2 = new ImageProcessor(path2);
//        System.out.println("psnr = "+img1.calculatePSNR(img2));
        //chisquare tester
//        Scanner sc = new Scanner(System.in);
//        ChiSquareAttack chi = new ChiSquareAttack();
//        System.out.println("Image Path: ");
//        String path = sc.nextLine();
//        chi.setImage(path);
//        double[] pr = chi.chiAnalysis();
//        System.out.println("red: " + pr[0]);
//        System.out.println("green: " + pr[1]);
//        System.out.println("blue: " + pr[2]);
    }
}
