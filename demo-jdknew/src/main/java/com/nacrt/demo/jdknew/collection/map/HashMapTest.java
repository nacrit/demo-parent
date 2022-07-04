package com.nacrt.demo.jdknew.collection.map;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * HashMap测试
 *
 * @author zhenghao
 * @date 2022/4/26 15:10
 */
public class HashMapTest<K, V> extends HashMap<K, V> {

    public static void main(String[] args) {
        //// 红黑树的结构还保留了单向链表的特性，是否包含实际用的时遍历链表
        //System.out.println(map.containsValue("aa"));
        // keySet测试，KeySet是一个内部类，其不存储元素，都是对HashMap中元素的映射
        // 如：toString()使用的是父类中的方法，调用了KeySet的iterator()
        //Set<String> keySet = map.keySet();
        //System.out.println(keySet);
        //System.out.println("keySet.remove(\"xx\") = " + keySet.remove("xx"));
        //System.out.println(keySet);
        //System.out.println("map = " + map);

//        System.out.println("ts = " + tableSizeFor(4));
//        Random random = new Random();
//        HashSet<Integer> low = new HashSet<Integer>();
//        HashSet<Integer> hei = new HashSet<Integer>();
//        for (int i = 0; i < 10000; i++) {
//            int i1 = random.nextInt(1000000);
//            if ((i1 & 16) == 0) {
//                low.add(i1);
//            } else {
//                hei.add(i1);
//            }
//        }
//        System.out.println("low = " + low);
//        System.out.println("hei = " + hei);
//        System.out.println("low.size() = " + low.size() + ",hei.size()=" + hei.size());

        // .............................................................
        //// clone测试
        HashMap<Object, Object> map2 = new HashMap<>();
        map2.put("aa", "11");
        map2.put("bb", "22");
        map2.put("cc", "33");
        Object clone = map2.clone();
        map2.put(map2, map2);
        System.out.println(clone);
        System.out.println(map2);

    }

    @Test
    public void resizeTest() {
        // 测试，当链表长度达到8时会转换为红黑树
        HashMap<String, String> map = new HashMap<>(64);
        map.put("xx", "aa");
        System.out.println("map = " + map);
        // 添加的元素都是一个节点上的，>8时会转化为红黑树
        Map<String, String> ss = IntStream.rangeClosed(0, 2000)
                .mapToObj(String::valueOf)
                .filter(s -> (((s.hashCode()) ^ (s.hashCode() >>> 16)) & 63) == 0)
                .collect(Collectors.toMap(Function.identity(), e -> UUID.randomUUID().toString()));
        map.putAll(ss);
        System.out.println("map = " + map);
    }

    /**
     * Field
     */
    @Test
    public void test1() {
        // Map接口的基于哈希表的实现。此实现提供所有可选的映射操作，并允许空值和空键。 （ HashMap类大致相当于Hashtable ，除了它是不同步的并且允许空值。）
        // 这个类不保证映射的顺序；特别是，它不保证订单会随着时间的推移保持不变。

        // 序列化值
        // private static final long serialVersionUID = 362498820763181265L;

        /*
         * The default initial capacity - MUST be a power of two.
         * 默认初始的容量
         */
        // static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

        /*
         * The maximum capacity, used if a higher value is implicitly specified by either of the constructors with arguments.
         * MUST be a power of two <= 1<<30.
         */
        // static final int MAXIMUM_CAPACITY = 1 << 30;

        /*
         * The load factor used when none specified in constructor.
         * 默认的负载因子
         */
        // static final float DEFAULT_LOAD_FACTOR = 0.75f;

        /*
         * The bin count threshold for using a tree rather than list for a
         * bin.  Bins are converted to trees when adding an element to a
         * bin with at least this many nodes. The value must be greater
         * than 2 and should be at least 8 to mesh with assumptions in
         * tree removal about conversion back to plain bins upon
         * shrinkage.
         * 转换为红黑树临界值
         */
        // static final int TREEIFY_THRESHOLD = 8;

        /*
         * The bin count threshold for untreeifying a (split) bin during a
         * resize operation. Should be less than TREEIFY_THRESHOLD, and at
         * most 6 to mesh with shrinkage detection under removal.
         * 元素个数小于等于此值时解除tree结构
         */
        // static final int UNTREEIFY_THRESHOLD = 6;

        /*
         * The smallest table capacity for which bins may be treeified.
         * (Otherwise the table is resized if too many nodes in a bin.)
         * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
         * between resizing and treeification thresholds.
         * 当链表长度大于8时,且数组长度达到64时,转红黑树
         */
        // static final int MIN_TREEIFY_CAPACITY = 64;

        /*
         * The table, initialized on first use, and resized as necessary. When allocated, length is always a power of two.
         * (We also tolerate length zero in some operations to allow bootstrapping mechanics that are currently not needed.)
         */
        // transient HashMap.Node<K,V>[] table;

        /*
         * Holds cached entrySet(). Note that AbstractMap fields are used for keySet() and values().
         */
        // transient Set<Map.Entry<K,V>> entrySet;

        /*
         * The number of key-value mappings contained in this map.
         * k-v的个数
         */
        // transient int size;

        /*
         * The number of times this HashMap has been structurally modified
         * Structural modifications are those that change the number of mappings in
         * the HashMap or otherwise modify its internal structure (e.g.,
         * rehash).  This field is used to make iterators on Collection-views of
         * the HashMap fail-fast.  (See ConcurrentModificationException).
         * // 修改次数
         */
        // transient int modCount;

        /*
         * The next size value at which to resize (capacity * loadFactor).
         * 下次要扩容的阈值
         */
        // (The javadoc description is true upon serialization.
        // Additionally, if the table array has not been allocated, this
        // field holds the initial array capacity, or zero signifying
        // DEFAULT_INITIAL_CAPACITY.)
        // int threshold;

        /*
         * The load factor for the hash table.
         * 负载因子
         */
        // final float loadFactor;
    }

