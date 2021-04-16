package main;

import function.MyFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class DES {

    private static String byteKey = "";
    private static  HashMap<String, String> encodeStringAndFileNameHashMap = new HashMap<>();

    public static void main(String[] args) {
        MyFunction myFunction = new MyFunction();


        System.out.println("请输您要加密的内容：1--字符/字符串  2--文件/文件夹");
        Scanner selectScanner = new Scanner(System.in);

        while (true) {
            try {
                Integer selectKey = selectScanner.nextInt();
                if (selectKey.equals(1) || selectKey.equals(2) || selectKey.equals(3)) {
                    switch (selectKey) {
                        case 1: {
                            System.out.println("请输入要加密的字符(串)");
                            Scanner scanner = new Scanner(System.in);
                            String input = scanner.nextLine();

                            /**
                             * 将输入的字节转换为ascii码list
                             */
                            ArrayList asciiList = myFunction.stringToAscii(input);
                            String encodeString = encodeAndDecode(input, asciiList, myFunction);
                            System.out.println("是否进行解密?(Y/n)");
                            Scanner decodeScanner = new Scanner(System.in);

                            while (true) {
                                String decodeInput = decodeScanner.nextLine();
                                if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
                                    switch (decodeInput) {
                                        case "Y":
                                        case "": {
                                            System.out.println("进行解密");
                                            String decodeString = myFunction.decode(encodeString);
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
                        }
                        case 2: {
                            System.out.println("请输入要加密的文件路径及名称");
                            Scanner scanner = new Scanner(System.in);
                            String fileDire = scanner.nextLine();
                            try {
                                File file = new File(fileDire);
                                if (file.isFile()) {
                                    System.out.println("您选择的是文件");
                                    byte[] bytes = fileToByte(fileDire);
                                    ArrayList<Integer> bytesList = new ArrayList<>();
                                    for (int i = 0; i < bytes.length; i++) {
                                        bytesList.add((int) bytes[i]);
                                    }
                                    String fileInput = myFunction.asciiToString(bytesList);
                                    String encodeString = encodeAndDecode(fileInput, bytesList, myFunction);
                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(encodeString.getBytes());
                                    System.out.println("是否进行解密?(Y/n)");
                                    Scanner decodeScanner = new Scanner(System.in);

                                    while (true) {
                                        String decodeInput = decodeScanner.nextLine();
                                        if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
                                            switch (decodeInput) {
                                                case "Y":
                                                case "": {
                                                    System.out.println("进行解密");
                                                    String decodeString = myFunction.decode(encodeString);
                                                    FileWriter fileWriter = new FileWriter(file);
                                                    fileWriter.write(decodeString);
                                                    fileWriter.close();
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
                                } else {
                                    //加密文件夹
                                    System.out.println("您选择的是文件夹");
                                    System.out.println(file);
                                    encodeFileDir(file, myFunction);
                                }

                            } catch (Exception e) {
                                System.out.println("出现错误，原因为： " + e);
                            }

                            break;
                        }
                    }
                } else {
                    System.out.println("您的输入有误，请重新输入： ");
                    Scanner newScanner = new Scanner(System.in);
                    selectScanner = newScanner;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("您的输入有误，请重新输入选项： ");
                Scanner newScanner = new Scanner(System.in);
                selectScanner = newScanner;
                continue;
            }
        }
    }

    public static byte[] fileToByte(String filepath) throws IOException {
        byte[] bytes = null;
        FileInputStream fis = null;
        try {
            File file = new File(filepath);
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            fis.close();
        }
        return bytes;
    }

    public static String encodeAndDecode(String input, ArrayList asciiList, MyFunction myFunction) {

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
        return encodeString.get();
    }

    public static void encodeFileDir(File file, MyFunction myFunction) {
        try {
            File[] filesArray = file.listFiles();
            for (int i = 0; i < filesArray.length; i++) {
                if (filesArray[i].isFile()) {
                    encodeFile(filesArray[i], myFunction);
                } else {
                    System.out.println(filesArray[i]);
                    encodeFileDir(filesArray[i], myFunction);
                }
            }
            decodeFileDir(file, myFunction);
        } catch (Exception e) {
            System.out.println("出现问题，原因为： " + e);
        }
    }

    public static void decodeFileDir(File file, MyFunction myFunction) {
        try {
            File[] filesArray = file.listFiles();
            for (int i = 0; i < filesArray.length; i++) {
                if (filesArray[i].isFile()) {
                    decodeFile(filesArray[i], myFunction);
                } else {
                    System.out.println(filesArray[i]);
                    decodeFileDir(filesArray[i], myFunction);
                }
            }
        } catch (Exception e) {
            System.out.println("出现问题，原因为： " + e);
        }
    }

    public static void encodeFile(File file, MyFunction myFunction) throws IOException {
        byte[] bytes = fileToByte(file.getPath());
        ArrayList<Integer> bytesList = new ArrayList<>();
        for (int j = 0; j < bytes.length; j++) {
            bytesList.add((int) bytes[j]);
        }
        String fileInput = myFunction.asciiToString(bytesList);
        System.out.println("文件内容为" + fileInput);
        String encodeString = encodeAndDecode(fileInput, bytesList, myFunction);
        encodeStringAndFileNameHashMap.put(file.getName(), encodeString);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(encodeString.getBytes());
    }

    public static void decodeFile(File file, MyFunction myFunction) {
        System.out.println("文件名为：" + file.getName());
        System.out.println("是否进行解密?(Y/n)");
        Scanner decodeScanner = new Scanner(System.in);
        while (true) {
            String decodeInput = decodeScanner.nextLine();
            if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
                switch (decodeInput) {
                    case "Y":
                    case "": {
                        System.out.println("进行解密");
                            try {
                                System.out.println("需要解密的字符为： " + encodeStringAndFileNameHashMap.get(file.getName()));
                                String decodeString = myFunction.decode(encodeStringAndFileNameHashMap.get(file.getName()));
                                FileWriter fileWriter = new FileWriter(file);
                                System.out.println("解密之后的字符为：" + decodeString);
                                System.out.println("    ");
                                fileWriter.write(decodeString);
                                fileWriter.close();
                            } catch (Exception e) {
                                System.out.println("问题原因为： " + e);
                            }
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
