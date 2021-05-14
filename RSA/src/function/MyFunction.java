package function;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;

public class MyFunction {
  /**
   * ASCII码转换为String
   *
   * @param value
   * @return
   */
  public String asciiToString(ArrayList<Integer> value) {
    StringBuffer sbu = new StringBuffer();
    for (int i = 0; i < value.size(); i++) {
      sbu.append((char) Integer.parseInt(value.get(i).toString()));
    }
    return sbu.toString();
  }


  /**
   * 将字符串转换为ASCII码
   *
   * @param value
   * @return
   */
  public ArrayList<Integer> stringToAscii(String value) {
    ArrayList<Integer> asciiArray = new ArrayList<Integer>();
    char[] chars = value.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      asciiArray.add((int) chars[i]);
    }
    return asciiArray;
  }

  /**
   * Karatsuba乘法
   */
  public static int karatsuba(long num1, long num2) {
    //递归终止条件
    if (num1 < 10 || num2 < 10) return (int) (num1 * num2);

    // 计算拆分长度
    int size1 = String.valueOf(num1).length();
    int size2 = String.valueOf(num2).length();
    int halfN = Math.max(size1, size2) / 2;

    /* 拆分为a, b, c, d */
    long a = Long.valueOf(String.valueOf(num1).substring(0, size1 - halfN));
    long b = Long.valueOf(String.valueOf(num1).substring(size1 - halfN));
    long c = Long.valueOf(String.valueOf(num2).substring(0, size2 - halfN));
    long d = Long.valueOf(String.valueOf(num2).substring(size2 - halfN));

    // 计算z2, z0, z1, 此处的乘法使用递归
    long z2 = karatsuba(a, c);
    long z0 = karatsuba(b, d);
    long z1 = karatsuba((a + b), (c + d)) - z0 - z2;

    return (int) (z2 * Math.pow(10, (2 * halfN)) + z1 * Math.pow(10, halfN) + z0);
  }

  /**
   * 最大公约数
   * @param m
   * @param n
   * @return
   */
  public int maxCommonDivisor(int m, int n) {
    if (m < n) {     // 保证被除数大于除数
      int temp = m;
      m = n;
      n = temp;
    }
    while (m % n != 0) {  // 在余数不能为0时,进行循环
      int temp = m % n;
      m = n;
      n = temp;
    }
    return n;    // 返回最大公约数
  }

  /**
   * 最小公倍数
   * @param m
   * @param n
   * @return
   */
  public int minCommonMultiple(int m, int n) {
    return m * n / this.maxCommonDivisor(m, n);
  }


  /**
   * 判断是否为质数
   *
   * @param num
   * @return
   */
  public boolean isPrime(int num) {
    boolean isPrime = true;
    if (num > 0) {
      int k = (int) Math.sqrt(num);//k为num的正平方根，取整数
      for (int i = 2; i <= k; i++) {
        if (num % i == 0) {
          isPrime = false;//不是素数
          break;
        }
      }
    }
    return isPrime;
  }

  /**
   * 判断两数是否互质
   *
   * @param m
   * @param n
   * @return
   */
  public boolean checkPrime(int m, int n) {
    boolean isPrime = false;
    int k = 0;
    for (k = m; 0 != k % n; k += m) ;
    if (k == m * n) {
      isPrime = true;
    } else {
      isPrime = false;
    }
    return isPrime;
  }

  /**
   * 获取e和k的值
   *
   * @param eNum
   * @param ourlaNum
   * @return
   */
  public ArrayList<Integer> getDNumAndKNum(int eNum, int ourlaNum) {
    ArrayList<Integer> nums = new ArrayList<>();
    float dNum = 0;
    float kNum = 0;
    for (int i = 0; i < ourlaNum; i++) {
      dNum = i;
      kNum = (eNum * dNum - 1) / ourlaNum;
      if (kNum % 1 == 0) {
        nums.add((int) dNum);
        nums.add((int) kNum);
      }
    }
    return nums;
  }

  /**
   * 创建文件
   * @param destFileName
   * @return
   */
  public boolean createFile(String destFileName) {
    File file = new File(destFileName);
    if(file.exists()) {
      System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
      return false;
    }
    if (destFileName.endsWith(File.separator)) {
      System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
      return false;
    }
    //判断目标文件所在的目录是否存在
    if(!file.getParentFile().exists()) {
      //如果目标文件所在的目录不存在，则创建父目录
      System.out.println("目标文件所在目录不存在，准备创建它！");
      if(!file.getParentFile().mkdirs()) {
        System.out.println("创建目标文件所在目录失败！");
        return false;
      }
    }
    //创建目标文件
    try {
      if (file.createNewFile()) {
        System.out.println("创建单个文件" + destFileName + "成功！");
        return true;
      } else {
        System.out.println("创建单个文件" + destFileName + "失败！");
        return false;
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
      return false;
    }
  }


  /**
   * 创建文件夹
   * @param destDirName
   * @return
   */
  public boolean createDir(String destDirName) {
    File dir = new File(destDirName);
    if (dir.exists()) {
      System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
      return false;
    }
    if (!destDirName.endsWith(File.separator)) {
      destDirName = destDirName + File.separator;
    }
    //创建目录
    if (dir.mkdirs()) {
      System.out.println("创建目录" + destDirName + "成功！");
      return true;
    } else {
      System.out.println("创建目录" + destDirName + "失败！");
      return false;
    }
  }

  /**
   * 字符串转换unicode
   * @param string
   * @return
   */
  public  String string2Unicode(String string) {
    StringBuffer unicode = new StringBuffer();
    for (int i = 0; i < string.length(); i++) {
      // 取出每一个字符
      char c = string.charAt(i);
      // 转换为unicode
      unicode.append("\\u" + Integer.toHexString(c));
    }

    return unicode.toString();
  }

  /**
   * unicode 转字符串
   * @param unicode 全为 Unicode 的字符串
   * @return
   */
  public String unicode2String(String unicode) {
    StringBuffer string = new StringBuffer();
    String[] hex = unicode.split("\\\\u");

    for (int i = 1; i < hex.length; i++) {
      // 转换出每一个代码点
      int data = Integer.parseInt(hex[i], 16);
      // 追加成string
      string.append((char) data);
    }

    return string.toString();
  }


  /**
   * 加密
   *  @param asciiList
   * @param nNum
   * @return
   */
  public ArrayList<Integer> encode(ArrayList asciiList,String mimeEncodeString, int nNum) throws UnsupportedEncodingException {
    ArrayList<Integer> encodeList = new ArrayList<>();
    byte[] mimePublicByte = Base64.getMimeDecoder().decode(mimeEncodeString.getBytes());
    String mimePublicString = new String(mimePublicByte, "utf-8");
    Integer eNum = Integer.parseInt(mimePublicString.substring(5)) - nNum;
    asciiList.forEach(ascii -> {
      BigInteger asciiBigInteger = new BigInteger(ascii.toString());
      BigInteger eNumBigInteger = new BigInteger(String.valueOf(eNum));
      BigInteger nNumBigInteger = new BigInteger(String.valueOf(nNum));
      BigInteger exponent = new BigInteger(String.valueOf(asciiBigInteger.modPow(eNumBigInteger, nNumBigInteger)));
      BigInteger encodeAscii = exponent.mod(BigInteger.valueOf(nNum));
      encodeList.add(encodeAscii.intValue());
    });
    return encodeList;
  }


  /**
   * 解密
   * @param decodeString
   * @param nNum
   * @return
   */
  public ArrayList<Integer> decode(String decodeString, String mimeEncodeString, int nNum) throws UnsupportedEncodingException {
    byte[] mimePrivateByte = Base64.getMimeDecoder().decode(mimeEncodeString.getBytes());
    String mimePrivateString = new String(mimePrivateByte, "utf-8");
    Integer dNum = Integer.parseInt(mimePrivateString.substring(6)) - nNum;
    ArrayList asciiList = this.stringToAscii(decodeString);
    ArrayList<Integer> decodeList = new ArrayList<Integer>();
    asciiList.forEach(ascii -> {
      BigInteger asciiBigInteger = new BigInteger(ascii.toString());
      BigInteger dNumBigInteger = new BigInteger(String.valueOf(dNum));
      BigInteger nNumBigInteger = new BigInteger(String.valueOf(nNum));
      BigInteger exponent = new BigInteger(String.valueOf(asciiBigInteger.modPow(dNumBigInteger, nNumBigInteger)));
      BigInteger decodeAscii = exponent.mod(BigInteger.valueOf(nNum));
      decodeList.add(decodeAscii.intValue());
    });
    return decodeList;
  }
}
