package com.hzh.cool.lambda.stream;

import java.util.Objects;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/31 13:19
 */
public class Trader {

    private String name;

    private String city;

    public Trader(String name, String university) {
        this.name = name;
        this.city = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String university) {
        this.city = university;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", university='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trader)) return false;
        Trader trader = (Trader) o;
        return Objects.equals(name, trader.name) &&
                Objects.equals(city, trader.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}
