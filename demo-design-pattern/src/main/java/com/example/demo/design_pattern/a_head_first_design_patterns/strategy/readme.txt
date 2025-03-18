策略模式（Strategy）：
    属于对象行为型设计模式，主要是定义一系列的算法，把这些算法一个个封装成拥有共同接口的单独的类，并且使它们之间可以互换。

    结构：要使算法拥有共同的接口，这样就要实现一个接口或者一个抽象类出来才行。
    这样基本上轮廓也就出来了，我们来看看吧：
    策略模式由三个角色组成：
        1) 算法使用环境(Context)角色：算法被引用到这里和一些其它的与环境有关的操作一起来完成任务。
        2) 抽象策略(Strategy)角色：规定了所有具体策略角色所需的接口。在 java 它通常由接口或者抽象类来实现。
        3) 具体策略(Concrete Strategy)角色：实现了抽象策略角色定义的接口。

        Context --->    Strategy
                            |
                        |       |
                   Strategy1  Strategy2

    举例
        在 Java 语言中对策略模式的应用是很多的，我们这里举个布局管理器的例子。在
        java.awt 类库中有很多种设定好了的 Container 对象的布局格式，这些格式你可以在创建软
        件界面的时候使用到。如果不使用策略模式，那么就没有了对布局格式扩展的可能，因为你
        要去修改 Container 中的方法，去让它知道你这种布局格式，这显然是不可行的。
        让我们来看看 java 源码中的实现吧。先来看看参与的类和他们扮演的角色吧，使用类
        图是再清楚不过的了。

        Container --->  LayoutManager
                               |
                           |       |
              GirdLayoutWindow    FlowLayoutWindow


    这里我只放上了能够用来讲解的最少部分。
    先来看看 Container 中有关的代码：
    LayoutManager layoutMgr; //对布局管理器接口的引用
    //获得在使用的具体布局管理器
    public LayoutManager getLayout() {
     return layoutMgr;
    }
    //设置要使用的具体布局管理器
    public void setLayout(LayoutManager mgr) {
     layoutMgr = mgr;
     if (valid) {
     invalidate();
     }
    }
    可以看到，Container 根本就不关心你使用的是什么具体的布局管理器，这样也就使得
    Container 不会随着布局管理器的增多而修改本身。所以说策略模式是对变化的封装。
    下面是布局管理器接口的代码：
    public interface LayoutManager {
     void addLayoutComponent(String name, Component comp);
     ………
     Dimension minimumLayoutSize(Container parent);
     void layoutContainer(Container parent);
    }
    而具体的布局管理器就是对上面接口方法的实现和扩展，我这里不再展现给大家了，有
    兴趣的可以查看 JDK 源代码。
    来看看对它的使用吧，以下是示意性代码：
    public class FlowLayoutWindow extends JApplet
    {
     public void init()
     {
     Containter cp = getContentPane();
     cp.setLayout(new FlowLayout());
     for(int i = 0 ; i < 20 ; i++)
     cp.add(new JButton("Button" + i));
     }
     ......
    }


    五、使用建议
        下面是使用策略模式的一些建议：
        1) 系统需要能够在几种算法中快速的切换。
        2) 系统中有一些类它们仅行为不同时，可以考虑采用策略模式来进行重构
        3) 系统中存在多重条件选择语句时，可以考虑采用策略模式来重构。
        但是要注意一点，策略模式中不可以同时使用多于一个的算法。


