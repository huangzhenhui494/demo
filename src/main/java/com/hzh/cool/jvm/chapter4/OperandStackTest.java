package com.hzh.cool.jvm.chapter4;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/29 22:28
 */
public class OperandStackTest {


    public void testAddOperation() {
        // byte、short、char、boolean: 都以int型来保存
        byte i = 15;
        int j = 8;
        int k = i + j;
    }

}
