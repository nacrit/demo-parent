# 1.并发编程

## 1.1 进程与线程
###  进程:
> 程序由指令和数据组成，但这些指令要运行，数据要读写，就必须将指令加载至 CPU，数据加载至内存。在
  指令运行过程中还需要用到磁盘、网络等设备。进程就是用来加载指令、管理内存、管理 IO 的
  当一个程序被运行，从磁盘加载这个程序的代码至内存，这时就开启了一个进程。
  进程就可以视为程序的一个实例。大部分程序可以同时运行多个实例进程（例如记事本、画图、浏览器
  等），也有的程序只能启动一个实例进程（例如网易云音乐、360 安全卫士等）

### 线程
>  一个进程之内可以分为一到多个线程。
>  一个线程就是一个指令流，将指令流中的一条条指令以一定的顺序交给 CPU 执行Java 中，线程作为最小调度单位，进程作为资源分配的最小单位。 
>  在 windows 中进程是不活动的，只是作为线程的容器

### 二者对比
  * 进程基本上相互独立的，而线程存在于进程内，是进程的一个子集
  * 进程拥有共享的资源，如内存空间等，供其内部的线程共享
  * 进程间通信较为复杂
    * 同一台计算机的进程通信称为 IPC（Inter-process communication）
    * 不同计算机之间的进程通信，需要通过网络，并遵守共同的协议，例如 HTTP
  * 线程通信相对简单，因为它们共享进程内的内存，一个例子是多个线程可以访问同一个共享变量
  * 线程更轻量，线程上下文切换成本一般上要比进程上下文切换低

## 1.2 并行与并发
* 并发（concurrent）是同一时间应对（dealing with）多件事情的能力。（宏观同时发生）
* 并行（parallel）是同一时间动手做（doing）多件事情的能力。(微观并行执行)

## 1.3 同步与异步
* 同步：需要等待结果返回，才能继续运行
* 异步：不需要等待结果返回，就能继续运行

# 2.Java 线程
## 2.1线程的创建 
* 方法一，直接使用 Thread
* 方法二，使用 Runnable 配合 Thread
* 方法三，FutureTask 配合 Thread
```java
// 详见：com.acrt.example.demo_juc.thread.CreateThreadDemo
/**
 * 1.线程与进程：一个进程之内可以分为一到多个线程，一个线程就是一个指令流，线程上下文切换成本一般上要比进程上下文切换低
 * 2.并发与并行：并发是一个CPU在不同的时间去不同线程中执行指令。并行是多个CPU同时处理不同的线程。
 *
 */
@Slf4j(topic = "c.CreateThreadDemo")
public class CreateThreadDemo {
    public static void main(String[] args) {
        create1().start();
        create2().start();
        create3AndStart();
    }
    /** 通过继承Thread类创建线程 */
    private static Thread create1() {
        // 此处使用匿名内部类，也相当于实现了Thread类
        return new Thread("t1"){
            @Override
            public void run() {
                // do something ...
                log.info("通过继承Thread类创建线程 .. ");
            }
        };
    }
    /** 通过实现Runnable接口创建线程 */
    private static Thread create2() {
        // 此处通过匿名内部类实现Runnable 来作Thread的构造方法的参数来实现线程的创建
        return new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("通过实现Runnable接口创建线程 .. ");
            }
        }, "t2");
    }
    /** 通过FutureTask配合Thread创建线程，此线程可有返回值 */
    private static void create3AndStart() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("通过FutureTask及实现Callable接口来创建线程 .. ");
                return "有返回值的线程";
            }
        });
        new Thread(futureTask, "t3").start();

        try {
            // 此方法会等待线程执行完才执行
            log.info("create3 thread 返回值：{}", futureTask.get());
        } catch (Exception ignored) {}
    }
}
```

### 小结：
* 方法一 是把线程和任务合并在了一起，方法二 是把线程和任务分开了
* 用 Runnable 更容易与线程池等高级 API 配合
* 用 Runnable 让任务类脱离了 Thread 继承体系，更灵活

