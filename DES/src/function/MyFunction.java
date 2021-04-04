package function;

import entity.AnotherEntity;
import entity.IPEntity;
import entity.SEntity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MyFunction {

    /**
     * 64位Bit
     */
    private static String bitString = "";
    /**
     * 密文
     */
    private String encodeString = "";
    private String leftString = "";
    private String rightString = "";
    private String nextString = "";
    /**
     * 明文
     */
    private String decodeString = "";
    /**
     * 密钥数组
     */
    private ArrayList<String> exchangedKeys = new ArrayList<>();
    /**
     * 补全字符的个数
     */
    private Integer num = 0;

    /**
     * 将字符串转换为ASCII码
     *
     * @param value
     * @return
     */
    public ArrayList stringToAscii(String value) {
        ArrayList asciiArray = new ArrayList();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            asciiArray.add((int) chars[i]);
        }
        return asciiArray;
    }

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
     * 十进制转二进制补全8位
     *
     * @param decimal 十进制数据
     */
    public String decimalToBinaryToEight(int decimal) {
        String binary = Integer.toBinaryString(decimal);
        int length = binary.length();
        if (length < 8) {
            for (int i = 0; i < 8 - length; i++) {
                binary = "0" + binary;
            }
        }
        return binary;
    }

    /**
     * 十进制转二进制补全4位
     *
     * @param decimal 十进制数据
     */
    public String decimalToBinaryToFour(int decimal) {
        AtomicReference<String> binary = new AtomicReference<>(Integer.toBinaryString(decimal));
        if (binary.get().length() < 4) {
            switch (binary.get().length()) {
                case 1: {
                    binary.set("000" + binary);
                    break;
                }
                case 2: {
                    binary.set("00" + binary);
                    break;
                }
                case 3: {
                    binary.set("0" + binary);
                    break;
                }
            }
        }
        return binary.get();
    }

    /**
     * 二进制转十进制
     *
     * @param binaryString
     * @return
     */
    public int binaryToDecimal(String binaryString) {
        return Integer.valueOf(binaryString, 2);
    }

    /**
     * 每八个字节分为一个String元素，置入数组中
     */

    public ArrayList<String> getEightByteArray(String input) {
        ArrayList<String> byteList = new ArrayList<>();
        int remainder = input.length() % 8;
        switch (remainder) {
            case 0: {
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                break;
            }
            case 1: {
                this.num = 7;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 1) + "0000000";
                byteList.add(lastByte);
                break;
            }
            case 2: {
                this.num = 6;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 2) + "000000";
                byteList.add(lastByte);
                break;
            }
            case 3: {
                this.num = 5;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 3) + "00000";
                byteList.add(lastByte);
                break;
            }
            case 4: {
                this.num = 4;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 4) + "0000";
                byteList.add(lastByte);
                break;
            }
            case 5: {
                this.num = 3;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 5) + "000";
                byteList.add(lastByte);
                break;
            }
            case 6: {
                this.num = 2;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 6) + "00";
                byteList.add(lastByte);
                break;
            }
            case 7: {
                this.num = 1;
                for (int i = 0; i < input.length() / 8; i++) {
                    int j = i * 8;
                    String eightByte = input.substring(j, j + 8);
                    byteList.add(eightByte);
                }
                String lastByte = input.substring(input.length() - 7) + "0";
                byteList.add(lastByte);
                break;
            }
        }
        return byteList;
    }

    /**
     * 将八个字节的二进制拼接为64位
     */

    public String getBitString(String eightByte) {
        ArrayList byteAsciiList = this.stringToAscii(eightByte.toString());
        ArrayList<String> byteDecimalList = new ArrayList<String>();
        byteAsciiList.forEach(byteAscii -> {
            String decimalString = this.decimalToBinaryToEight((Integer) byteAscii);
            byteDecimalList.add(decimalString);
        });
        AtomicReference<String> middleBitString = new AtomicReference<>("");
        byteDecimalList.forEach(byteDecimal -> {
                    middleBitString.set(middleBitString + byteDecimal);
                    bitString = middleBitString.get();
                }

        );
        return bitString;
    }

    /**
     * 将64位二进制分成8组
     */
    public ArrayList getBitArrayList(String bitString) {
        ArrayList bitArrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            int j = i * 8;
            String byteString = bitString.substring(j, j + 8);
            bitArrayList.add(byteString);
        }
        String byteString = bitString.substring(56);
        bitArrayList.add(byteString);
        return bitArrayList;
    }

    /**
     * 将64位的二进制数据进行IP置换
     */
    public String IPExchange(String bitString) {
        AtomicReference<String> exchangeBitString = new AtomicReference<>("");
        for (int i = 0; i < bitString.length(); i++) {
            exchangeBitString.set(exchangeBitString + bitString.substring(IPEntity.IP[i] - 1, IPEntity.IP[i]));
        }
        return exchangeBitString.get();
    }

    /**
     * 对置换后的64位二进制数据进行加工
     *
     * @param exchangedBitString
     * @return
     */
    public String encode(String exchangedBitString, String byteKey) {

        /**
         * 将输入的密钥转换为ascii码list
         */
        ArrayList asciiList = this.stringToAscii(byteKey);

        /**
         * ascii码转二进制并放到数组中
         */
        ArrayList<String> binaryList = new ArrayList<String>();
        asciiList.forEach(ascii -> {
            String decimalString = this.decimalToBinaryToEight((Integer) ascii);
            binaryList.add(decimalString);
        });

        AtomicReference<String> binaryKey = new AtomicReference<>("");
        binaryList.forEach(decimalKey -> {
            binaryKey.set(binaryKey + decimalKey);
        });


        String leftBitString = exchangedBitString.substring(0, 32);
        String rightBitString = exchangedBitString.substring(32);

        //对密钥进行PC_1置换
        String PCOneExchangedKey = this.PCOneChangeForKey(binaryKey.get());


        //对密钥进行分组
        String firstKeyString = PCOneExchangedKey.substring(0, 28);
        String secondKeyString = PCOneExchangedKey.substring(28);

        //计算16轮密钥
        ArrayList<String> movedFirstKeysList = this.getKeys(firstKeyString);
        ArrayList<String> movedSecondKeysList = this.getKeys(secondKeyString);

        ArrayList<String> movedKeys = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            movedKeys.add(movedFirstKeysList.get(i).toString() + movedSecondKeysList.get(i).toString());
        }

        //对密钥进行PC-2置换，由56位变为48位
        this.exchangedKeys = this.PCTwoExchange(movedKeys);

        this.leftString = leftBitString;
        this.rightString = rightBitString;
        //用轮函数进行加密
        for (int i = 0; i < 16; i++) {
            String nextRightBitString = this.rotation(this.leftString, this.rightString, this.exchangedKeys.get(i));
            this.leftString = this.rightString;
            this.rightString = nextRightBitString;
        }

        //拼接最后一轮得到的左右字符并进行置换
        String toBeChangedString = this.leftString + this.rightString;
        String IPInverseChangedString = this.IPInverseChange(toBeChangedString);
        //将二进制进行转换
        ArrayList byteStringArrayList = this.getBitArrayList(IPInverseChangedString);

        this.encodeString = this.getEncodeString(byteStringArrayList);
        return this.encodeString;
    }

    /**
     * 对二进制数组进行解析
     *
     * @param byteStringArrayList
     * @return
     */
    private String getEncodeString(ArrayList byteStringArrayList) {
        ArrayList<Integer> binaryList = new ArrayList<Integer>();
        byteStringArrayList.forEach(byteString -> {
            int binaryNum = this.binaryToDecimal(byteString.toString());
            binaryList.add(binaryNum);
        });

        String finalString = this.asciiToString(binaryList);

        return finalString;
    }

    /**
     * IP逆置换
     *
     * @return
     */
    private String IPInverseChange(String toBeChangedString) {
        AtomicReference<String> exchangeBitString = new AtomicReference<>("");
        for (int i = 0; i < toBeChangedString.length(); i++) {
            exchangeBitString.set(exchangeBitString + toBeChangedString.substring(IPEntity.IPInverse[i] - 1, IPEntity.IPInverse[i]));
        }
        return exchangeBitString.get();
    }

    /**
     * 将后32位拓展为48位
     *
     * @param secondBitString
     */
    private String ExchangeBits(String secondBitString) {
        AtomicReference<String> eExchangeBitString = new AtomicReference<>("");
        ArrayList<String> toExchangeStringList = new ArrayList<String>();
        ArrayList<String> exchangedStringList = new ArrayList<>();
        AtomicReference<String> eExchangedBitString = new AtomicReference<>("");
        for (int i = 0; i < 7; i++) {
            int j = i * 4;
            String s = secondBitString.substring(j, j + 4);
            toExchangeStringList.add(s);
        }
        toExchangeStringList.add(secondBitString.substring(28));
        /**
         * 将分成的8个组重新拼凑
         */
        for (int m = 0; m < 8; m++) {
            if (m == 0) {
                eExchangeBitString.set(toExchangeStringList.get(7).substring(3) + toExchangeStringList.get(m) + toExchangeStringList.get(m + 1).substring(0, 1));
            } else if (m > 0 && m < 7) {
                eExchangeBitString.set(toExchangeStringList.get(m - 1).substring(3) + toExchangeStringList.get(m) + toExchangeStringList.get(m + 1).substring(0, 1));
            } else {
                eExchangeBitString.set(toExchangeStringList.get(m - 1).substring(3) + toExchangeStringList.get(m) + toExchangeStringList.get(0).substring(0, 1));
            }
            exchangedStringList.add(eExchangeBitString.get());
        }
        exchangedStringList.forEach(exchangedString -> {
            eExchangedBitString.set(eExchangedBitString + exchangedString);
        });
        return eExchangedBitString.get();
    }

    /**
     * 对key进行pc-1置换，由64位变为56位
     *
     * @param key
     * @return
     */
    public String PCOneChangeForKey(String key) {
        AtomicReference<String> PCOneChangeKey = new AtomicReference<>("");
        for (int i = 0; i < 56; i++) {
            PCOneChangeKey.set(PCOneChangeKey + key.substring(AnotherEntity.PC_1Change[i] - 1, AnotherEntity.PC_1Change[i]));
        }
        return PCOneChangeKey.get();
    }

    /**
     * 计算16轮密钥
     *
     * @return
     */
    public ArrayList<String> getKeys(String keyString) {
        ArrayList<String> movedKeysList = new ArrayList<String>();
        int j = 0;
        for (int i = 0; i < 16; i++) {
            j += AnotherEntity.LFT[i];
            String movedString = this.leftMoveIndex(keyString, j);
            movedKeysList.add(movedString);
        }
        return movedKeysList;
    }

    /**
     * 反转字符串（循环交换）
     *
     * @param from
     * @return
     */
    public static String reChange(String from) {
        char[] froms = from.toCharArray();
        int length = froms.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = froms[i];
            froms[i] = froms[length - 1 - i];
            froms[length - 1 - i] = temp;
        }
        return String.valueOf(froms);
    }

    /**
     * 循环左移index位字符串
     * 思想：先部分反转，后整体反转
     *
     * @param from
     * @param index
     * @return
     */
    public String leftMoveIndex(String from, int index) {
        String first = from.substring(0, index);
        String second = from.substring(index);
        first = reChange(first);
        second = reChange(second);
        from = first + second;
        from = reChange(from);
        return from;
    }

    /**
     * 对key进行PC-2置换
     *
     * @param keys
     * @return
     */
    public ArrayList<String> PCTwoExchange(ArrayList<String> keys) {
        ArrayList<String> PCTwoChangedKeys = new ArrayList<String>();
        for (int i = 0; i < keys.size(); i++) {
            AtomicReference<String> PCTwoChangeKey = new AtomicReference<>("");
            for (int j = 0; j < 48; j++) {
                PCTwoChangeKey.set(PCTwoChangeKey + keys.get(i).substring(AnotherEntity.PC_2Change[i] - 1, AnotherEntity.PC_2Change[i]));
            }
            PCTwoChangedKeys.add(PCTwoChangeKey.get());
        }

        return PCTwoChangedKeys;
    }

    /**
     * 轮函数
     */
    public String rotation(String leftString, String rightString, String key) {
        this.nextString = leftString;
        //对第二部分进行拓展
        String EExchangedString = this.ExchangeBits(rightString);
        AtomicReference<String> toCompressedString = new AtomicReference<>("");
        for (int i = 0; i < 47; i++) {
            toCompressedString.set(toCompressedString + String.valueOf(Integer.parseInt(EExchangedString.substring(i, i + 1)) ^ Integer.parseInt(key.substring(i, i + 1))));
        }
        toCompressedString.set(toCompressedString + String.valueOf(Integer.parseInt(EExchangedString.substring(47)) ^ Integer.parseInt(key.substring(47))));
        String compressedString = this.sBox(toCompressedString.get());
        String PExchangedString = this.PExchange(compressedString);
        AtomicReference<String> newString = new AtomicReference<>("");
        for (int i = 0; i < 31; i++) {
            newString.set(newString + String.valueOf(Integer.parseInt(PExchangedString.substring(i, i + 1)) ^ Integer.parseInt(leftString.substring(i, i + 1))));
        }
        newString.set(newString + String.valueOf(Integer.parseInt(PExchangedString.substring(31)) ^ Integer.parseInt(leftString.substring(31))));
        return newString.get();
    }


    /**
     * S盒压缩
     */
    public String sBox(String bitString) {
        ArrayList boxes = new ArrayList();
        for (int i = 0; i < 8; i++) {
            int j = i * 6;
            String cell = bitString.substring(j, j + 6);
            boxes.add(cell);
        }

        ArrayList theNumList = new ArrayList();
        AtomicInteger i = new AtomicInteger(0);
        boxes.forEach(box -> {
            i.set(i.get() + 1);
            String rowBinary = box.toString().substring(0, 1) + box.toString().substring(5);
            String colBinary = box.toString().substring(1, 5);
            int row = this.binaryToDecimal(rowBinary);
            int col = this.binaryToDecimal(colBinary);
            theNumList.add(SEntity.SBox[i.get() - 1][row][col]);

        });

        AtomicReference<String> decimalString = new AtomicReference<>("");
        theNumList.forEach(theNum -> {
            String decimalNum = this.decimalToBinaryToFour(Integer.parseInt(theNum.toString()));
            decimalString.set(decimalString + decimalNum);
        });
        return decimalString.get();
    }

    /**
     * P置换
     */
    public String PExchange(String compressedString) {
        AtomicReference<String> exchangeBitString = new AtomicReference<>("");
        for (int i = 0; i < compressedString.length(); i++) {
            exchangeBitString.set(exchangeBitString + compressedString.substring(AnotherEntity.PChange[i] - 1, AnotherEntity.PChange[i]));
        }
        return exchangeBitString.get();
    }

    /**
     * 解密
     *
     * @param s
     */
    public String decode(String s) {
        ArrayList encodeAsciiList = this.stringToAscii(s);
        ArrayList<String> decimalList = new ArrayList<String>();
        encodeAsciiList.forEach(ascii -> {
            String decimalString = this.decimalToBinaryToEight((Integer) ascii);
            decimalList.add(decimalString);
        });

        /**
         * 每8个字节生成一个数组元素
         */
        ArrayList byteList = getEightByteArray(s);

        /**
         * 8个字节共生成64位二进制，将其拼接，存于ArrayList
         */
        ArrayList<String> bitStringList = new ArrayList<>();
        byteList.forEach(eightByte -> {
            bitStringList.add(this.getBitString(eightByte.toString()));
        });


        ArrayList<String> plaintextList = new ArrayList<>();
        bitStringList.forEach(bitString -> {
            String plaintext = this.inverseChange(bitString);
            plaintextList.add(plaintext);
        });
        ArrayList decodeStringList = new ArrayList();
        plaintextList.forEach(plaintext -> {
            //将二进制进行转换
            ArrayList byteStringArrayList = this.getBitArrayList(plaintext);
            this.decodeString = this.getEncodeString(byteStringArrayList);
            decodeStringList.add(this.decodeString);
        });

        AtomicReference<String> toReturnDecodeString = new AtomicReference<>("");
        decodeStringList.forEach(decodeString -> {
            toReturnDecodeString.set(toReturnDecodeString + decodeString.toString());
        });
        return toReturnDecodeString.get().substring(0, toReturnDecodeString.get().length() - this.num);
    }

    /**
     * 逆向转换
     *
     * @param bitString
     * @return
     */
    private String inverseChange(String bitString) {
        String inversedKey = this.inverseIpInverse(bitString);
        this.decomposeString(inversedKey);
        //用轮函数进行解密
        for (int i = 0; i < 16; i++) {
            String nextRightBitString = this.rotation(this.rightString, this.leftString, this.exchangedKeys.get(15 - i));
            this.rightString = this.leftString;
            this.leftString = nextRightBitString;
        }
        String plaintext = this.leftString + this.rightString;
        return this.inverseIp(plaintext);
    }


    /**
     * 逆向IP逆置换
     *
     * @param bitString
     */
    private String inverseIpInverse(String bitString) {
        ArrayList<String> inversedStringList = new ArrayList<String>();
        for (int j = 0; j < 64; j++) {
            inversedStringList.add("0");
        }
        for (int i = 0; i < 64; i++) {
            inversedStringList.set(IPEntity.IPInverse[i] - 1, bitString.substring(i, i + 1));
        }

        AtomicReference<String> stringAtomicReference = new AtomicReference<>("");
        inversedStringList.forEach(inversedString -> {
            stringAtomicReference.set(stringAtomicReference + inversedString);
        });
        return stringAtomicReference.get();
    }

    /**
     * 分解为前后两部分
     *
     * @param inversedKey
     */
    private void decomposeString(String inversedKey) {
        this.leftString = inversedKey.substring(0, 32);
        this.rightString = inversedKey.substring(32);
    }

    /**
     * 逆向IP置换
     *
     * @param bitString
     */
    private String inverseIp(String bitString) {
        ArrayList<String> inversedStringList = new ArrayList<String>();
        for (int j = 0; j < 64; j++) {
            inversedStringList.add("0");
        }
        for (int i = 0; i < 64; i++) {
            inversedStringList.set(IPEntity.IP[i] - 1, bitString.substring(i, i + 1));
        }

        AtomicReference<String> stringAtomicReference = new AtomicReference<>("");
        inversedStringList.forEach(inversedString -> {
            stringAtomicReference.set(stringAtomicReference + inversedString);
        });
        return stringAtomicReference.get();
    }

}
