package com.tiger.questionoftheday;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicDataTypeTest {

    @Test
    public void byteTest() {
        assertEquals(1, Byte.BYTES);
        assertEquals(8, Byte.SIZE);

        assertEquals(2, Short.BYTES);
        assertEquals(16, Short.SIZE);

        assertEquals(4, Integer.BYTES);
        assertEquals(32, Integer.SIZE);

        assertEquals(8, Long.BYTES);
        assertEquals(64, Long.SIZE);

        assertEquals(4, Float.BYTES);
        assertEquals(32, Float.SIZE);

        assertEquals(8, Double.BYTES);
        assertEquals(64, Double.SIZE);

        assertEquals(2, Character.BYTES);
        assertEquals(16, Character.SIZE);

        /*
          # java中boolean类型占几个字节
          - 单个的boolean 类型变量在编译的时候是使用的int 类型。 boolean a=true;//这个a在JVM中占4个字节即：32位
          - boolean 类型的数组时，在编译的时候是作为byte array来编译的所以boolean 数组里面的每一个元件占一个字节，
          boolean[] b = new boolean[10];//数组时，每一个boolean在JVM中占一个字节。

          虽然Java虚拟机定义了一个boolean类型，但它只为它提供了非常有限的支持。
          没有Java虚拟机指令专门用于对boolean值的操作。
          相反，Java编程语言中对boolean值进行操作的表达式被编译为使用Java虚拟机int数据类型的值。

          Java虚拟机直接支持boolean数组。它的newarray指令可以创建boolean数组。使用byte数组指令baload和bastore访问和修改类型为boolean的数组。
          Java虚拟机使用1表示boolean数组组件的true，0表示false。其中Java编程语言布尔值由编译器映射到Java虚拟机类型int的值，编译器必须使用相同的编码。

          虚拟机为什么不用byte或short代替boolean而是int,这样不是更节省内存空间？
          因为int对于32位处理器，一次处理的数据是32位，CPU寻址也是32位的查找，具有高效储存的特点
         */

        //不加后缀修饰的浮点数默认是double类型
        assertEquals(0.3,3*0.1);



    }

}