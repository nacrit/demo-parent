# [深入浅出设计模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns)
### 创建型：创建对象方式
[简单工厂](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/factory/a_simplyfactory)
* 01.**[工厂方法模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/factory/b_factorymethod)**：子类决定创建对象，主要是为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的
* 02.**[抽象工厂模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/factory/c_abstractfactory)**：生产一组相关对象（产品族），创建对象比工厂方法更复杂
* 03.**[单例设计模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/singleton)**（单态模式或者单件模式）：全局唯一实例，保证一个类仅有 一个实例，并提供一个访问它的全局访问点。
* 04.[构建者模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/builder)：分步骤构建复杂对象，将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
* 05.[原型模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/prototype)：通过克隆生成新对象，用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。

### 结构型：对象组合方式
* 06.**[适配器模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/adapter)**：转换接口使不兼容类协作，将一个类的接口转换成客户希望的另外一个接口。Adapter 模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
* 07.**[装饰模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/decorator)**：动态添加功能，避免继承爆炸，动态地给一个对象添加一些额外的职责
* 08.**[代理模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/proxy)**：控制对对象的访问（延迟、权限），为其他对象提供一种代理以控制对这个对象的访问。
* 09.[门面模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/facade)(外观模式)：提供统一入口简化子系统调用，为子系统中的一组接口提供一个一致的界面
* 10.[组合模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/composite)：树形结构处理“部分-整体”关系，将对象以树形结构组织起来，以达成“部分－整体”的层次结构，使得客户端对单个对象和组合对象的使用具有一致性。
* 11.[桥接模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/bright)：分离抽象与实现，支持多维度变化，将抽象部分与它的实现部分分离，使它们都可以独立地变化。
* 12.[享元模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/flyweight)：共享细粒度对象，减少内存占用，采用一个共享类来避免大量拥有相同内容的“小类”的开销。

### 行为型：对象交互方式
* 13.**[观察者](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/observer)**（Observer）模式(发布-订阅（Publish/Subscribe）模式)：一对多依赖，状态变化自动通知，定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
* 14.**[策略模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/strategy)**（Strategy）：封装算法，运行时切换，属于对象行为型设计模式，主要是定义一系列的算法，把这些算法一个个封装成拥有共同接口的单独的类，并且使它们之间可以互换。
* 15.**[模板方法](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/template_method)**（Template Method）模式：父类定义算法骨架，子类实现步骤，定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。
* 16.[状态模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/state)：对象行为随状态改变而改变，允许一个对象在其内部状态改变时改变它的行为。
* 17.**[责任链模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/chain_of_responsibility)**：请求沿链传递，直到被处理，使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。
* 18.[命令模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/command)：将请求封装为对象，支持撤销/重做，将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤消的操作。
* 19.[调停者模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/mediator)（传递器模式、中介者模式）：通过中介对象减少对象间直接耦合，用一个调停对象来封装一系列的对象交互。
* 20.[备忘录](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/memento)（Memento）模式（标记（Token）模式）：保存对象状态，支持回滚，在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后 就可将该对象恢复到原先保存的状态。
* 21.[访问者模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/visitor)：将操作与数据结构分离，便于扩展，顾名思义使用了这个模式后就可以在不修改已有程序结构的前提下，通过添加额外的“访问者”来完成对已有代码功能的提升。表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
* 22.[解释器模式](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/interpreter)：定义语法规则，解释特定语言，定义语言的文法，并且建立一个解释器来解释该语言中的句子。
* 23.[迭代器](src/main/java/com/example/demo/design_pattern/a_head_first_design_patterns/interator)（Iterator）模式（游标（Cursor）模式）：提供统一接口遍历集合元素，GOF 给出的定义为：提供一种方法访问一个容器（container）对象中各个元素，而又不需暴露该对象的内部细节。

- 附件——《话说分派》

# [贯穿设计模式](src/main/java/com/example/demo/design_pattern/b_hbwxz)
## 下载plantuml-integration插件 *.puml

