package com.hzh.cool.lambda.stream.forkjointest;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/31 14:23
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static long LIMIT = 10000L;

    private long start;

    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long legth = end - start;
        if (legth <= LIMIT) {
            long sum = 0;
            for(long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        } else {
            long middle = (end + start) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle - 1);
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(middle, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
