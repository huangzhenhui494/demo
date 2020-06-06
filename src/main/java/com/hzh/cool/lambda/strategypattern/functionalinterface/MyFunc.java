package com.hzh.cool.lambda.strategypattern.functionalinterface;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/30 11:11
 */
public interface MyFunc<T, R> {

    public R getValue(T t1, T t2);
}

class Test{

    public static void main(String[] args) {





    }

    public static  Long getSum(Long i, Long j, MyFunc<Long, Long> func){
        return func.getValue(i,j);
    }

    public static <T> Predicate<T> distinctKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> seen = new ConcurrentHashMap<>();


        // 此时的putIfAbsent为<Object, Boolean>， keyExtractor.apply(t)刚好为入参t,返回Object
//        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        return null;
    }

    /**
     * 取反
     */
    public static Object getFalse(Boolean bool, Function<Boolean, Object> function){
        return function.apply(bool);
    }

    /**
     * 分解：
     * list.stream().filter(distinctByKey(b -> b.getName()))
     *               .forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));
     *
     *               ① filter需要传入lambda表达式， 如（b -> b.getName == null） 也就是传入b.getName==null这个Bool值，返回Bool值
     *               即 t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null 中的 -> 后面这一段lambda表达式
     *               所以方法最后要返回一个lambda表达式
     *
     *               ② Function<T, R> 是传入T,R 返回R
     *               distinctByKey(Function<? super T, ?> keyExtractor)  需要传入lambda表达式即 b -> b.getName
     *               b.getName作为apply的入参， 即 b.getName == t
     *               Predicate<T> 是段言型， 即传入含T类型变量的表达式,返回Bool ， 即 t -> 1*2 + t * 3 > 111
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
