
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
//        LSBSteganography lsb = new LSBSteganography(secret, filePath);
//        String biner = lsb.secretDataToBinary();
//        System.out.println(biner);
//        System.out.println(lsb.binaryToSecretData(biner));
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

        //pit tester
        Scanner sc = new Scanner(System.in);
        System.out.print("Secret Data: ");
        String secret = sc.nextLine();
        System.out.print("Path: ");
        String filePath = sc.nextLine();
        System.out.println();

        PITSteganography pit = new PITSteganography(secret, filePath);
        System.out.println("width: " + pit.image.getImgWidth());
        System.out.println("height: " + pit.image.getImgHeight());
        System.out.println("random: " + pit.generateRandomNumber());
        System.out.println();

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
        System.out.println();

        pit.hideSecretData();
        pit.image.createImage();

        System.out.print("Path: ");
        String filePathAfter = sc.nextLine();
        System.out.println();

        ImageProcessor img = new ImageProcessor(filePathAfter);
        int[][][] pixel = img.getPixels(img.img);
        int nopixel1 = 0;
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
