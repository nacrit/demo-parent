迭代器（Iterator）模式（游标（Cursor）模式）：
    GOF 给出的定义为：提供一种方法访问一个容器（container）对象中各个元素，而又不需暴露该对象的内部细节。

    迭代器模式组成：
        1) 迭代器角色（Iterator）：迭代器角色负责定义访问和遍历元素的接口。
        2) 具体迭代器角色（Concrete Iterator）：具体迭代器角色要实现迭代器接口，并要记录遍历中的当前位置。
        3) 容器角色（Container）：容器角色负责提供创建具体迭代器角色的接口。
        4) 具体容器角色（Concrete Container）：具体容器角色实现创建具体迭代器角色的接口——这个具体迭代器角色于该容器的结构相关。

    三、 举例
        我们先来列举下迭代器模式的实现方式。
            1．迭代器角色定义了遍历的接口，但是没有规定由谁来控制迭代。在 Java collection的应用中，是由客户程序来控制遍历的进程，被称为外部迭代器；
            还有一种实现方式便是由迭代器自身来控制迭代，被称为内部迭代器。外部迭代器要比内部迭代器灵活、强大，而且内部迭代器在 java 语言环境中，可用性很弱。
            2．在迭代器模式中没有规定谁来实现遍历算法。好像理所当然的要在迭代器角色中实现。因为既便于一个容器上使用不同的遍历算法，也便于将一种遍历算法应用于不同的容器。
            但是这样就破坏掉了容器的封装——容器角色就要公开自己的私有属性，在 java 中便意味着向其他类公开了自己的私有属性。
            那我们把它放到容器角色里来实现好了。这样迭代器角色就被架空为仅仅存放一个遍历当前位置的功能。但是遍历算法便和特定的容器紧紧绑在一起了。
            而在 Java Collection 的应用中，提供的具体迭代器角色是定义在容器角色中的内部类。这样便保护了容器的封装。但是同时容器也提供了遍历算法接口，你可以扩展自己的迭代器。

        好了，我们来看下 Java Collection 中的迭代器是怎么实现的吧。
        //迭代器角色，仅仅定义了遍历接口
        public interface Iterator {
             boolean hasNext();
             Object next();
             void remove();
        }
        //容器角色，这里以 List 为例。它也仅仅是一个接口，就不罗列出来了
        //具体容器角色，便是实现了 List 接口的 ArrayList 等类。为了突出重点这里指罗列和
        迭代器相关的内容
        //具体迭代器角色，它是以内部类的形式出来的。AbstractList 是为了将各个具体容器
        角色的公共部分提取出来而存在的。
        public abstract class AbstractList extends AbstractCollection implements List {
             ……
            //这个便是负责创建具体迭代器角色的工厂方法
             public Iterator iterator() {
             return new Itr();
         }

        //作为内部类的具体迭代器角色
        private class Itr implements Iterator {
            int cursor = 0;
            int lastRet = -1;
            int expectedModCount = modCount;
            public boolean hasNext() {
                return cursor != size();
            }
            public Object next() {
                 checkForComodification();
                 try {
                     Object next = get(cursor);
                     lastRet = cursor++;
                     return next;
                 } catch(IndexOutOfBoundsException e) {
                     checkForComodification();
                     throw new NoSuchElementException();
                 }
            }
            public void remove() {
                if (lastRet == -1)
                    throw new IllegalStateException();
                    checkForComodification();
                try {
                    AbstractList.this.remove(lastRet);
                    if (lastRet < cursor)
                        cursor--;
                    lastRet = -1;
                    expectedModCount = modCount;
                } catch(IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }
            final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            }
        }
