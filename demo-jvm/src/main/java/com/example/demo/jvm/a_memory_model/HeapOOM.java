package com.example.demo.jvm.a_memory_model;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码清单2-3 Java堆内存溢出异常测试
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 限制Java堆的大小为20MB，不可扩展（将堆的最小值-Xms参数与最大值-Xmx参数
 * 设置为一样即可避免堆自动扩展），通过参数-XX：+HeapDumpOnOutOf-MemoryError可以让虚拟机
 * 在出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析。
 *
 * @author zhenghao
 */
public class HeapOOM {
    static class OOMObject {
    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
        /*
        java.lang.OutOfMemoryError: Java heap space
        Dumping heap to java_pid20716.hprof ...
        Heap dump file created [28365519 bytes in 0.082 secs]
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
    }
}

