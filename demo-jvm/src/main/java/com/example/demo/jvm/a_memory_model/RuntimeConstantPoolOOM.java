package com.example.demo.jvm.a_memory_model;

import java.util.HashSet;
import java.util.Set;

/**
 * 代码清单2-7 运行时常量池导致的内存溢出异常
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * 由于运行时常量池是方法区的一部分，所以这两个区域的溢出测试可以放到一起进行。前面曾经提到HotSpot从JDK 7开始逐步“去永久代”的计划，并在JDK 8中完全使用元空间来代替永久代
 * String::intern()是一个本地方法，它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象的引用；
 * 为自JDK 7起，原本存放在永久代的字符串常量池被移至Java堆之中，所以在JDK 7及以上版本，限制方法区的容量对该测试用例来说是毫无意义的。
 * 使用-Xmx参数限制最大堆到6MB就能够看到以下两种运行结果之一，具体取决于哪里的对象分配时产生了溢出：
 * @author zhenghao
 * @date 2022/4/24 17:05
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
//        oomTest();
        internTest();
    }

    private static void oomTest() {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
        /*
        jdk7以前异常：
        Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
            at java.lang.String.intern(Native Method)
            at org.fenixsoft.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java: 18)

        jdk7起的异常
        // OOM异常一：
            Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at java.base/java.lang.Integer.toString(Integer.java:440)
            at java.base/java.lang.String.valueOf(String.java:3058)
            at RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:12)
        // OOM异常二：
            Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at java.base/java.util.HashMap.resize(HashMap.java:699)
            at java.base/java.util.HashMap.putVal(HashMap.java:658)
            at java.base/java.util.HashMap.put(HashMap.java:607)
            at java.base/java.util.HashSet.add(HashSet.java:220)
            at RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java from InputFile-Object:14)
         */
    }

    /**
     * 代码清单2-8 String.intern()返回引用的测试
     * 这段代码在JDK 6中运行，会得到两个false，而在JDK 7中运行，会得到一个true和一个false。
     * 产生差异的原因是，在JDK 6中，intern()方法会把首次遇到的字符串实例复制到永久代的字符串常量池中存储，
     * 返回的也是永久代里面这个字符串实例的引用，而由StringBuilder创建的字符串对象实例在Java堆上，所以必然不可能是同一个引用，结果将返回false。
     * 而JDK 7（以及部分其他虚拟机，例如JRockit）的intern()方法实现就不需要再拷贝字符串的实例到永久代了，
     * 既然字符串常量池已经移到Java堆中，那只需要在常量池里记录一下首次出现的实例引用即可，
     * 因此intern()返回的引用和由StringBuilder创建的那个字符串实例就是同一个。而对str2比较返回false，这是因为“java”
     * [2]这个字符串在执行String-Builder.toString()之前就已经出现过了，字符串常量池中已经有它的引用，
     * 不符合intern()方法要求“首次遇到”的原则，“计算机软件”这个字符串则是首次出现的，因此结果返回true。
     */
    public static void internTest() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