    /**
     * 构造方法
     */
    @Test
    public void test2() {
        /*
        1.hashMap使用懒初始化机制:第一次添加时开始扩容
        2.负载因子如果不指定都是0.75f
        3.若不指定容器大小,第一次初始化默认时16,若指定容器大小ic,第一次初始化大小为2^n(2^n>=ic,n取最小值,靠tableSizeFor方法实现)
         */
        // 指定初始大小及负载因子构造
        // public HashMap(int initialCapacity, float loadFactor)
        // public HashMap(int initialCapacity)
        // public HashMap()
        // public HashMap(Map<? extends K, ? extends V> m)

        // 实现了 Map.putAll 和 Map 构造函数
        // final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict)
    }

    /**
     * 下此扩容大小预估方法
     *
     * @param cap
     * @return
     */
    static int tableSizeFor(int cap) { // 0100
        int n = cap - 1; // 0011
        n |= n >>> 1; // 0011
        n |= n >>> 2; // 0011
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /**
     * 方法
     */
    @Test
    public void test3() {
        // 构造传map和putAll实现方法
        // final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict)
        // public int size()
        // public boolean isEmpty()

        // 调用getNode实现
        // public V get(Object key)

        // 遍历节点（列表或红黑树），找到节点
        // final Node<K,V> getNode(int hash, Object key)
        // public boolean containsKey(Object key)

        // 设置key-value，实际调用putVal
        // public V put(K key, V value)
        // public void putAll(Map<? extends K, ? extends V> m)

        /*
         * Implements Map.put and related methods.
         *
         * @param hash hash for key
         * @param key the key
         * @param value the value to put
         * @param onlyIfAbsent if true, don't change existing value
         * @param evict if false, the table is in creation mode.
         * @return previous value, or null if none
         */
        // final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)

        /* 初始化或成倍扩容size
         * Initializes or doubles table size.  If null, allocates in accord with initial capacity target held in field threshold.
         * Otherwise, because we are using power-of-two expansion, the elements from each bin must either stay at same index,
         * or move with a power of two offset in the new table.
         *
         * @return the table
         */
        // final Node<K,V>[] resize()

        /*
         * Replaces all linked nodes in bin at index for given hash unless table is too small, in which case resizes instead.
         * 链表转红黑树的方法
         */
        // final void treeifyBin(Node<K,V>[] tab, int hash)
        // 移除，返回旧值
        // public V remove(Object key)
        // final Node<K,V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable)

        // 清空元素，table的length不变
        // public void clear()
        // 是否包含某个value，循环遍历所有的节点
        // public boolean containsValue(Object value)

        // 所有key，keySet测试，KeySet是一个内部类，其不存储元素，都是对HashMap中元素的映射
        // 如：remove(Object key)会同步删除HashMap的k-v对
        // 如：toString()使用的是父类中的方法，调用了KeySet的iterator()
        // public Set<K> keySet()
        // 类似KeySet，也是内部类：Values extends AbstractCollection<V>
        // 如：调用clear()时会同步调用HashMap#clear()
        // public Collection<V> values()
        // 类似KeySet，内部类：EntrySet extends AbstractSet<Map.Entry<K,V>>
        // public Set<Map.Entry<K,V>> entrySet()

        // 获取，如果不存在返回指定默认值
        // public V getOrDefault(Object key, V defaultValue)
        // 不存在就设置
        // public V putIfAbsent(K key, V value)
        // public boolean remove(Object key, Object value)
        // 匹配到key-oldV就替换新的v
        // public boolean replace(K key, V oldValue, V newValue)
        // 存在旧值时才替换
        // public V replace(K key, V value)
        // 将每个条目的值替换为对该条目调用给定函数的结果，直到所有条目都已处理或该函数引发异常。函数抛出的异常被转发给调用者。
        // public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)


        // 如果指定的键尚未与值关联（或映射到null ），则尝试使用给定的映射函数计算其值并将其输入到此映射中，除非为null 。
        // public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
        // 如果指定键的值存在且非空，则尝试在给定键及其当前映射值的情况下计算新映射。
        // public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        // 尝试计算指定键及其当前映射值的映射（如果没有当前映射，则为null ）。
        // public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)

        // merge实现类似：map.compute(key, (k, v) -> (v == null) ? msg : v.concat(msg))
        // 如果指定的键尚未与值关联或与 null 关联，则将其与给定的非 null 值关联。否则，将关联的值替换为给定重映射函数的结果，或者如果结果为null则删除。
        // 当为一个键组合多个映射值时，此方法可能很有用。例如，要创建或附加String msg到值映射：map.merge(key, msg, String::concat)
        // public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
        // 对该映射中的每个条目执行给定的操作，直到处理完所有条目或该操作引发异常。除非实现类另有规定，否则按照条目集迭代的顺序执行动作（如果指定了迭代顺序）。动作抛出的异常将转发给调用者。
        // public void forEach(BiConsumer<? super K, ? super V> action)

        // 浅克隆
        // public Object clone()
        // 将HashMap实例的状态保存到流中（即序列化它）
        // private void writeObject(java.io.ObjectOutputStream s)
        // 从流中重构此映射（即反序列化它）。
        // private void readObject(java.io.ObjectInputStream s)
        // 重置为初始默认状态。由 clone 和 readObject 调用。
        // void reinitialize()
    }

    /*
     * table大小不变，table的所有元素置为null
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
//    public void clear() {
//        Node<K,V>[] tab;
//        modCount++;
//        if ((tab = table) != null && size > 0) {
//            size = 0;
//            Arrays.fill(tab, null);
//        }
//    }

    /*
     * put 方法实现
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value，为true时不修改现有的值
     * @param evict if false, the table is in creation mode. false --> 创建模式
     * @return previous value, or null if none
     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
//        Node<K,V>[] tab;
//        Node<K,V> p; // 头节点指针
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0) // 第一次添加初始化操作
//            n = (tab = resize()).length;
//        if ((p = tab[i = (n - 1) & hash]) == null) // 如果没有头节点，直接插入即可
//            tab[i] = newNode(hash, key, value, null);
//        else { // 有头节点，可能添加或修改，节点结构可能是链表或红黑树
//            Node<K,V> e; // 找到的与新增key相同的老元素的节点，存在时修改e的value接口，不存在时添加到节点尾部
//            K k;
//            if (p.hash == hash && // 头节点和要添加的key相同，后面修改value即可，无需扩容
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof TreeNode) // 如果是红黑树，按照红黑树的put逻辑处理
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else { // 链表结构的节点，遍历（可能替换、添加、扩容添加）
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) { // 到尾节点，无相同key，添加新节点，
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st，判断是否要转换红黑树
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash && // 找到节点key值相同，后面修改value即可，无需扩容
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) { // existing mapping for key，说明有相同的key，只需修改value即可
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold) // 容量超过阈值，开始扩容
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }

    /*
     * Implements Map.putAll and Map constructor.
     *
     * @param m the map
     * @param evict false when initially constructing this map, else true (relayed to method afterNodeInsertion).
     */
//    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
//        int s = m.size(); // 比如是10
//        if (s > 0) {
//            // 确保table容量足够
//            if (table == null) { // pre-size,未初始化？
//                // 10 / 0.75 + 1 = 14.333, +1是为了保证容量向下取整后的值在扩容范围内
//                float ft = ((float)s / loadFactor) + 1.0F;
//                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
//                        (int)ft : MAXIMUM_CAPACITY); // 防止容量移除
//                if (t > threshold) // 大于扩容的阈值
//                    threshold = tableSizeFor(t); // 增大下次需要扩容的阈值，16
//            } else if (s > threshold) // 扩容一次
//                resize();
//
//            // 遍历集合，逐个添加到当前集合中
//            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//                K key = e.getKey();
//                V value = e.getValue();
//                putVal(hash(key), key, value, false, evict);
//            }
//        }
//    }

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash to lower.
     * Because the table uses power-of-two masking,
     * sets of hashes that vary only in bits above the current mask will always collide.
     * (Among known examples are sets of Float keys holding consecutive whole numbers in small tables.)
     * So we apply a transform that spreads the impact of higher bits downward.
     * There is a tradeoff between speed, utility, and quality of bit-spreading.
     * Because many common sets of hashes are already reasonably distributed (so don't benefit from spreading),
     * and because we use trees to handle large sets of collisions in bins,
     * we just XOR some shifted bits in the cheapest possible way to reduce systematic lossage,
     * as well as to incorporate impact of the highest bits that would otherwise never be used in index calculations because of table bounds.
     */
    static final int hash(Object key) {
        int h;
        // hashCode ^ hashCode右移16位
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /*
     * Initializes or doubles table size.  If null, allocates in accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the elements from each bin must either stay at same index,
     * or move with a power of two offset in the new table.
     * 初始化或加倍表大小。
     *   如果为空，则按照字段阈值中保存的初始容量目标进行分配。
     *   否则，因为我们使用二次幂展开，每个 bin 中的元素必须保持相同的索引，或者在新表中以二次幂的偏移量移动。
     * @return the table
     */
//    final HashMap.Node<K,V>[] resize() {
//
//        // -------------- 计算新的容器的大小及阈值 --------------------------------
//        // 扩容前的容器大小
//        HashMap.Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold; // 扩容前的阈值
//        // 计算新的容器大小和阈值
//        int newCap, newThr = 0;
//        if (oldCap > 0) { // 存在扩容前的容器
//            if (oldCap >= MAXIMUM_CAPACITY) { // 超过了最大扩容值：1<<30，不在扩容
//                threshold = Integer.MAX_VALUE; // 下此扩容阈值取整数最大值：1<<31-1
//                return oldTab;
//            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && // 新的容器大小扩容两倍
//                    oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold, 阈值扩容为原来的两倍
//        } else if (oldThr > 0) // initial capacity was placed in threshold，第一次扩容，容器大小直接取oldThr
//            newCap = oldThr;
//        else {               // zero initial threshold signifies using defaults，第一次扩容都没有指定，都取默认值16，12
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        if (newThr == 0) { // 阈值可能溢出
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
//                    (int)ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//
//        // ------------------------- 开始扩容，迁移元素 ----------------------------
//        @SuppressWarnings({"rawtypes","unchecked"})
//        HashMap.Node<K,V>[] newTab = (HashMap.Node<K,V>[])new HashMap.Node[newCap]; // 新的table
//        table = newTab;
//        if (oldTab != null) { // 非第一次扩容，需要迁移数据
//            for (int j = 0; j < oldCap; ++j) { // 遍历老的table，迁移数据
//                HashMap.Node<K,V> e;
//                if ((e = oldTab[j]) != null) {  // 非空，说明有数据
//                    oldTab[j] = null; // 将旧的table元素置为null，便于垃圾回收
//                    if (e.next == null) // node只有一个头节点，直接迁移
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof HashMap.TreeNode) // 如果是红黑树，使用迁移红黑树的方法
//                        ((HashMap.TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//                    else { // preserve order，>1的链表结构的节点
//                        HashMap.Node<K,V> loHead = null, loTail = null; // 低位 头和尾部指针（头部用于插入table，尾部用于拼接后续节点）
//                        HashMap.Node<K,V> hiHead = null, hiTail = null; // 高位 头和尾部指针（头部用于插入table，尾部用于拼接后续节点）
//                        HashMap.Node<K,V> next;
//                        do { // 遍历链表节点
//                            next = e.next; // 下一个节点
//                            if ((e.hash & oldCap) == 0) { // 判断高位是否为0，为0放回原容器中，若为1则放到高位出
//                                if (loTail == null) // 尾部为null说明是第一次添加，设置到头节点
//                                    loHead = e;
//                                else // 将节点加入到链尾
//                                    loTail.next = e;
//                                loTail = e; // 尾部指针修改
//                            } else { // 若为1则放到高位出，此时元素放高位
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) { // 低位 将链尾置为null
//                            loTail.next = null;
//                            newTab[j] = loHead; // 低位下标 j
//                        }
//                        if (hiTail != null) { // 高位 将链尾置为null
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead; // 高位下标 j+oldCap
//                        }
//                    }
//                }
//            }
//        }
//        return newTab; // 扩容后的table
//    }


    /*
     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn extends Node)
     * so can be used as extension of either regular or linked node.
     * 继承了LinkedHashMap.Entry<K,V>
     * LinkedHashMap.Entry<K,V> extends HashMap.Node<K,V>
     */
//    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
//        HashMap.TreeNode<K,V> parent;  // red-black tree links
//        HashMap.TreeNode<K,V> left;
//        HashMap.TreeNode<K,V> right;
//        HashMap.TreeNode<K,V> prev;    // needed to unlink next upon deletion
//        boolean red;
//        TreeNode(int hash, K key, V val, Node<K,V> next) {
//            super(hash, key, val, next);
//        }
//
//        /**
//         * Returns root of tree containing this node.
//         */
//        final HashMap.TreeNode<K,V> root() {
//            for (HashMap.TreeNode<K,V> r = this, p;;) {
//                if ((p = r.parent) == null)
//                    return r;
//                r = p;
//            }
//        }
//
//        /**
//         * Ensures that the given root is the first node of its bin.
//         */
//        static <K,V> void moveRootToFront(Node<K,V>[] tab, HashMap.TreeNode<K,V> root) {
//            int n;
//            if (root != null && tab != null && (n = tab.length) > 0) {
//                int index = (n - 1) & root.hash;
//                HashMap.TreeNode<K,V> first = (HashMap.TreeNode<K,V>)tab[index];
//                if (root != first) {
//                    Node<K,V> rn;
//                    tab[index] = root;
//                    HashMap.TreeNode<K,V> rp = root.prev;
//                    if ((rn = root.next) != null)
//                        ((HashMap.TreeNode<K,V>)rn).prev = rp;
//                    if (rp != null)
//                        rp.next = rn;
//                    if (first != null)
//                        first.prev = root;
//                    root.next = first;
//                    root.prev = null;
//                }
//                assert checkInvariants(root);
//            }
//        }
//
//        /**
//         * Finds the node starting at root p with the given hash and key.
//         * The kc argument caches comparableClassFor(key) upon first use
//         * comparing keys.
//         */
//        final HashMap.TreeNode<K,V> find(int h, Object k, Class<?> kc) {
//            HashMap.TreeNode<K,V> p = this;
//            do {
//                int ph, dir; K pk;
//                HashMap.TreeNode<K,V> pl = p.left, pr = p.right, q;
//                if ((ph = p.hash) > h)
//                    p = pl;
//                else if (ph < h)
//                    p = pr;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if (pl == null)
//                    p = pr;
//                else if (pr == null)
//                    p = pl;
//                else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0)
//                    p = (dir < 0) ? pl : pr;
//                else if ((q = pr.find(h, k, kc)) != null)
//                    return q;
//                else
//                    p = pl;
//            } while (p != null);
//            return null;
//        }
//
//        /**
//         * Calls find for root node.
//         */
//        final HashMap.TreeNode<K,V> getTreeNode(int h, Object k) {
//            return ((parent != null) ? root() : this).find(h, k, null);
//        }
//
//        /**
//         * Tie-breaking utility for ordering insertions when equal
//         * hashCodes and non-comparable. We don't require a total
//         * order, just a consistent insertion rule to maintain
//         * equivalence across rebalancings. Tie-breaking further than
//         * necessary simplifies testing a bit.
//         */
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null ||
//                    (d = a.getClass().getName().
//                            compareTo(b.getClass().getName())) == 0)
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            return d;
//        }
//
//        /**
//         * Forms tree of the nodes linked from this node.
//         */
//        final void treeify(Node<K,V>[] tab) {
//            HashMap.TreeNode<K,V> root = null;
//            for (HashMap.TreeNode<K,V> x = this, next; x != null; x = next) {
//                next = (HashMap.TreeNode<K,V>)x.next;
//                x.left = x.right = null;
//                if (root == null) {
//                    x.parent = null;
//                    x.red = false;
//                    root = x;
//                }
//                else {
//                    K k = x.key;
//                    int h = x.hash;
//                    Class<?> kc = null;
//                    for (HashMap.TreeNode<K,V> p = root;;) {
//                        int dir, ph;
//                        K pk = p.key;
//                        if ((ph = p.hash) > h)
//                            dir = -1;
//                        else if (ph < h)
//                            dir = 1;
//                        else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0)
//                            dir = tieBreakOrder(k, pk);
//
//                        HashMap.TreeNode<K,V> xp = p;
//                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                            x.parent = xp;
//                            if (dir <= 0)
//                                xp.left = x;
//                            else
//                                xp.right = x;
//                            root = balanceInsertion(root, x);
//                            break;
//                        }
//                    }
//                }
//            }
//            moveRootToFront(tab, root);
//        }
//
//        /**
//         * Returns a list of non-TreeNodes replacing those linked from
//         * this node.
//         */
//        final Node<K,V> untreeify(HashMap<K,V> map) {
//            Node<K,V> hd = null, tl = null;
//            for (Node<K,V> q = this; q != null; q = q.next) {
//                Node<K,V> p = map.replacementNode(q, null);
//                if (tl == null)
//                    hd = p;
//                else
//                    tl.next = p;
//                tl = p;
//            }
//            return hd;
//        }
//
//        /**
//         * Tree version of putVal.
//         */
//        final HashMap.TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
//                                               int h, K k, V v) {
//            Class<?> kc = null;
//            boolean searched = false;
//            HashMap.TreeNode<K,V> root = (parent != null) ? root() : this;
//            for (HashMap.TreeNode<K,V> p = root;;) {
//                int dir, ph; K pk;
//                if ((ph = p.hash) > h)
//                    dir = -1;
//                else if (ph < h)
//                    dir = 1;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if ((kc == null &&
//                        (kc = comparableClassFor(k)) == null) ||
//                        (dir = compareComparables(kc, k, pk)) == 0) {
//                    if (!searched) {
//                        HashMap.TreeNode<K,V> q, ch;
//                        searched = true;
//                        if (((ch = p.left) != null &&
//                                (q = ch.find(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.find(h, k, kc)) != null))
//                            return q;
//                    }
//                    dir = tieBreakOrder(k, pk);
//                }
//
//                HashMap.TreeNode<K,V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    Node<K,V> xpn = xp.next;
//                    HashMap.TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
//                    if (dir <= 0)
//                        xp.left = x;
//                    else
//                        xp.right = x;
//                    xp.next = x;
//                    x.parent = x.prev = xp;
//                    if (xpn != null)
//                        ((HashMap.TreeNode<K,V>)xpn).prev = x;
//                    moveRootToFront(tab, balanceInsertion(root, x));
//                    return null;
//                }
//            }
//        }
//
//        /**
//         * Removes the given node, that must be present before this call.
//         * This is messier than typical red-black deletion code because we
//         * cannot swap the contents of an interior node with a leaf
//         * successor that is pinned by "next" pointers that are accessible
//         * independently during traversal. So instead we swap the tree
//         * linkages. If the current tree appears to have too few nodes,
//         * the bin is converted back to a plain bin. (The test triggers
//         * somewhere between 2 and 6 nodes, depending on tree structure).
//         */
//        final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
//                                  boolean movable) {
//            int n;
//            if (tab == null || (n = tab.length) == 0)
//                return;
//            int index = (n - 1) & hash;
//            HashMap.TreeNode<K,V> first = (HashMap.TreeNode<K,V>)tab[index], root = first, rl;
//            HashMap.TreeNode<K,V> succ = (HashMap.TreeNode<K,V>)next, pred = prev;
//            if (pred == null)
//                tab[index] = first = succ;
//            else
//                pred.next = succ;
//            if (succ != null)
//                succ.prev = pred;
//            if (first == null)
//                return;
//            if (root.parent != null)
//                root = root.root();
//            if (root == null
//                    || (movable
//                    && (root.right == null
//                    || (rl = root.left) == null
//                    || rl.left == null))) {
//                tab[index] = first.untreeify(map);  // too small
//                return;
//            }
//            HashMap.TreeNode<K,V> p = this, pl = left, pr = right, replacement;
//            if (pl != null && pr != null) {
//                HashMap.TreeNode<K,V> s = pr, sl;
//                while ((sl = s.left) != null) // find successor
//                    s = sl;
//                boolean c = s.red; s.red = p.red; p.red = c; // swap colors
//                HashMap.TreeNode<K,V> sr = s.right;
//                HashMap.TreeNode<K,V> pp = p.parent;
//                if (s == pr) { // p was s's direct parent
//                    p.parent = s;
//                    s.right = p;
//                }
//                else {
//                    HashMap.TreeNode<K,V> sp = s.parent;
//                    if ((p.parent = sp) != null) {
//                        if (s == sp.left)
//                            sp.left = p;
//                        else
//                            sp.right = p;
//                    }
//                    if ((s.right = pr) != null)
//                        pr.parent = s;
//                }
//                p.left = null;
//                if ((p.right = sr) != null)
//                    sr.parent = p;
//                if ((s.left = pl) != null)
//                    pl.parent = s;
//                if ((s.parent = pp) == null)
//                    root = s;
//                else if (p == pp.left)
//                    pp.left = s;
//                else
//                    pp.right = s;
//                if (sr != null)
//                    replacement = sr;
//                else
//                    replacement = p;
//            }
//            else if (pl != null)
//                replacement = pl;
//            else if (pr != null)
//                replacement = pr;
//            else
//                replacement = p;
//            if (replacement != p) {
//                HashMap.TreeNode<K,V> pp = replacement.parent = p.parent;
//                if (pp == null)
//                    root = replacement;
//                else if (p == pp.left)
//                    pp.left = replacement;
//                else
//                    pp.right = replacement;
//                p.left = p.right = p.parent = null;
//            }
//
//            HashMap.TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);
//
//            if (replacement == p) {  // detach
//                HashMap.TreeNode<K,V> pp = p.parent;
//                p.parent = null;
//                if (pp != null) {
//                    if (p == pp.left)
//                        pp.left = null;
//                    else if (p == pp.right)
//                        pp.right = null;
//                }
//            }
//            if (movable)
//                moveRootToFront(tab, r);
//        }
//
//        /**
//         * Splits nodes in a tree bin into lower and upper tree bins,
//         * or untreeifies if now too small. Called only from resize;
//         * see above discussion about split bits and indices.
//         *
//         * @param map the map
//         * @param tab the table for recording bin heads
//         * @param index the index of the table being split
//         * @param bit the bit of hash to split on
//         */
//        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
//            HashMap.TreeNode<K,V> b = this;
//            // Relink into lo and hi lists, preserving order
//            HashMap.TreeNode<K,V> loHead = null, loTail = null;
//            HashMap.TreeNode<K,V> hiHead = null, hiTail = null;
//            int lc = 0, hc = 0;
//            for (HashMap.TreeNode<K,V> e = b, next; e != null; e = next) {
//                next = (HashMap.TreeNode<K,V>)e.next;
//                e.next = null;
//                if ((e.hash & bit) == 0) {
//                    if ((e.prev = loTail) == null)
//                        loHead = e;
//                    else
//                        loTail.next = e;
//                    loTail = e;
//                    ++lc;
//                }
//                else {
//                    if ((e.prev = hiTail) == null)
//                        hiHead = e;
//                    else
//                        hiTail.next = e;
//                    hiTail = e;
//                    ++hc;
//                }
//            }
//
//            if (loHead != null) {
//                if (lc <= UNTREEIFY_THRESHOLD)
//                    tab[index] = loHead.untreeify(map);
//                else {
//                    tab[index] = loHead;
//                    if (hiHead != null) // (else is already treeified)
//                        loHead.treeify(tab);
//                }
//            }
//            if (hiHead != null) {
//                if (hc <= UNTREEIFY_THRESHOLD)
//                    tab[index + bit] = hiHead.untreeify(map);
//                else {
//                    tab[index + bit] = hiHead;
//                    if (loHead != null)
//                        hiHead.treeify(tab);
//                }
//            }
//        }
//
//        /* ------------------------------------------------------------ */
//        // Red-black tree methods, all adapted from CLR
//
//        static <K,V> HashMap.TreeNode<K,V> rotateLeft(HashMap.TreeNode<K,V> root,
//                                                      HashMap.TreeNode<K,V> p) {
//            HashMap.TreeNode<K,V> r, pp, rl;
//            if (p != null && (r = p.right) != null) {
//                if ((rl = p.right = r.left) != null)
//                    rl.parent = p;
//                if ((pp = r.parent = p.parent) == null)
//                    (root = r).red = false;
//                else if (pp.left == p)
//                    pp.left = r;
//                else
//                    pp.right = r;
//                r.left = p;
//                p.parent = r;
//            }
//            return root;
//        }
//
//        static <K,V> HashMap.TreeNode<K,V> rotateRight(HashMap.TreeNode<K,V> root,
//                                                       HashMap.TreeNode<K,V> p) {
//            HashMap.TreeNode<K,V> l, pp, lr;
//            if (p != null && (l = p.left) != null) {
//                if ((lr = p.left = l.right) != null)
//                    lr.parent = p;
//                if ((pp = l.parent = p.parent) == null)
//                    (root = l).red = false;
//                else if (pp.right == p)
//                    pp.right = l;
//                else
//                    pp.left = l;
//                l.right = p;
//                p.parent = l;
//            }
//            return root;
//        }
//
//        static <K,V> HashMap.TreeNode<K,V> balanceInsertion(HashMap.TreeNode<K,V> root,
//                                                            HashMap.TreeNode<K,V> x) {
//            x.red = true;
//            for (HashMap.TreeNode<K,V> xp, xpp, xppl, xppr;;) {
//                if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (!xp.red || (xpp = xp.parent) == null)
//                    return root;
//                if (xp == (xppl = xpp.left)) {
//                    if ((xppr = xpp.right) != null && xppr.red) {
//                        xppr.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.right) {
//                            root = rotateLeft(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateRight(root, xpp);
//                            }
//                        }
//                    }
//                }
//                else {
//                    if (xppl != null && xppl.red) {
//                        xppl.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.left) {
//                            root = rotateRight(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateLeft(root, xpp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        static <K,V> HashMap.TreeNode<K,V> balanceDeletion(HashMap.TreeNode<K,V> root,
//                                                           HashMap.TreeNode<K,V> x) {
//            for (HashMap.TreeNode<K,V> xp, xpl, xpr;;) {
//                if (x == null || x == root)
//                    return root;
//                else if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (x.red) {
//                    x.red = false;
//                    return root;
//                }
//                else if ((xpl = xp.left) == x) {
//                    if ((xpr = xp.right) != null && xpr.red) {
//                        xpr.red = false;
//                        xp.red = true;
//                        root = rotateLeft(root, xp);
//                        xpr = (xp = x.parent) == null ? null : xp.right;
//                    }
//                    if (xpr == null)
//                        x = xp;
//                    else {
//                        HashMap.TreeNode<K,V> sl = xpr.left, sr = xpr.right;
//                        if ((sr == null || !sr.red) &&
//                                (sl == null || !sl.red)) {
//                            xpr.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sr == null || !sr.red) {
//                                if (sl != null)
//                                    sl.red = false;
//                                xpr.red = true;
//                                root = rotateRight(root, xpr);
//                                xpr = (xp = x.parent) == null ?
//                                        null : xp.right;
//                            }
//                            if (xpr != null) {
//                                xpr.red = (xp == null) ? false : xp.red;
//                                if ((sr = xpr.right) != null)
//                                    sr.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateLeft(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//                else { // symmetric
//                    if (xpl != null && xpl.red) {
//                        xpl.red = false;
//                        xp.red = true;
//                        root = rotateRight(root, xp);
//                        xpl = (xp = x.parent) == null ? null : xp.left;
//                    }
//                    if (xpl == null)
//                        x = xp;
//                    else {
//                        HashMap.TreeNode<K,V> sl = xpl.left, sr = xpl.right;
//                        if ((sl == null || !sl.red) &&
//                                (sr == null || !sr.red)) {
//                            xpl.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sl == null || !sl.red) {
//                                if (sr != null)
//                                    sr.red = false;
//                                xpl.red = true;
//                                root = rotateLeft(root, xpl);
//                                xpl = (xp = x.parent) == null ?
//                                        null : xp.left;
//                            }
//                            if (xpl != null) {
//                                xpl.red = (xp == null) ? false : xp.red;
//                                if ((sl = xpl.left) != null)
//                                    sl.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateRight(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//            }
//        }
//
//        /**
//         * Recursive invariant check
//         */
//        static <K,V> boolean checkInvariants(HashMap.TreeNode<K,V> t) {
//            HashMap.TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
//                    tb = t.prev, tn = (HashMap.TreeNode<K,V>)t.next;
//            if (tb != null && tb.next != t)
//                return false;
//            if (tn != null && tn.prev != t)
//                return false;
//            if (tp != null && t != tp.left && t != tp.right)
//                return false;
//            if (tl != null && (tl.parent != t || tl.hash > t.hash))
//                return false;
//            if (tr != null && (tr.parent != t || tr.hash < t.hash))
//                return false;
//            if (t.red && tl != null && tl.red && tr != null && tr.red)
//                return false;
//            if (tl != null && !checkInvariants(tl))
//                return false;
//            if (tr != null && !checkInvariants(tr))
//                return false;
//            return true;
//        }
//    }
}
