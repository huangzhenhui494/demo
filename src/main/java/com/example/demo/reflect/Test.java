package com.example.demo.reflect;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2020/5/26 16:31
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, IOException {

        //0 获取Student对象的字节码
        Class clazz = Class.forName("com.example.demo.reflect.User");

        // ① 构造器
//        constructTest(clazz);

        // ② 获取成员变量并调用
//        memberVariableTest(clazz);

        // ③ 获取成员方法并调用
//        memberMethodTest(clazz);

        // ④ 反射main方法
//        mainMethodTest(clazz);

        // ⑤ 反射和配置文件
//        propertiesAndReflect();

        // ⑥ 反射越过泛型检查
        genericCheck();



    }

    private static void genericCheck() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<?> list = new ArrayList<>();
        Method add = list.getClass().getMethod("add", Object.class);
        add.invoke(list, "1");
        add.invoke(list, 2);
        add.invoke(list, 1.1);

        for (Object o : list) {
            System.out.println(o);
        }
    }

    private static void propertiesAndReflect() throws IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<?> clazz = Class.forName(getValue("className"));
        Method methodName = clazz.getMethod(getValue("methodName"), String.class, int.class);
        Object obj = clazz.getConstructor().newInstance();
        methodName.invoke(obj, "String", 5);
    }

    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();
        FileReader in = new FileReader("src/main/resources/pro.txt");
        pro.load(in);
        in.close();
        return pro.getProperty(key);

    }


    /**
     * main方法
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void mainMethodTest(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // ① 获取main方法
        Method main = clazz.getMethod("main", String[].class);
        // ② 调用main方法
        main.invoke(null, new Object[]{new String[]{"a", "b", "c", "d"}});

    }

    /**
     * 成员方法
     * @param clazz
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void memberMethodTest(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /**
         * 获取成员方法并调用：
         *
         * 1.批量的：
         * 		public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
         * 		public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
         * 2.获取单个的：
         * 		public Method getMethod(String name,Class<?>... parameterTypes):
         * 					参数：
         * 						name : 方法名；
         * 						Class ... : 形参的Class类型对象
         * 		public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
         *
         * 	 调用方法：
         * 		Method --> public Object invoke(Object obj,Object... args):
         * 					参数说明：
         * 					obj : 要调用方法的对象；
         * 					args:调用方式时所传递的实参；
         */

        Object obj;
        // ① 获取单个公有方法
        System.out.println("① 获取单个公有方法");
        Method print1 = clazz.getMethod("print1", String.class, int.class);
        System.out.println(print1);
        obj = clazz.getConstructor().newInstance();
        print1.invoke(obj, "String", 1);

        // ② 获取单个私有方法,并解除私有限定执行
        System.out.println("② 获取单个私有方法,并解除私有限定执行");
        Method print3 = clazz.getDeclaredMethod("print3", int.class);
        System.out.println(print3);
        obj = clazz.getConstructor().newInstance();
        // 暴力反射
        print3.setAccessible(true);
        print3.invoke(obj, 1);

        // ③ 获取所有成员方法
        System.out.println("③ 获取所有公有成员方法");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        // ④ 获取所有成员方法
        System.out.println("④ 获取所有成员方法");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
    }

    /**
     * 成员变量
     * @param clazz
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void memberVariableTest(Class clazz) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /**
         * 获取成员变量并调用：
         *
         * 1.批量的
         *      1).Field[] getFields():获取所有的"公有字段"
         *  	2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
         * 2.获取单个的：
         *  	1).public Field getField(String fieldName):获取某个"公有的"字段；
         *  	2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
         *
         *  设置字段的值：
         *  	Field --> public void set(Object obj,Object value):
         *  		参数说明：
         *  		1.obj:要设置的字段所在的对象；
         *  		2.value:要为字段设置的值；
         *
         */

        // ① 获取某个公有字段
        System.out.println("① 获取某个公有字段");
        Field field = clazz.getField("city");
        System.out.println(field);
        // 获取一个对象并调用
        Object obj = clazz.getConstructor().newInstance();
        field.set(obj, "北京");
        User user = (User) obj;
        System.out.println(user);


        // ① 获取某个私有字段
        System.out.println("① 获取某个私有字段");
        Field declaredField = clazz.getDeclaredField("username");
        System.out.println(declaredField);
        Object obj2 = clazz.getConstructor().newInstance();
        // //暴力反射，解除私有限定
        declaredField.setAccessible(true);
        declaredField.set(obj2, "暴力反射用户名");
        User user1 = (User) obj2;
        System.out.println(user1);


        // ③ 获取所有公有字段
        System.out.println("③ 获取所有公有字段");
        Field[] fields = clazz.getFields();
        for (Field field1 : fields) {
            System.out.println(field1);
        }

        // ④ 获取所有字段
        System.out.println("④ 获取所有字段");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField1 : declaredFields) {
            System.out.println(declaredField1);
        }

    }

    /**
     * 构造器
     * @param clazz
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void constructTest(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /**
         *
         *  通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
         *
         *  1.获取构造方法：
         *  1).批量的方法：
         *  	public Constructor[] getConstructors()：所有"公有的"构造方法
         *      public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
         *
         *  2).获取单个的方法，并调用：
         *  	public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
         *  	public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
         *
         *      调用构造方法：
         *  	Constructor-->newInstance(Object... initargs)
         *
         */

        // ① 获取无参构造方法
        System.out.println("①获取公有无参构造方法");
        Constructor constructor1 = clazz.getConstructor(null);
        System.out.println(constructor1);

        // ② 根据类型获取单个公有构造方法
        System.out.println("②根据参数类型获取公有构造方法");
        Constructor constructor2 = clazz.getConstructor(String.class, String.class);
        System.out.println(constructor2);
        // 使用newInstance执行该构造方法
        Object con1 = constructor2.newInstance("123", "pwd");
        System.out.println(con1);

        // ② 根据类型获取单个私有构造方法
        System.out.println("③根据参数类型获取私有构造方法");
        Constructor constructor3 = clazz.getDeclaredConstructor(String.class, String.class, int.class);
        System.out.println(constructor3);
        // 使用newInstance执行该构造方法,私有的需要设置暴力访问
        constructor3.setAccessible(true);
        Object con2 = constructor3.newInstance("123", "pwd", 1);
        System.out.println(con2);

        // ④ 获取所有公有构造方法
        System.out.println("④获取所有公有构造方法");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constr : constructors) {
            System.out.println(constr);
        }

        // ⑤ 获取所有构造方法,包括私有
        System.out.println("⑤获取所有构造方法");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}
