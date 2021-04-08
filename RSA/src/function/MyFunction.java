package function;

import java.math.BigInteger;
import java.util.ArrayList;

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
    System.out.println(nums);
    return nums;
  }

  /**
   * 加密
   *  @param asciiList
   * @param eNum
   * @param nNum
   * @return
   */
  public ArrayList<Integer> encode(ArrayList asciiList, int eNum, int nNum) {
    ArrayList<Integer> encodeList = new ArrayList<Integer>();
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
   * @param dNum
   * @param nNum
   * @return
   */
  public ArrayList<Integer> decode(String decodeString, int dNum, int nNum) {
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
