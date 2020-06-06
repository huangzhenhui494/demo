package com.hzh.cool.lambda.stream;

import java.util.Objects;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/31 13:19
 */
public class Transaction{

    private Trader trader;

    private Integer year;

    private Integer num;

    public Transaction(Trader trader, Integer year, Integer num) {
        this.trader = trader;
        this.year = year;
        this.num = num;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(trader, that.trader) &&
                Objects.equals(year, that.year) &&
                Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trader, year, num);
    }
}
