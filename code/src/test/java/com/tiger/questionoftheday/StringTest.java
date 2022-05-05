package com.tiger.questionoftheday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author shihujiang
 * @date 2022/3/16
 */
public class StringTest {

    @Test
    public void testString() {
        String name = "YanYi";
        Assertions.assertTrue(name == "YanYi");

        String name1 = "YanYi";
        Assertions.assertTrue(name.hashCode() == name1.hashCode());

        //线程安全、可变的字符序列
        StringBuffer buffer = new StringBuffer();

        //可变的字符序列
        StringBuilder builder = new StringBuilder();


    }
}
