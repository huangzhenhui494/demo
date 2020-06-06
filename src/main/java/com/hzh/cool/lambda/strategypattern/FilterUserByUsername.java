package com.hzh.cool.lambda.strategypattern;

import com.hzh.cool.reflect.reflecttest.User;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/30 8:53
 */
public class FilterUserByUsername implements MyPredicate<User> {
    @Override
    public Boolean compare(User user) {
        return user.getUsername().equals("afd");
    }
}