## 2.2 查看和杀死进程、线程的方法
### windows
```
# 任务管理器可以查看进程和线程数，也可以用来杀死进程
tasklist | findstr xx # 查看进程
taskkill /F /P pid # 杀死进程
```
### linux
```bash
ps -fe # 查看所有进程
ps -fT -p <PID> # 查看某个进程（PID）的所有线程
kill -9 pid # 杀死进程
top 按大写 H #切换是否显示线程
top -H -p <PID> # 查看某个进程（PID）的所有线程
```

### Java
``` 
jps # 命令查看所有 Java 进程
jstack <PID> # 查看某个 Java 进程（PID）的所有线程状态
jconsole # 来查看某个 Java 进程中线程的运行情况（图形界面）
```
#### jconsole 远程监控配置
* 需要以如下方式运行你的 java 类
    * 修改 /etc/hosts 文件将 127.0.0.1 映射至主机名
* 如果要认证访问，还需要做如下步骤
    * 复制 jmxremote.password 文件
    * 修改 jmxremote.password 和 jmxremote.access 文件的权限为 600 即文件所有者可读写
连接时填入 controlRole（用户名），R&D（密码）


## 2.3 线程运行原理
### 1.栈与栈帧
* Java Virtual Machine Stacks （Java 虚拟机栈）
* 每个方法被执行的时候都会同时创建一个栈帧(stack frame)用于存储局部变量表、操作数栈、动态链接、方法出口等信息，是属于线程的私有的。
    * 每个线程启动后，虚拟机就会为其分配一块栈内存。
    * 每个栈由多个栈帧（Frame）组成，对应着每次方法调用时所占用的内存
    * 每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法

### 2.线程上下文切换（Thread Context Switch）
* 因为以下一些原因导致 cpu 不再执行当前的线程，转而执行另一个线程的代码
    * 被动原因
        * 线程的 cpu 时间片用完
        * 垃圾回收
        * 有更高优先级的线程需要运行
    * 主动原因
        * 线程自己调用了 sleep、yield、wait、join、park、synchronized、lock 等方法
        当 Context Switch 发生时，需要由操作系统保存当前线程的状态，并恢复另一个线程的状态，Java 中对应的概念
        就是程序计数器（Program Counter Register），它的作用是记住下一条 jvm 指令的执行地址，是线程私有的
#### 提示：Context Switch 频繁发生会影响性能

### 3.Thread常见方法
* start()   启动一个新线程，在新线程中运行run方法中的代码，start 方法只是让线程进入就绪状态，里面代码不一定立刻运行，只有当 CPU 将时间片分给线程时，才能进入运行状态，执行代码。每个线程的 start 方法只能调用一次，调用多次就会出现 IllegalThreadStateException
	 run()		新线程启动会调用的方法，如果在构造 Thread 对象时传递了 Runnable 参数，则线程启动后会调用 Runnable 中的 run 方法，否则默认不执行任何操作。但可以创建 Thread 的子类对象，来覆盖默认行为
	 join()    等待线程运行结束	
	 join(long n)  等待线程运行结束,最多等待 n 毫秒	
	 getId()		获取线程长整型的 id	id 唯一
	 getName()		获取线程名	
	 setName(String)   修改线程名	
	 getPriority()		获取线程优先级	
	 setPriority(int)	修改线程优先级	java中规定线程优先级是1~10 的整数，较大的优先级能提高该线程被 CPU 调度的机率
	 getState()		获取线程状态	Java 中线程状态是用 6 个 enum 表示，分别为：NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
	 isInterrupted()	判断是否被打断	不会清除 打断标记
	 isAlive()		线程是否存活（还没有运行完毕）	
	 interrupt()	打断线程	如果被打断线程正在 sleep，wait，join 会导致被打断的线程抛出 InterruptedException，并清除 打断标记 ；如果打断的正在运行的线程，则会设置 打断标记，park 的线程被打断，也会设置 打断标记
	 interrupted()	判断当前线程是否被打断	会清除 打断标记
	 currentThread()	获取当前正在执行的线程	
	 sleep(long n)	让当前执行的线程休眠n毫秒，休眠时让出 cpu 的时间片给其它线程	
	 yield()	提示线程调度器让出当前线程对CPU的使用	主要是为了测试和调试

