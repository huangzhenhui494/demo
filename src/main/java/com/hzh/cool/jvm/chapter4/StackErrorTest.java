package com.hzh.cool.jvm.chapter4;

/**
 * @description:StackOverflowError
 * @Author huangzhenhui
 * @Date 2020/5/27 22:19
 */
public class StackErrorTest {

    private static int i = 1;

    /**
     * 未设置栈时输出11402
     * Run -> Edit Configurations -> 当前程序的VmOPtions: -Xss256k
     * 设置栈为 -Xss256k 后，最大输出为 2468
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(i);
        i++;
        main(args);
        // Exception in thread "main" java.lang.StackOverflowError

    }
}
