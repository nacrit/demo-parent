package com.example.demo.design_pattern.zz_assign;

/**
 * Java 中最常见的特性：重载（overloading）与重置（overriding）。
 * 下面是重载的一个具体的小例子，这是一个再简单不过的代码了：
 */
//Test For OverLoading
public class Test {
    public void doSomething(int i) {
        System.out.println("doString int = " + i);
    }
    public void doSomething(String s) {
        System.out.println("doString String = " + s);
    }
    public void doSomething(int i, String s) {
        System.out.println("doString int = " + i + " String = " + s);
    }


    //这几个方法，唯独的不同便在这参数上
    public void dost(Father f , Father f1){
        System.out.println("ff");
    }
    public void dost(Father f , Son s){
        System.out.println("fs");
    }
    public void dost(Son s , Son s2){
        System.out.println("ss");
    }
    public void dost(Son s , Father f){
        System.out.println("sf");
    }

    public static void main(String[] rags) {
        // 1.
//        Test t = new Test();
//        int i = 0;
//        t.doSomething(i);
//        t.doSomething("test");
//        t.doSomething(i, "hello");

        // 2.
//        Father f = new Father();
//        Father s = new Son();
//        f.dost(1);
//        s.dost(4);
//        s.dost("dispatchTest");
//        //s.dost("test" , 5);
        /*
        在编译期间，编译器根据 f、s 的静态类型来为他们选择了方法，当然都选择了父类 Father的方法。
        而到了运行期，则又根据 s 的实际类型动态的替换了原来选择的父类中的方法。这便是结果产生的原因。
        如果把上面代码中的注释去掉，则会出现编译错误。原因便是在编译期，编译器根据 s的静态类型 Father 找不到带有两个参数的方法 dost。
         */

        // 3
//        Father f = new Father();
//        Father s = new Son();
//        Test t = new Test();
//        t.dost(f , new Father());
//        t.dost(f , s);
//        t.dost(s, f);
        /*
        执行结果没有像预期的那样输出 ff、fs、sf 而是输出了三个 ff。为什么？原因便是在编
        译期，编译器使用 s 的静态类型为其选择方法，于是这三个调用都选择了第一个方法；而在
        运行期，由于 Java 仅仅根据方法所属对象的实际类型来分派方法，因此这个“错误”就没有
        被纠正而一直错了下去……
        可以看出，Java 在静态分派时，可以根据 n（n>0）个参数类型来选择不同的方法，这
        按照上面的定义应该属于多分派的范围。而在运行期时，则只能根据方法所属对象的实际类
        型来进行方法的选择，这又属于单分派的范围。
        因此可以说 Java 语言支持静态多分派和动态单分派。
         */

//        Father f = new Father();
//        Father s = new Son();
//        System.out.println("f.i " + f.i);
//        System.out.println("s.i " + s.i);
//        System.out.println("f.i " + f.getI());
//        System.out.println("s.i " + s.getI());
        /*
        f.i 0
        s.i 0
        f.i 0
        s.i 9
         产生的原因是 Java 编译和运行程序的机制。“数据是什么”是由编译时决定的；而“方法是哪个”则在运行时决定。
         */

        Father f = new Father();
        Father s = new Son();
        f.dosh(f);
        f.dosh(s);
        s.dosh(f);
        s.dosh(s);
        /*
        结果:
        Here is Son's Father
        Here is Son's Son
        Here is Father's Father
        Here is Father's Son
        Java 不能支持动态多分派，但是可以通过代码设计来实现动态的多重分派。这里举一个双重分派的实现例子。
        大致的思想便是通过一个参数来传递 JVM 不能判断的类型。通过 Java 的动态单分派来
        完成一次分派后，在方法中使用 instanceof 来判断参数的类型，进而决定执行哪个相关方法。
         */
    }

}

class Father {
    public void dost(int i){
        System.out.println("Welcome Father! int = "+ i);
    }
    public void dost(String s){
        System.out.println("Welcome Father! String = "+ s);
    }

    int i = 0 ;
    public int getI() {
        return i;
    }

    public void dosh(Father f) {
        if (f instanceof Son) {
            System.out.println("Here is Son's Son");
        } else if (f instanceof Father) {
            System.out.println("Here is Son's Father");
        }
    }
}
class Son extends Father{
    public void dost(int i){
        System.out.println("Welcome Son! int = "+i);
    }
    public void dost(String s ,int i ){
        System.out.println("Welcome Son! String = "+s+" int = "+i);
    }

    int i = 9 ;
    public int getI() {
        return i;
    }

    public void dosh(Father f) {
        if (f instanceof Son) {
            System.out.println("Here is Father's Son");
        } else if (f instanceof Father) {
            System.out.println("Here is Father's Father");
        }
    }
}