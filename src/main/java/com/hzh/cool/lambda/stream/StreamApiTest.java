package com.hzh.cool.lambda.stream;

import com.sun.javafx.scene.control.skin.ColorPalette;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.CompareTo;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/31 10:38
 */
@RunWith(SpringRunner.class)
public class StreamApiTest {

    private static List<Transaction> transactions;

    public static void main(String[] args) {
        System.out.println(1);
    }

     static {
        Trader raoul = new Trader("Raoul", "Cambrideg");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambrideg");
        Trader brian = new Trader("Brian", "Cambrideg");

         transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
    }

    // 1 找出2011年发生的所有交易，并按交易额排序，从高到低
    @Test
    public void test1() {
        List<Transaction> collect = transactions.stream()
                .filter(t -> 2011 == t.getYear())
                .sorted((t1, t2) -> t2.getNum() - t1.getNum())
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    // 2.交易员都在哪些不同的城市工作过？
    @Test
    public void test2() {
        List<String> citys = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        citys.forEach(System.out::println);
    }

    // 3.查找所有来自剑桥的交易员的交易信息，并按姓名排序, 按名字去重
    @Test
    public void test3() {
        transactions.stream()
                .filter(t -> "Cambrideg".equals(t.getTrader().getCity()))
                .filter(distinctByKey(t -> t.getTrader().getName()))
                .sorted(Comparator.comparing(t -> t.getTrader().getName()))
                .forEach(System.out::println);
    }

    // 4.返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4(){
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        
        // 一个字符串
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .collect(Collectors.joining("-")));

        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat));

        // 所有字母排序
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(t -> filterCharacter(t))
                .sorted()
                .forEach(System.out::print);
    }

    // 5.有没有交易员在米兰工作
    @Test
    public void test5() {
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("milan"));
        System.out.println(milan);
    }

    // 6.打印生活在剑桥的交易员所有交易额
    @Test
    public void test6(){
        System.out.println(
                transactions.stream()
                        .filter(t -> "Cambrideg".equalsIgnoreCase(t.getTrader().getCity()))
                        .mapToInt(t -> t.getNum()).sum()
        );
        System.out.println(
                transactions.stream()
                        .filter(t -> "Cambrideg".equalsIgnoreCase(t.getTrader().getCity()))
                        .map(Transaction::getNum)
                        .reduce(0, Integer::sum) // 即 .reduce(0, (t1, t2) -> t1 + t2)
        );
    }

    // 7.所有交易中，最高交易额是多少
    @Test
    public void test7() {
        System.out.println(
//                transactions.stream()
//                .max((o1, o2) -> o1.getNum() - o2.getNum())
                transactions.stream()
                .map(Transaction::getNum)
                .max(Comparator.comparingInt(t -> t))
        );
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }


    /**
     * 对某个字段去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}


