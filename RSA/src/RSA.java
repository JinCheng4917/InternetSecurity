import function.MyFunction;

import java.io.*;
import java.util.*;

class RSA {

  private static HashMap<String, String> encodeStringAndFileNameHashMap = new HashMap<>();
  //欧拉函数值
  private static int ourlaNum = 0;
  //N的值
  private static int NNum = 0;
  //D的值
  private static int DNum = 0;
  //K的值
  private static int KNum = 0;

  private static String mimePublicEncodedString = "";

  private static String mimePrivateEncodedString = "";

  public static void main(String[] args) throws UnsupportedEncodingException {


    MyFunction myFunction = new MyFunction();
    System.out.println("开始生成密钥");
    int num1 = new Random().nextInt(100);
    int num2 = new Random().nextInt(100);
    while (true) {
      if (myFunction.isPrime(num2) && myFunction.isPrime(num1) && num2 > 10 && num1 > 10) {
        NNum = myFunction.karatsuba(num1, num2);
        ourlaNum = myFunction.minCommonMultiple(num1 - 1, num2 - 1);
        int eNum = new Random().nextInt(ourlaNum - 1);
        while (true) {
          if (myFunction.maxCommonDivisor(eNum, ourlaNum) == 1 && eNum > 1 && eNum < 50) {
            ArrayList nums = myFunction.getDNumAndKNum(eNum, ourlaNum);
            DNum = (int) nums.get(0);
            String publicKey = "hebut" + (eNum + NNum);
            String privateKey = "iHebut" + (DNum + NNum);
            mimePublicEncodedString = Base64.getMimeEncoder().encodeToString(publicKey.getBytes("utf-8"));
            mimePrivateEncodedString = Base64.getMimeEncoder().encodeToString(privateKey.getBytes("utf-8"));
            System.out.println("公钥为：" + mimePublicEncodedString);
            System.out.println("私钥为：" + mimePrivateEncodedString);


            System.out.println("请输您要加密的内容：1--字符/字符串  2--文件/文件夹");
            Scanner selectScanner = new Scanner(System.in);

            while (true) {
              try {
                Integer selectKey = selectScanner.nextInt();
                if (selectKey.equals(1) || selectKey.equals(2) || selectKey.equals(3)) {
                  switch (selectKey) {
                    case 1: {

                      System.out.println("请输入明文：");
                      Scanner input = new Scanner(System.in);
                      String theInput = input.nextLine();
                      String unicodeString = myFunction.string2Unicode(theInput);

                      /**
                       * 将输入的字节转换为ascii码list
                       */
                      ArrayList asciiList = myFunction.stringToAscii(unicodeString);

                      ArrayList encodeList = myFunction.encode(asciiList, mimePublicEncodedString, NNum);
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
                              String decodeUnicodeString = myFunction.asciiToString(decodeStringList);
                              System.out.println("**************************      解密完成     *******************************");
                              String decodeString = myFunction.unicode2String(decodeUnicodeString);
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
                        myFunction.createDir("handledRSA");
                        myFunction.createDir("handledRSA/encodedRSA");
                        myFunction.createDir("handledRSA/decodedRSA");
                        File file = new File(fileDire);
                        if (file.isFile()) {
                          System.out.println("您选择的是文件");
                          encodeFile(file, myFunction);
                          System.out.println("是否进行解密?(Y/n)");
                          Scanner decodeScanner = new Scanner(System.in);
                          while (true) {
                            String decodeInput = decodeScanner.nextLine();
                            if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
                              switch (decodeInput) {
                                case "Y":
                                case "": {
                                  decodeFile(file, myFunction, encodeStringAndFileNameHashMap.get(file.getName()));
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
          } else {
            eNum = new Random().nextInt(ourlaNum);
            continue;
          }
        }
      } else {
        num1 = new Random().nextInt(100);
        num2 = new Random().nextInt(100);
        continue;
      }
    }
  }


  public static String fileToByte(String filepath) throws IOException {
    String content = "";
    try {
      File file = new File(filepath);
      BufferedReader in = new BufferedReader(new FileReader(file));
      String str;
      while ((str = in.readLine()) != null) {
        content = str;
      }
    } catch (IOException e) {
    }
    return content;
  }

  public static void encodeFileDir(File file, MyFunction myFunction) {
    try {
      File[] filesArray = file.listFiles();
      for (int i = 0; i < filesArray.length; i++) {
        if (filesArray[i].isFile()) {
          encodeFile(filesArray[i], myFunction);
        } else {
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
          decodeFile(filesArray[i], myFunction, encodeStringAndFileNameHashMap.get(filesArray[i].getName()));
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
    String fileInput = fileToByte(file.getPath());
    System.out.println("文件名为：" + file.getName());
    System.out.println("文件内容为：  " + fileInput);
    String unicodeString = myFunction.string2Unicode(fileInput);

    /**
     * 将输入的字节转换为ascii码list
     */
    ArrayList asciiList = myFunction.stringToAscii(unicodeString);

    ArrayList encodeList = myFunction.encode(asciiList, mimePublicEncodedString, NNum);
    myFunction.createFile("handledRSA/encodedRSA/" +file.getName() + System.currentTimeMillis() + ".txt");
    File encodeFile = new File("handledRSA/encodedRSA/" +file.getName() + System.currentTimeMillis() + ".txt");
    String encodeString = myFunction.asciiToString(encodeList);
    System.out.println("**************************      加密完成     *******************************");
    encodeStringAndFileNameHashMap.put(file.getName(), encodeString);
    System.out.println("加密之后的字符为： " + encodeString);
    System.out.println("         ");


    encodeStringAndFileNameHashMap.put(file.getName(), encodeString);
    FileOutputStream fileOutputStream = new FileOutputStream(encodeFile);
    fileOutputStream.write(encodeString.getBytes());
  }

  public static void decodeFile(File file, MyFunction myFunction, String encodeString) {
    System.out.println("是否进行解密?(Y/n)");
    Scanner decodeScanner = new Scanner(System.in);
    while (true) {
      String decodeInput = decodeScanner.nextLine();
      if (decodeInput.equals("Y") || decodeInput.equals("n") || decodeInput.equals("")) {
        switch (decodeInput) {
          case "Y":
          case "": {
            try {
              myFunction.createFile("handledRSA/decodedRSA/" +file.getName() + System.currentTimeMillis() + ".txt");
              File decodeFile = new File("handledRSA/decodedRSA/" + file.getName() + System.currentTimeMillis() + ".txt");
              System.out.println("进行解密");
              FileWriter fileWriter = new FileWriter(decodeFile);
              ArrayList decodeStringList = myFunction.decode(encodeString, mimePrivateEncodedString, NNum);
              String decodeUnicodeString = myFunction.asciiToString(decodeStringList);
              System.out.println("**************************      解密完成     *******************************");
              String decodeString = myFunction.unicode2String(decodeUnicodeString);
              System.out.println("解密之后的字符为： " + decodeString);
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