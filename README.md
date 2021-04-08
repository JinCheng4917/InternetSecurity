# InternetSecurity
This is repository of InternetSecurity



## 实验一 [DES加密算法](https://www.cxyxiaowu.com/1478.html)


#### 示例1  密钥长度不正确

> 请输入要加密的字符
> dasojdlaskdjasdskldjasdklasjdasklsjsklasdjk;alsikdp[wioeropqweqw
> 请输入8个字节的密钥：
> djsdksjdd
> 密钥长度不正确，请重新输入： 
> djksdsjd
> **************************      加密完成     *******************************
> 加密之后的字符为： DC,Ð(/ÐBs¡b îu¼J,ïðUÙþrÍÍ¥yXäï=qY¥·hù)iÄ%_3gSåEê©Z
>           
> 是否进行解密?(Y/n)
> 
> 进行解密
> 解密之后的字符为： dasojdlaskdjasdskldjasdklasjdasklsjsklasdjk;alsikdp[wioeropqweqw
> 
> Process finished with exit code 0
> 
#### 示例2  解密时输入不符合要求

> 请输入要加密的字符
> dasjdsahdklasdhjasjkldashdkasjldhskldjdklasdjas
> 请输入8个字节的密钥：
> dshdjhsd
> **************************      加密完成     *******************************
> 加密之后的字符为： 3õu:ÌÓ{æ ¼,>ñ3H©Jã6½x /þuug]ýÙé4õ­<Ï
>           
> 是否进行解密?(Y/n)
> dasd
> 您的输入有误，请重新输入： 
> dd
> 您的输入有误，请重新输入： 
> 
> 进行解密
> 解密之后的字符为： dasjdsahdklasdhjasjkldashdkasjldhskldjdklasdjas
> 
> Process finished with exit code 0
> 

#### 示例3 不进行解密

> 请输入要加密的字符
> dsajdklasdjaskldjasdklasdjas
> 请输入8个字节的密钥：
> dhsjdshd
> **************************      加密完成     *******************************
> 加密之后的字符为： ôLüxiÔÿM1KÆ3ª"=¢«øs-Ð
>           
> 是否进行解密?(Y/n)
> n
> 您没有选择解密
> 
> Process finished with exit code 0
> 

#### 示例4 进行解密（输入回车（默认为Y） / 输入Y）
> 
> 请输入要加密的字符
> djaskldjasasjdaskld
> 请输入8个字节的密钥：
> djsdkddd
> **************************      加密完成     *******************************
> 加密之后的字符为： ñ´µÝÍÇ%R@ûdÞeÓ÷äG|w
>           
> 是否进行解密?(Y/n)
> Y
> 进行解密
> 解密之后的字符为： djaskldjasasjdaskld
> 
> Process finished with exit code 0



## 实验二 [RSA加密](https://www.jianshu.com/p/685cfeffe703)


#### 示例1  解密时输入不符合要求
> 公钥为：aGVidXQ3Njgy
> 私钥为：aUhlYnV0ODcxNA==
> 请输入明文：
> dsahdjkhasjkdh
> **************************      加密完成     *******************************
> 加密之后的字符为： ᝼ࡢᅮ୻᝼គኢ୻ᅮࡢគኢ᝼୻
>           
> 是否进行解密?(Y/n)
> djsahdj
> 您的输入有误，请重新输入： 
> dsd
> 您的输入有误，请重新输入： 
> 
> 进行解密
> 
> 解密之后的字符为： dsahdjkhasjkdh
> 
> Process finished with exit code 0

#### 示例2 不进行解密
> 公钥为：aGVidXQyNTY4
> 私钥为：aUhlYnV0MzQ4MA==
> 请输入明文：
> 「
> **************************      加密完成     *******************************
> 加密之后的字符为： ۀ
>           
> 是否进行解密?(Y/n)
> n
> 您没有选择解密
> 
> Process finished with exit code 0
> 

#### 示例3 进行解密（输入回车（默认为Y） / 输入Y）
> 公钥为：aGVidXQxOTQw
> 私钥为：aUhlYnV0MTk0MA==
> 请输入明文：
> sdjkhjkshadjk
> **************************      加密完成     *******************************
> 加密之后的字符为： ш-ѱۮΙѱۮшΙʲ-ѱۮ
>           
> 是否进行解密?(Y/n)
> 
> 进行解密
> 
> 解密之后的字符为： sdjkhjkshadjk
> 
> Process finished with exit code 0





