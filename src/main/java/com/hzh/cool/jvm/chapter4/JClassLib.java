package com.hzh.cool.jvm.chapter4;

import com.hzh.cool.reflect.reflecttest.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/28 22:12
 */
public class JClassLib {

    int num = 1;
    public JClassLib(){
        this.num = 1 ;
    }

    public static void main(String[] args) {
        JClassLib jClassLib = new JClassLib();
        int num = 10;
        jClassLib.test1();


        List<User> userList = new ArrayList<>();
        userList.stream().filter(o -> (o.getAge() > 35 && o.getUsername() == "")).collect(Collectors.toList()).forEach(o -> System.out.println(o.getAge()));

    }

    private void test1() {

        Date date = new Date();
        double d = 1.12;
        String name = "123";
        test2(123);
        int i = 1;
    }

    private void test2(int i) {
    }

    /**
     * 变量的分类：
     *
     * 	按照数据类型分：① 基本数据类型 ② 引用数据类型
     *
     * 	按照在类中声明的位置来分：
     * 		① 成员变量：在使用前，都经历过默认初始化值
     * 	             	类变量: linking的prepare阶段：给类变量默认赋值 --> initial阶段：给类变量显示赋值即静态代码块赋值
     * 	                实例变量: 随着对象的创建，会在堆空间中分配实例变量空间，并进行赋值
     * 		② 局部变量：在使用前，必须要进行显示赋值的！ 否则编译不通过  int num; system.out.println(num); 使用前。。。
     * 	基本数类型存储的是值，引用数据类型存储的是引用，局部变量没有显式赋值，局部变量表就没有值
     */
}
