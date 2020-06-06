package com.hzh.cool.jvm.chapter4;

/**
 * @description: 程序计数寄存器
 * @Author huangzhenhui
 * @Date 2020/5/27 21:06
 */

// javap -v PCRegisterTest.class 反编译后（直接class文件右键查看命令）
public class PCRegisterTest {

    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;
        String s = "abc";
        System.out.println(i);
        System.out.println(k);

        // 反编译后
        /**
         * main方法的ldc 即常量池中取一个常量  #2 往上找常量池符号Constant Pool
         * #28 = String  即表示为字符串， #2 -> #28
         * #28 = 具体数据abc
         */


    }
}
