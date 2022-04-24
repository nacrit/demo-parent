package com.example.demo.jvm.a_memory_model;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代码清单2-9 借助CGLib使得方法区出现内存溢出异常
 *   1.7：VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 *   1.8：Vm Args：-XX:MaxMetaspaceSize=10m
 * @author zhenghao
 * @date 2022/4/24 17:28
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
        /*
        在JDK 7中的运行结果：
            Caused by: java.lang.OutOfMemoryError: PermGen space
            at java.lang.ClassLoader.defineClass1(Native Method)
            at java.lang.ClassLoader.defineClassCond(ClassLoader.java:632)
            at java.lang.ClassLoader.defineClass(ClassLoader.java:616)
            ... 8 more

            方法区溢出也是一种常见的内存溢出异常，一个类如果要被垃圾收集器回收，要达成的条件是比较苛刻的。
            在经常运行时生成大量动态类的应用场景里，就应该特别关注这些类的回收状况。这类场景除了之前提到的程序使用了CGLib字节码增强和动态语言外，
            常见的还有：大量JSP或动态产生JSP文件的应用（JSP第一次运行时需要编译为Java类）、基于OSGi的应用（即使是同一个类文件，被不同的加载器加载也会视为不同的类）等。

            在JDK 8以后，永久代便完全退出了历史舞台，元空间作为其替代者登场。在默认设置下，前面列举的那些正常的动态创建新类型的测试用例已经很难再迫使虚拟机产生方法区的溢出异常了。
            不过为了让使用者有预防实际应用里出现类似于代码清单2-9那样的破坏性的操作，HotSpot还是提供了一些参数作为元空间的防御措施，主要包括：
                -XX：MaxMetaspaceSize：设置元空间最大值，默认是-1，即不限制，或者说只受限于本地内存大小。
                -XX：MetaspaceSize：指定元空间的初始空间大小，以字节为单位，达到该值就会触发垃圾收集进行类型卸载，
                    同时收集器会对该值进行调整：如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，
                    那么在不超过-XX：MaxMetaspaceSize（如果设置了的话）的情况下，适当提高该值。
                -XX：MinMetaspaceFreeRatio：作用是在垃圾收集之后控制最小的元空间剩余容量的百分比，
                    可减少因为元空间不足导致的垃圾收集的频率。类似的还有-XX：Max-MetaspaceFreeRatio，用于控制最大的元空间剩余容量的百分比。

             jdk8异常：-XX:MaxMetaspaceSize=10m
             Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
	            at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:558)
         */
    }

    static class OOMObject {
    }

}
