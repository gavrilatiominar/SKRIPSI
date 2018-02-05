
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
        //binary converter tester
//        Scanner sc = new Scanner(System.in);
//        String scrt = sc.nextLine();
//        String path = sc.nextLine();
//        PITSteganography s = new PITSteganography(scrt, path);
//        System.out.println(s.secretDataToBinary());
//        
//        if(s.oddParity()){
//            System.out.println("ODD");
//        } else {
//            System.out.println("EVEN");
//        }
//        System.out.println("Convert to text");
//        System.out.println(s.binaryToSecretData(s.secretDataToBinary()));

        //image processor tester
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Path: ");
//        String filePath = sc.nextLine();
//        System.out.println();
//
//        ImageProcessor img = new ImageProcessor(filePath);
//        System.out.println("width: " + img.getImgWidth());
//        System.out.println("height: " + img.getImgHeight());
//
//        System.out.println();
//        System.out.print("x: ");
//        int x = sc.nextInt();
//
//        System.out.print("y: ");
//        int y = sc.nextInt();
//
//        System.out.println();
//
//        System.out.println("red: " + img.getRedValue(x, y));
//        System.out.println("green: " + img.getGreenValue(x, y));
//        System.out.println("blue: " + img.getBlueValue(x, y));
//
//        System.out.println();
//        System.out.print("red: ");
//        int r = sc.nextInt();
//        System.out.print("green: ");
//        int g = sc.nextInt();
//        System.out.print("blue: ");
//        int b = sc.nextInt();
//        
//        img.setPixelValue(x, y, r, g, b);
//        img.setRedValue(r, x, y);
//        img.setGreenValue(g, x, y);
//        img.setBlueValue(b, x, y);
//        
//        System.out.println("");
//        
//        System.out.print("x1: ");
//        int x1 = sc.nextInt();
//        
//        System.out.print("y1: ");
//        int y1 = sc.nextInt();
//        
//        System.out.println("red: " + img.getRedValue(x1, y1));
//        System.out.println("green: " + img.getGreenValue(x1, y1));
//        System.out.println("blue: " + img.getBlueValue(x1, y1));
//        
//        System.out.println();
//
//        System.out.print("red: ");
//        int r1 = sc.nextInt();
//        System.out.print("green: ");
//        int g1 = sc.nextInt();
//        System.out.print("blue: ");
//        int b1 = sc.nextInt();
//        
//        img.setPixelValue(x1, y1, r1, g1, b1);
//        img.createImage();
        //print semua nilai piksel
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Path: ");
//        String filePath = sc.nextLine();
//        System.out.println();
//
//        ImageProcessor img = new ImageProcessor(filePath);
//        System.out.println("width: " + img.getImgWidth());
//        System.out.println("height: " + img.getImgHeight());
//        System.out.println();
//
//        int[] pixel = img.getPixels(img.coverImage); //nyimpennya 0,0 - 1,0 - 2,0 - dst
//        int nopixel = 0;
//        for (int i = 0; i < pixel.length; i++) {
//            if (i % 3 == 0) {
//                System.out.println(nopixel);
//                System.out.println("red: " + pixel[i]);
//            } else if (i % 3 == 1) {
//                System.out.println("green: " + pixel[i]);
//            } else {
//                System.out.println("blue: " + pixel[i] + "\n");
//                nopixel++;
//            }
//        }
        //pit tester
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Secret data: ");
//        String scrt = sc.nextLine();
//        System.out.println();
//        System.out.print("Path: ");
//        String path = sc.nextLine();
//        System.out.println();
//
//        PITSteganography pit = new PITSteganography(scrt, path);
//        System.out.println(pit.isEven());
//        System.out.println(pit.isPrime());
        //lsb tester
        Scanner sc = new Scanner(System.in);
        System.out.print("Secret Data: ");
        String secret = sc.nextLine();
        System.out.print("Path: ");
        String filePath = sc.nextLine();
        System.out.println();

        LSBSteganography lsb = new LSBSteganography(secret, filePath);
        System.out.println("width: " + lsb.coverImage.getImgWidth());
        System.out.println("height: " + lsb.coverImage.getImgHeight());
        System.out.println();

        //lsb.coverImage.getPixels(lsb.coverImage.coverImage);
        int[][][] pixelAwal = lsb.coverImage.getPixels(lsb.coverImage.coverImage);
        int nopixel = 0;
//        for (int i = 0; i < pixelAwal.length; i++) {
//            if (i % 3 == 0) {
//                System.out.println(nopixel);
//                System.out.println("red: " + pixelAwal[i]);
//            } else if (i % 3 == 1) {
//                System.out.println("green: " + pixelAwal[i]);
//            } else {
//                System.out.println("blue: " + pixelAwal[i] + "\n");
//                nopixel++;
//            }
//        }
        for (int y = 0; y < lsb.coverImage.getImgHeight(); y++) {
            for (int x = 0; x < lsb.coverImage.getImgWidth(); x++) {
                System.out.println(nopixel);
                System.out.println("red: " + pixelAwal[y][x][0]);
                System.out.println("green: " + pixelAwal[y][x][1]);
                System.out.println("blue: " + pixelAwal[y][x][2]);
                System.out.println();
                nopixel++;
            }
        }
        System.out.println();

        lsb.hideSecretData();
        lsb.coverImage.createImage();

        System.out.print("Path: ");
        String filePathAfter = sc.nextLine();
        System.out.println();

        ImageProcessor img = new ImageProcessor(filePathAfter);
        //img.getPixels(img.coverImage);
        int[][][] pixel = img.getPixels(img.coverImage);
        int nopixel1 = 0;
//        for (int i = 0; i < pixel.length; i++) {
//            if (i % 3 == 0) {
//                System.out.println(nopixel1);
//                System.out.println("red: " + pixel[i]);
//            } else if (i % 3 == 1) {
//                System.out.println("green: " + pixel[i]);
//            } else {
//                System.out.println("blue: " + pixel[i] + "\n");
//                nopixel1++;
//            }
//        }        
        for (int y1 = 0; y1 < img.getImgHeight(); y1++) {
            for (int x1 = 0; x1 < img.getImgWidth(); x1++) {
                System.out.println(nopixel1);
                System.out.println("red: " + pixel[y1][x1][0]);
                System.out.println("green: " + pixel[y1][x1][1]);
                System.out.println("blue: " + pixel[y1][x1][2]);
                System.out.println();
                nopixel1++;
            }
        }
    }
}