### 4.start与run
* 直接调用 run 是在主线程中执行了 run，没有启动新的线程
* 使用 start 是启动新的线程，通过新的线程间接执行 run 中的代码

### 5.sleep 与 yield
* sleep
1. 调用 sleep 会让当前线程从 Running 进入 Timed Waiting 状态（阻塞）
2. 其它线程可以使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException
3. 睡眠结束后的线程未必会立刻得到执行
4. 建议用 TimeUnit 的 sleep 代替 Thread 的 sleep 来获得更好的可读性
* yield(线程礼让)
1. 调用 yield 会让当前线程从 Running 进入 Runnable 就绪状态，然后调度执行其它线程
2. 具体的实现依赖于操作系统的任务调度器

### 6.线程优先级
* 线程优先级会提示（hint）调度器优先调度该线程，但它仅仅是一个提示，调度器可以忽略它
* 如果 cpu 比较忙，那么优先级高的线程会获得更多的时间片，但 cpu 闲时，优先级几乎没作用

### 7.interrupt 方法详解
* interrupt：打断 sleep，wait，join 的线程，进入阻塞状态
    * 打断 sleep 的线程, 会清空打断状态
    * 打断正常运行的线程, 不会清空打断状态
    * 打断 park 线程, 不会清空打断状态，如果打断标记已经是 true, 则 park 会失效

    例：com.acrt.example.demo_juc.thread.InterruptDemo
### 8.不推荐使用的方法
* 这些方法已过时，容易破坏同步代码块，造成线程死锁
    * stop() 停止线程运行
    * suspend() 挂起（暂停）线程运行
    * resume() 恢复线程运行


###  9.主线程与守护线程
    * 默认情况下，Java 进程需要等待所有线程都运行结束，才会结束。有一种特殊的线程叫做守护线程，只要其它非守
      护线程运行结束了，即使守护线程的代码没有执行完，也会强制结束。
      例：com.acrt.example.demo_juc.thread.DaemonDemo

*  例：
  * 垃圾回收器线程就是一种守护线程 
  * Tomcat 中的 Acceptor 和 Poller 线程都是守护线程，所以 Tomcat 接收到 shutdown 命令后，不会等待它们处理完当前请求 



### 10.线程的状态

#### 10.1 操作系统 ：五种状态 

![系统层面线程5种状态](img\系统层面线程5种状态.png)

- 【初始状态】仅是在语言层面创建了线程对象，还未与操作系统线程关联 
- 【可运行状态】（就绪状态）指该线程已经被创建（与操作系统线程关联），可以由 CPU 调度执行 
- 【运行状态】指获取了 CPU 时间片运行中的状态，当 CPU 时间片用完，会从【运行状态】转换至【可运行状态】，会导致线程的上下文切换 
- 【阻塞状态】 如果调用了阻塞 API，如 BIO 读写文件，这时该线程实际不会用到 CPU，会导致线程上下文切换，进入 【阻塞状态】 等 BIO 操作完毕，会由操作系统唤醒阻塞的线程，转换至【可运行状态】与【可运行状态】的区别是，对【阻塞状态】的线程来说只要它们一直不唤醒，调度器就一直不会考虑调度它们 
- 【终止状态】表示线程已经执行完毕，生命周期已经结束，不会再转换为其它状态 



#### 10.2 Java API 层 六种状态

![javaApi层线程六种状态](img\javaApi层线程六种状态.png)

* NEW 线程刚被创建，但是还没有调用 start() 方法 
* RUNNABLE 当调用了 start() 方法之后，注意，Java API 层面的 RUNNABLE 状态涵盖了 操作系统 层面的【可运行状态】、【运行状态】和【阻塞状态】（由于 BIO 导致的线程阻塞，在 Java 里无法区分，仍然认为是可运行） 
* BLOCKED ， WAITING ， TIMED_WAITING 都是 Java API 层面对【阻塞状态】的细分，后面会在状态转换节详述 
* TERMINATED 当线程代码运行结束 

例：com.acrt.example.demo_juc.thread.StateDemo







