附件——《话说分派》

一、引言
这篇文章，完全是为了更好的讲解访问者(Visitor)模式而写的。让我们进入这扑朔迷离的分派世界吧（是不是有点夸张了，汗）。

二、名词解释
先来解释下分派的意思吧。。
在 OO（object-oriented）语言中使用了继承来描述不同的类之间的“社会关系”——类型层次。
而这些类实例化的对象们则是对这个类型层次的体现。因此大部分 OO 语言的对象都存在两个身份证：静态类型和实际类型。
所谓静态类型，就是对象被声明时的类型；而实际类型则便是创建对象时的类型。举个例子：
B 是 A 的子类。则A object1 = new B ( );中 object1 的静态类型便是 A，而实际类型却是 B。
在 Java 语言中，编译器会根据对象的静态类型来检查错误；而在运行时，则使用对象的真实身份。
OO 还有一个重要的特点：一个类中可以存在两个相同名称的方法，而它们是根据参数类型的不同来区分的。
正因以上两个原因，便产生了分派——根据类型的不同来选择不同的方法的过程——OO语言的重要特性。
三、分类
分派可以发生在编译期或者是运行期。因此按此标准，分派分为静态分派和动态分派。
在程序的编译期，只有对象的静态类型是有效的，因此静态分派就是根据对象（包括参数对象）的静态类型来选择方法的。最典型的便是方法重载（overloading）。
在运行期，动态分派会根据对象的实际类型来选择方法。典型的例子便是方法重置（overriding）
而 OO 语言正是由以上两种分派方式来提供多态特性的。
按照选择方法时所参照的类型的个数，分派分为单分派和多分派。
OO 语言也因此分为了单分派（Uni-dispatch）语言和多分派（Multi-dispatch）语言。比如 Smalltalk 就是单分派语言，而 CLOS 和 Cecil 就是多分派语言。
说道多分派，就不得提到另一个概念：多重分派（multiple dispatch）。它指由多个单分派组成的分派过程（而多分派则往往不能分割的）。
因此单分派语言可以通过多重分派的方式来实现和多分派语言一样的效果。
那么我们熟悉的 Java 语言属于哪一种分派呢？
四、Java 分派实践
先来看看在 Java 中最常见的特性：重载（overloading）与重写（覆写）（overriding）。
下面是重载的一个具体的小例子，这是一个再简单不过的代码了：
//Test For OverLoading
public class Test{
 public void doSomething(int i){
 System.out.println("doString int = "+ i );
 }
 public void doSomething(String s){
 System.out.println("doString String = "+ s);
 }
 public void doSomething(int i , String s){
 System.out.println("doString int = "+ i +" String = "+ s);
 }
 public static void main(String[] rags){
 Test t = new Test();
 int i = 0;
 t.doSomething(i);
 }
}
没什么好稀奇的，你对这部分知识已经熟练掌握了，那么你对下面这段代码的用意也一定了如指掌了吧。
//Test For Overriding
public class Test{
 public static void main(String[] rags){
 Father f = new Father();
 Father s = new Son();
 f.dost();
 s.dost();
 }
}
class Father {
 public void dost(){
 System.out.println("Welcome Father!");
 }
}
class Son extends Father{
 public void dost(){
 System.out.println("Welcome Son!");
 }
}
那么下面这个代码呢？
public class Test{
 public static void main(String[] rags){
 Father f = new Father();
 Father s = new Son();

 f.dost(1);
 s.dost(4);
 s.dost("dispatchTest");
 //s.dost("test" , 5);
 }
}
class Father {

 public void dost(int i){
 System.out.println("Welcome Father! int = "+ i);
 }
 public void dost(String s){
 System.out.println("Welcome Father! String = "+ s);
 }
}
class Son extends Father{

 public void dost(int i){
 System.out.println("Welcome Son! int = "+i);
 }
 public void dost(String s ,int i ){
 System.out.println("Welcome Son! String = "+s+" int = "+i);
 }
}
在编译期间，编译器根据 f、s 的静态类型来为他们选择了方法，当然都选择了父类 Father的方法。
而到了运行期，则又根据 s 的实际类型动态的替换了原来选择的父类中的方法。这便是结果产生的原因。
如果把上面代码中的注释去掉，则会出现编译错误。原因便是在编译期，编译器根据 s的静态类型 Father 找不到带有两个参数的方法 dost。
再来一个，可要注意看了：
public class Test{
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
 public static void main(String[] rags){
 Father f = new Father();
 Father s = new Son();
 Test t = new Test();
 t.dost(f , new Father());
 t.dost(f , s);
 t.dost(s, f);
 }
}
class Father {}
class Son extends Father{}
执行结果没有像预期的那样输出 ff、fs、sf 而是输出了三个 ff。为什么？原因便是在编译期，编译器使用 s 的静态类型为其选择方法，于是这三个调用都选择了第一个方法；
而在运行期，由于 Java 仅仅根据方法所属对象的实际类型来分派方法，因此这个“错误”就没有被纠正而一直错了下去……
可以看出，Java 在静态分派时，可以根据 n（n>0）个参数类型来选择不同的方法，这按照上面的定义应该属于多分派的范围。
而在运行期时，则只能根据方法所属对象的实际类型来进行方法的选择，这又属于单分派的范围。
因此可以说 Java 语言支持静态多分派和动态单分派。
五、小插曲
你看看下面的代码会怎么执行呢？
public class Test{
 public static void main(String[] rags){
 Father f = new Father();
 Father s = new Son();

 System.out.println("f.i " + f.i);
 System.out.println("s.i " + s.i);
 f.dost();
 s.dost();
 }
}
class Father {
 int i = 0 ;
 public void dost(){
 System.out.println("Welcome Father!");
 }
}
class Son extends Father{
 int i = 9 ;
 public void dost(){
 System.out.println("Welcome Son!");
 }
}
运行结果：
\>java Test
f.i 0
s.i 0
Welcome Father!
Welcome Son!
产生的原因是 Java 编译和运行程序的机制。“数据是什么”是由编译时决定的；而“方法是哪个”则在运行时决定。
六、双重分派
Java 不能支持动态多分派，但是可以通过代码设计来实现动态的多重分派。这里举一个双重分派的实现例子。
大致的思想便是通过一个参数来传递 JVM 不能判断的类型。
通过 Java 的动态单分派来完成一次分派后，在方法中使用 instanceof 来判断参数的类型，进而决定执行哪个相关方法。
 public class Test{
 public static void main(String[] rags){
 Father f = new Father();
 Father s = new Son();
 s.dosh(f);
 s.dosh(s);
 f.dosh(s);
 f.dosh(f);
 }
}
class Father {
 public void dosh(Father f){
 if(f instanceof Son){
 System.out.println("Here is Father's Son");
 }else if(f instanceof Father){
 System.out.println("Here is Father's Father");
 }
 }
}
class Son extends Father{
 public void dosh(Father f){
 if(f instanceof Son){
 System.out.println("Here is Son's Son");
 }else if(f instanceof Father){
 System.out.println("Here is Son's Father");
 }
 }
}
执行结果：
Here is Son's Father
Here is Son's Son
Here is Father's Son
Here is Father's Father
呵呵，慢慢在代码中琢磨吧。用这种方式来实现双重分派，思路比较简单清晰。但是对
于复杂一点的程序，则代码显得冗长，不易读懂。而且添加新的类型比较麻烦，不是一种好
的设计方案。访问者（Visitor）模式则较好的解决了这种模式的不足。至于访问者模式的实
现……
请关注我的《深入浅出访问者模式》。




