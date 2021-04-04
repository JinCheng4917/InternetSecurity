# InternetSecurity
This is repository of InternetSecurity

[DES加密算法](https://www.cxyxiaowu.com/1478.html)


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



