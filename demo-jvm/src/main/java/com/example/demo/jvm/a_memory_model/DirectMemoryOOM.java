package com.example.demo.jvm.a_memory_model;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *  VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 *  直接内存（Direct Memory）的容量大小可通过-XX：MaxDirectMemorySize参数来指定，如果不去指定，则默认与Java堆最大值（由-Xmx指定）一致，
 *  代码清单2-10越过了DirectByteBuffer类直接通过反射获取Unsafe实例进行内存分配（Unsafe类的getUnsafe()方法指定只有引导类加载器才会返回实例，
 *  体现了设计者希望只有虚拟机标准类库里面的类才能使用Unsafe的功能，在JDK 10时才将Unsafe的部分功能通过VarHandle开放给外部使用），
 *  因为虽然使用DirectByteBuffer分配内存也会抛出内存溢出异常，但它抛出异常时并没有真正向操作系统申请分配内存，
 *  而是通过计算得知内存无法分配就会在代码里手动抛出溢出异常，真正申请分配内存的方法是Unsafe::allocateMemory()。
 *
 * @author zhenghao
 * @date 2022/4/24 17:37
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int i = 0;
        try {
            while (true) {
                unsafe.allocateMemory(_1MB);
                ++i;
                Thread.sleep(20);
            }
        } catch (Throwable e) {
            System.out.println(i);
            throw e;
        }
        /*
        结果：
        Exception in thread "main" java.lang.OutOfMemoryError
            at sun.misc.Unsafe.allocateMemory(Native Method)
            at com.example.demo.jvm.a_memory_model.DirectMemoryOOM.main(DirectMemoryOOM.java:25)

         由直接内存导致的内存溢出，一个明显的特征是在Heap Dump文件中不会看见有什么明显的异常情况，
         如果读者发现内存溢出之后产生的Dump文件很小，而程序中又直接或间接使用了DirectMemory（典型的间接使用就是NIO），
         那就可以考虑重点检查一下直接内存方面的原因了
         */
    }

}
