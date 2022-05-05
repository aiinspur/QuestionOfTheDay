package com.tiger.questionoftheday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * @author shihujiang
 * @date 2022/3/22
 */
public class BigIntegerTest {

    @Test
    public void test() {
        // 大数：一、十、百、千、万、亿、兆、京、垓、秭、穣、沟、涧、正、载、极、恒河沙、阿僧祇、那由他、不可思议、无量大数
        // 小数：分、厘、毛、糸、忽、微、纤、沙、尘、埃、渺、漠、模糊、逡巡、须臾、瞬息、弹指、刹那、六德、虚空、清净、阿赖耶、阿摩罗、涅槃寂静

        BigInteger b1 = new BigInteger("999999999999999999999999999998");
        BigInteger b2 = new BigInteger("1");
        Assertions.assertTrue(new BigInteger("999999999999999999999999999999").equals(b1.add(b2)));
        System.out.println(b1.add(BigInteger.valueOf(2)));

        BigInteger b3 = new BigInteger("2");
        System.out.println(b3.pow(2));


        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.floatValue();
        System.out.println(f);


    }
}
