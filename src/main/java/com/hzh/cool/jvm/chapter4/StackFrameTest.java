package com.hzh.cool.jvm.chapter4;

/**
 * @description: 出入栈
 * @Author huangzhenhui
 * @Date 2020/5/28 21:13
 *
 * 方法的结束方式： ① 正常结束，以return为代表
 *                 ② 方法执行中出现未捕获的异常，以抛出异常的方式结束
 *
 *                 // void 的方法反编译后有return。 正常情况我们是省略了
 */
public class StackFrameTest {

    public static void main(String[] args) {
        StackFrameTest frameTest = new StackFrameTest();
        System.out.println("method1准备入栈");
        frameTest.method1();
        System.out.println("method1已经出栈");
        System.out.println("main方法即将执行完成");
    }

    private void method1() {
        System.out.println("method1已经入栈执行中");
        System.out.println("method2准备入栈");
        method2();
        System.out.println("method2已经出栈");
        System.out.println("method1准备出栈");
    }

    private void method2() {
        System.out.println("method2已经入栈执行中");
        System.out.println("method3准备入栈");
        method3();
        System.out.println("method3已经出栈");
        System.out.println("method2准备出栈");
    }

    private void method3() {
        System.out.println("method2已经入栈执行中");
        System.out.println("method3准备出栈");
    }
}
