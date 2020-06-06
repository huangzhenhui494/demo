package com.hzh.cool.lambda.strategypattern;

import com.hzh.cool.reflect.reflecttest.User;
import com.sun.org.apache.xpath.internal.functions.FuncUnparsedEntityURI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 策略模式，通过年龄或username过滤userList
 * @Author huangzhenhui
 * @Date 2020/5/30 8:53
 */
public class FilterTest {

    public static void main(String[] args) {


        List<User> userList = new ArrayList<>();

        Collections.sort(userList, Comparator.comparingInt(User::getAge));

        // ....add
        // 方式一不简洁
//        List<User> userListByAge = filterUserList(userList, new FilterUserByAge());

        // 优化方式二，匿名内部类
        List<User> users = filterUserList(userList, new MyPredicate<User>() {
            @Override
            public Boolean compare(User user) {
                return user.getAge() > 35;
            }
        });

        // 优化方式三，lambda 增加可读性
        List<User> users2 = filterUserList(userList, user -> user.getAge() > 35);
        users2.forEach(System.out::println);

        // 优化方式4, Stream API
        users2.stream()
                .filter(u -> u.getAge() > 10)
                .limit(2)
                .forEach(System.out::println);

    }

    // 方式一，不简洁，要创建多个接口传入
    public static List<User> filterUserList(List<User> userList, MyPredicate<User> predicate) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (predicate.compare(user)) {
                result.add(user);
            }
        }
        return result;
    }




}
