package com.hzh.cool.lambda.stream.forkjointest;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/31 14:32
 */
public class TestForkJoin {

    public static void main(String[] args) {

    }

    // forkjoin 333
    @Test
    public void test1() {
        Instant start = Instant.now();
        // forkjoin需要池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 50000000000L);
        Long invoke = forkJoinPool.invoke(task);
        System.out.println(invoke);
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    // 普通for  8538(100亿)
    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0;
        for(long i = 0; i<= 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }

    // stream  java8并行流
    @Test
    public void test3() {
        Instant start = Instant.now();

        long reduce = LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

    }
}
