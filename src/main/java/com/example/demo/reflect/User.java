package com.example.demo.reflect;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/26 15:51
 */
public class User {


    private String username;

    protected String password;

    int age;

    public String city;

    User(String username) {
    }

    public User() {
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    protected User(String username, String password, int age, String city) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.city = city;
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " 的main方法执行了");
        for (String s : args) {
            System.out.println(s);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public void print1(String s, int b) {
        System.out.println("公有String参数的print1:" + s + b);
    }

    void print2() {
        System.out.println("默认无参的print2");
    }

    private void print3(int a) {
        System.out.println("私有int参数的print3");
    }

    protected void print4(){
        System.out.println("受保护的无参print4");
    }
}


