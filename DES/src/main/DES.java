package main;

import function.MyFunction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class DES {

    private static String byteKey = "";

    public static void main(String[] args) {
        MyFunction myFunction = new MyFunction();


        System.out.println("请输入要加密的字符");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        /**
         * 将输入的字节转换为ascii码list
         */
        ArrayList asciiList = myFunction.stringToAscii(input);


        /**
         * ascii码转十进制并放到数组中
         */
        ArrayList<String> decimalList = new ArrayList<String>();
        asciiList.forEach(ascii -> {
            String decimalString = myFunction.decimalToBinaryToEight((Integer) ascii);
            decimalList.add(decimalString);
        });

        /**
         * 每8个字节生成一个数组元素
         */
        ArrayList byteList = myFunction.getEightByteArray(input);

        /**
         * 8个字节共生成64位二进制，将其拼接，存于ArrayList
         */
        ArrayList<String> bitStringList = new ArrayList<>();
        byteList.forEach(eightByte -> {
            bitStringList.add(myFunction.getBitString(eightByte.toString()));
        });

        /**
         * 进行IP置换,将置换之后的字符存入数组中
         */
        ArrayList<String> exchangedBitStringList = new ArrayList<>();
        bitStringList.forEach(bitString -> {
            exchangedBitStringList.add(myFunction.IPExchange(bitString));
        });

        System.out.println("请输入8个字节的密钥：");
        Scanner keyScanner = new Scanner(System.in);

        while (true) {
            String byteKey = keyScanner.nextLine();
            if (byteKey.length() != 8) {
                System.out.println("密钥长度不正确，请重新输入： ");
                Scanner newScanner = new Scanner(System.in);
                keyScanner = newScanner;
                continue;
            } else {
                DES.byteKey = byteKey;
                break;
            }
        }


        /**
         * 将数据分成两个32位,然后传入密钥进行加工处理
         */
        AtomicReference<String> encodeString = new AtomicReference<>("");
        exchangedBitStringList.forEach(exchangedBitString -> {
            String string = myFunction.encode(exchangedBitString, byteKey);
            encodeString.set(encodeString + string);
        });
        System.out.println("**************************      加密完成     *******************************");
        System.out.println("加密之后的字符为： " + encodeString.get());

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
                        String decodeString = myFunction.decode(encodeString.get());
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
    }
}
