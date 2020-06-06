package com.hzh.cool.lambda.strategypattern;

import java.util.PrimitiveIterator;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/30 8:49
 */
@FunctionalInterface
public interface MyPredicate<T> {

    public Boolean compare(T t);
}
