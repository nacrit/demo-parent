package com.example.demo.mistakes.demo02;//package com.example.demo.mistakes.demo02;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.stream.IntStream;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InterestingTest {
//    @Test
//    public void main() {
//        Interesting interesting = new Interesting();
//        new Thread(interesting::add).start();
//        new Thread(interesting::compare).start();
//    }
//
//    @Test
//    public void dataTest() {
//        int count = 1000000;
//        Data.reset();
//        //多线程循环一定次数调用Data类不同实例的wrong方法
////        IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().wrong());
//        IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().right());
//        System.out.println("结果：" + Data.getCounter());
//    }
//
//    @Resource
//    private ArrayListTest alt;
//
//    @Test
//    public void arrayListTest() {
//        alt.wrong();
//        alt.right();
//    }
//
//    @Resource
//    private ReentrantLockTest reentrantLockTest;
//
//    @Test
//    public void dealLockTest() {
////        ReentrantLockTest.items.entrySet().stream().forEach(System.out::println);
////        System.out.println(reentrantLockTest.wrong());    // 错误示例
//        System.out.println(reentrantLockTest.right());      // 正确示例
//    }
//}