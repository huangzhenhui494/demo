package com.hzh.cool.jvm.chapter2;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/26 20:52
 */
public class StringClassLoaderTest {

    public static void main(String[] args) {

        // 引导类加载器（C和C++编写，获取不到）和自定义加载器
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取其上层（非继承关系） 扩展类加载器 包含的关系
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); // sun.misc.Launcher$ExtClassLoader@29444d75

        // 试图获取BootStrapClassLoader
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader); // null

        // 用户自定义类的类加载器： 默认使用系统类加载器来加载
        ClassLoader thisClassClassLoader = StringClassLoaderTest.class.getClassLoader();
        System.out.println(thisClassClassLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

        // String类加载器 : 获取不到，引导类加载器加载
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader); // null

    }
}
