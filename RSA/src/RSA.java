import function.MyFunction;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

class RSA {
  public static void main(String[] args) throws UnsupportedEncodingException {
    //欧拉函数值
    int ourlaNum = 0;
    //N的值
    int NNum = 0;
    //D的值
    int DNum = 0;
    //K的值
    int KNum = 0;
    MyFunction myFunction = new MyFunction();
    int num1 = new Random().nextInt(100);
    int num2 = new Random().nextInt(100);
    while (true) {
      if (myFunction.isPrime(num2) && myFunction.isPrime(num1) && num2 > 10 && num1 > 10) {
        NNum = myFunction.karatsuba(num1, num2);
        ourlaNum = myFunction.minCommonMultiple(num1 - 1, num2 - 1);
        int eNum = new Random().nextInt(ourlaNum -1);
        while (true) {
          if (myFunction.maxCommonDivisor(eNum, ourlaNum) == 1 && eNum > 1 && eNum < 50) {
            ArrayList nums = myFunction.getDNumAndKNum(eNum, ourlaNum);
            DNum = (int) nums.get(0);
            String publicKey = "hebut" + (eNum + NNum);
            String privateKey = "iHebut" + (DNum + NNum);
            String mimePublicEncodedString = Base64.getMimeEncoder().encodeToString(publicKey.getBytes("utf-8"));
            String mimePrivateEncodedString = Base64.getMimeEncoder().encodeToString(privateKey.getBytes("utf-8"));
            System.out.println("公钥为：" + mimePublicEncodedString);
            System.out.println("私钥为：" + mimePrivateEncodedString);

            System.out.println("请输入明文：");
            Scanner input = new Scanner(System.in);
            String theInput =  input.next();

            /**
             * 将输入的字节转换为ascii码list
             */
            ArrayList asciiList = myFunction.stringToAscii(theInput);

            ArrayList encodeList = myFunction.encode(asciiList,mimePublicEncodedString, NNum);
            String encodeString = myFunction.asciiToString(encodeList);
            System.out.println("**************************      加密完成     *******************************");
            System.out.println("加密之后的字符为： " + encodeString);

            System.out.println("          ");
            System.out.println("是否进行解密?(Y/n)");
            Scanner decodeScanner = new Scanner(System.in);

            while (true) {
              String decodeInput = decodeScanner.nextLine();
              if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
                switch (decodeInput) {
                  case "Y":
                  case "": {
                    System.out.println("进行解密");
                    ArrayList decodeStringList = myFunction.decode(encodeString, mimePrivateEncodedString, NNum);
                    String decodeString = myFunction.asciiToString(decodeStringList);
                    System.out.println("解密之后的字符为： " + decodeString);
                    break;
                  }
                  case "n": {
                    System.out.println("您没有选择解密");
                    break;
                  }
                }
                break;
              } else {
                System.out.println("您的输入有误，请重新输入： ");
                Scanner newScanner = new Scanner(System.in);
                decodeScanner = newScanner;
                continue;
              }
            }
            break;
          }else {
            eNum = new Random().nextInt(ourlaNum);
            continue;
          }
        }

        break;
      } else {
        num1 = new Random().nextInt(100);
        num2 = new Random().nextInt(100);
        continue;
      }
    }
  }
}