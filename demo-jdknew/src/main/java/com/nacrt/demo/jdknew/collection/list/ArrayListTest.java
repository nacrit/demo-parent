package com.nacrt.demo.jdknew.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ArrayListTest
 *
 * @author zhenghao
 * @date 2022/4/26 10:29
 */
public class ArrayListTest {
    public static void main(String[] args) {
        // 测试RandomAccess
//        test1();

        // 字段使用
//        test2();

        // 构造方法
//        test3();

        // 成员方法
//        test4();

        // 流程梳理
        test5();

    }

    /**
     * 实现RandomAccess的集合，for循环比迭代器输出快很多
     */
    public static void test1() {
        // AbstractList:此类提供List接口的骨架实现，以最大限度地减少实现此接口所需的工作，该接口由“随机访问”数据存储（例如数组）支持。
        // List:有序集合（也称为序列）。此界面的用户可以精确控制每个元素在列表中的插入位置。用户可以通过整数索引（列表中的位置）访问元素，并在列表中搜索元素。
        // RandomAccess:List实现使用的标记接口表明它们支持快速（通常是恒定时间）随机访问。此接口的主要目的是允许通用算法在应用于随机或顺序访问列表时改变其行为以提供良好的性能。
        // Cloneable:一个类实现了Cloneable接口，以向Object.clone()方法指示该方法可以合法地对该类的实例进行逐个字段的复制。
        // java.io.Serializable:类的可序列化性由实现 java.io.Serializable 接口的类启用。未实现此接口的类将不会对其任何状态进行序列化或反序列化。
        // 可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，仅用于识别可序列化的语义。
        // public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable

        List<Integer> list = IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toList());
//        int sumAvg = 0;
//        for (int k = 0; k < 10; k++) {
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < list.size(); i++) {
                ++i;
            }
        }
        long end1 = System.currentTimeMillis();
        long time1 = end1 - start;
        System.out.println("user time1 = " + time1);
        for (int j = 0; j < 100; j++) {
            for (Integer i : list) {
                ++i;
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("user time2 = " + (end2 - end1));
        long avg = (end2 - end1) / (time1 < 1 ? 1 : time1);
//        sumAvg += avg;
        System.out.println("avgRate = " + avg);
        System.out.println("-------------------------------------");
//        }
//        System.out.println("sumAvg = " + sumAvg);
    }

    /**
     * ArrayList字段意义
     */
    private static void test2() {
        // 序列化值
        // private static final long serialVersionUID = 8683452581122892189L;

        /*
         * Default initial capacity.
         * 默认初始化容量为10
         */
        // private static final int DEFAULT_CAPACITY = 10;

        /*
         * Shared empty array instance used for empty instances.
         * 用于空实例的共享空数组实例
         * 1.构造器指定容器大小为0时
         * 2.构造器传入空的集合
         * 3.trimToSize()：修剪elementData，elementDate无数据时
         * 4.readObject()：反序列化时，先将数组置为空
         */
        // private static final Object[] EMPTY_ELEMENTDATA = {};

        /*
         * Shared empty array instance used for default sized empty instances.
         * We distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when first element is added.
         * 用于默认大小的空实例的共享空数组实例。
         * 我们将其与 EMPTY_ELEMENTDATA 区分开来，以了解添加第一个元素时要膨胀多少。
         * 1.空构造时默认elementData的值
         * 2.如果elementData是此值，默认扩容大小为 DEFAULT_CAPACITY
         */
        // private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

        /*
         * The array buffer into which the elements of the ArrayList are stored.
         * The capacity of the ArrayList is the length of this array buffer.
         * Any empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA will be expanded to DEFAULT_CAPACITY when the first element is added.
         * transient修饰的字段不会被序列化
         */
        // transient Object[] elementData; // non-private to simplify nested class access(非私有以简化嵌套类访问)

        /*
         * The size of the ArrayList (the number of elements it contains).
         * ArrayList 的大小（它包含的元素数量）。
         * 实际元素的数量
         */
        // private int size;

        /*
         * The maximum size of array to allocate.
         * Some VMs reserve some header words in an array.
         * Attempts to allocate larger arrays may result in
         * OutOfMemoryError: Requested array size exceeds VM limit
         * 要分配的数组的最大大小。一些 VM 在数组中保留一些标题字。尝试分配更大的数组可能会导致 OutOfMemoryError：请求的数组大小超过 VM 限制
         * 数组最大容量，如果扩容的大小大于此值就取Integer.MAX_VALUE（2^31-1）
         */
        // private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    }

    /**
     * 构造方法
     */
    private static void test3() {
        // 默认为 DEFAULTCAPACITY_EMPTY_ELEMENTDATA
        // public ArrayList()
        // 指定容器大小 size>0会初始化elementData=new Object[initialCapacity]，size==0初始化为EMPTY_ELEMENTDATA
        // public ArrayList(int initialCapacity)
        // 传入集合，传入集合size==0，elementData=EMPTY_ELEMENTDATA;如果集合是ArrayList直接将数组赋值给elementData，否则Arrays.copyOf(a, size, Object[].class)
        // public ArrayList(Collection<? extends E> c)
    }

    /**
     * 方法解析
     */
    private static void test4() {
        // 复制数组,将original数组拷贝newLength长度(可能会截取)到T[]类型的数组中
        // 参形：original–要复制的数组, newLength–要返回的副本的长度, newType–要返回的副本的类
        // java.util.Arrays.copyOf(U[] original, int newLength, Class<? extends T[]> newType)
        // public static native void arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length)

        // 修建elementData将length缩减到size
        // public void trimToSize()

        // 如有必要，增加此ArrayList实例的容量，以确保它至少可以容纳最小容量参数指定的元素数量。
        // public void ensureCapacity(int minCapacity)

        // 计算最小扩容大小
        // private static int calculateCapacity(Object[] elementData, int minCapacity)
        // 确保内部容量足够,实际调用ensureExplicitCapacity
        // private void ensureCapacityInternal(int minCapacity)
        // 确保容量足够,会增加确认数,容量不足会扩容(minCapacity>e.length时)
        // private void ensureExplicitCapacity(int minCapacity)
        // 扩容方法,扩容后大小:newCapacity = max(oldCapacity + (oldCapacity >> 1), minCapacity);
        // private void grow(int minCapacity)
        // 容量过大处理
        // private static int hugeCapacity(int minCapacity)

        // 容器元素数量
        // public int size()

        // 是否为空
        // public boolean isEmpty()

        // 是否包含某元素
        // public boolean contains(Object o)
        // 返回某元素正序第一次出现的下标
        // public int indexOf(Object o)
        // public int lastIndexOf(Object o)

        // 克隆集合,浅拷贝（只拷贝引用,元素本身不会被复制）,克隆出的集合修改次数重置
        // public Object clone()

        // 集合转数组,实际调用Arrays.copyOf
        // public Object[] toArray()
        // 将集合转成T[]类型的数组
        // public <T> T[] toArray(T[] a)

        // 检查要查找的下标是否合法
        // private void rangeCheck(int index)
        // 检查要添加的下标是否合法
        // private void rangeCheckForAdd(int index)

        // 根据下标查元素
        // E elementData(int index)
        // public E get(int index)
        // 修改元素,返回老的元素
        // public E set(int index, E element)
        String t1 = new ArrayList<String>().set(-1, "a");// java.lang.ArrayIndexOutOfBoundsException: -1
        String t2 = new ArrayList<String>().set(0, "a");// java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        // 添加一个元素
        // public boolean add(E e)
        // 指定下标添加一个元素,下标及之后的元素后移
        // public void add(int index, E element)
        // 返回旧数据
        // public E remove(int index)
        // public boolean remove(Object o)
        // 更具下标删除元素
        // private void fastRemove(int index)
        // 将数组中元素置为null,数组大小不变,size=0,modCount+1
        // public void clear()
        // 批量添加,System.arraycopy
        // public boolean addAll(Collection<? extends E> c)
        // 指定位置批量添加
        // public boolean addAll(int index, Collection<? extends E> c)
        // 移除指定下标范围的元素[fromIndex,toIndex)
        // protected void removeRange(int fromIndex, int toIndex)
        // 批量删除
        // public boolean removeAll(Collection<?> c)
        // 实际批量删除操作,使用双向指针,对数组元素左移,后续元素置为null
        // private boolean batchRemove(Collection<?> c, boolean complement)
        // this = 交集(this, c)
        // public boolean retainAll(Collection<?> c)

        // 序列化
        // private void writeObject(java.io.ObjectOutputStream s)
        // 反序列化
        // private void readObject(java.io.ObjectInputStream s)

        // 迭代器
        // public ListIterator<E> listIterator(int index)
        // public ListIterator<E> listIterator() --> listIterator(0)

        // 截取集合
        // public List<E> subList(int fromIndex, int toIndex) -> new SubList(this, 0, fromIndex, toIndex)
        // private class SubList extends AbstractList<E> implements RandomAccess
        // static void subListRangeCheck(int fromIndex, int toIndex, int size)

        // 遍历集合,底层使用的是for循环
        // public void forEach(Consumer<? super E> action)

        // 在此列表中的元素上创建一个后期绑定和快速失败的Spliterator
        // public Spliterator<E> spliterator()
        // 基于索引的二分法，延迟初始化的 Spliterator
        // static final class ArrayListSpliterator<E> implements Spliterator<E>
        // 移除元素
        // public boolean removeIf(Predicate<? super E> filter)
        // 替换
        // public void replaceAll(UnaryOperator<E> operator)
        // 排序
        // public void sort(Comparator<? super E> c)
    }

    /**
     * 流程梳理
     */
    private static void test5() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1"); // 第一次添加,默认大小为10
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11"); // 开始扩容, 10+10/2=15
        System.out.println(list);
        list.addAll(Arrays.asList("12", "13", "14", "15", "16")); // 15+15/2=22
        System.out.println(list);
//        System.out.println(list.retainAll(Arrays.asList("1", "10", "15")));
//        System.out.println(list);
    }

}
