

[TOC]



# Java基本数据类型

8种基本数据类型。

```java
// byte 1字节 8位
assertEquals(1, Byte.BYTES);
assertEquals(8, Byte.SIZE);
// short 2字节 16位
assertEquals(2, Short.BYTES);
assertEquals(16, Short.SIZE);
// int 4字节 32位
assertEquals(4, Integer.BYTES);
assertEquals(32, Integer.SIZE);
// long 8字节 64位
assertEquals(8, Long.BYTES);
assertEquals(64, Long.SIZE);

//float  4字节 32位
assertEquals(4,Float.BYTES);
assertEquals(32,Float.SIZE);
//double 8字节 64位
assertEquals(8, Double.BYTES);
assertEquals(64, Double.SIZE);

//char 2字节 16位
assertEquals(2, Character.BYTES);
assertEquals(16, Character.SIZE);


```

# java中boolean类型占几个字节

- 单个的boolean 类型变量在编译的时候是使用的int 类型。 boolean a=true;//这个a在JVM中占4个字节即：32位
- boolean 类型的数组时，在编译的时候是作为byte array来编译的所以boolean 数组里面的每一个元件占一个字节， boolean[] b = new boolean[10];//数组时，每一个boolean在JVM中占一个字节。 

虽然Java虚拟机定义了一个boolean类型，但它只为它提供了非常有限的支持。没有Java虚拟机指令专门用于对boolean值         的操作。相反，Java编程语言中对boolean值进行操作的表达式被编译为使用Java虚拟机int数据类型的值。Java虚拟机直接支持boolean数组。它的newarray指令可以创建boolean数组。使用byte数组指令baload和bastore访问和修改类型为boolean的数组。Java虚拟机使用1表示boolean数组组件的true，0表示false。其中Java编程语言布尔值由编译器映射到Java虚拟机类型int的值，编译器必须使用相同的编码。

**虚拟机为什么不用byte或short代替boolean而是int,这样不是更节省内存空间？**因为int对于32位处理器，一次处理的数据是32位，CPU寻址也是32位的查找，具有高效储存的特点



# short s1 = 1; s1 = s1 + 1;

short s1 = 1; s1 += 1;有错吗？

- s1 = s1+1 中的1默认类型是int，表达式中低范围类型s1会默认转为int来相加，得到int型的结果，最后int型的结果不能隐式转为short，编译报错
- s1 += 1; 存在隐含的强制转化 s1 += 1 -> s1 = (short) s1+ 1; 编译不会报错

# float f = 3.4; 是否正确

在java里，不加后缀修饰的浮点数默认是double类型。double类型不能隐式类型转成float,编译会报错。

# 表达式3*0.1 == 0.3 吗？

浮点型存在精度问题，3*0.1得到的double数据尾数位 和 0.3 尾数位是不一样的 ，false

```java
assertEquals(0.3,3*0.1);
```

org.opentest4j.AssertionFailedError: 
Expected :0.3
Actual   :0.30000000000000004



# BigInteger原理

