package com.hzh.cool.jvm.chapter4;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/27 22:01
 */
public class StackTest {

    public void methodA(){
        int i = 10;
        methodB();
    }
    public void methodB(){
        int m = 40;
    }
    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.methodA();
    }
    /**
     * 一个栈帧一个方法
     * 执行后 main方法压入栈底，A方法入栈，B方法入栈
     * B方法执行完出栈，A方法执行完出栈，main方法执行完出栈
     */
}
