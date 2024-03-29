# 贯穿设计模式
## 设计模式原则与分类
### 1.1 要点：
1. 单一职责
2. 接口隔离
3. 依赖倒置
4. 里氏替换
5. 迪米特原则
6. 开闭原则
7. 创建型设计模式
8. 结构设计模式
9. 行为设计模式

### 1.2 设计模式的原则
#### 1. 单一职责
这一职责备受争议，根据划分不同，单一职责划分也有所不同（根据粒度，项目的规模等不同拆分也有区别）。如main方法，工具类，mvc三层架构等都是单一职责划分的体现。

#### 2. 接口隔离
单一职责是接口隔离的基础，单一职责更注重职责划分，而接口隔离更注重接口使用的精确性和最小化。
1.2.1 不要使用没有任何依赖关系的接口，就是如果接口的功能和此类没关系就没必要去实现
1.2.2 一个类对另一个类的依赖性应当是建立在最小的接口上的，一个接口相当于一个角色，不应该当将不同的角色都交给一个接口，没关系的接口不要放一起

#### 3. 依赖倒置
面向接口编程，不应该依赖低层模块、应该依赖他们的抽象（细节依赖抽象）等。

#### 4. 里氏替换
一种对子类和父类的设计原则：
1.4.1 子类需要实现父类中所有的抽象方法（为 替换 做准备）也是多态的体现
1.4.2 子类可以加入自己的特有的方法及属性
1.4.3 子类覆盖父类已实现的方法（非抽象）

#### 5. 迪米特原则中（最少知道原则）
一个类对其他类知道的越少越好，简单说就是只暴露方法，实现细节不暴露

#### 6. 开闭原则
对扩展开放，对修改关闭。提高代码的扩展性。如父类的空方法或抛异常的方式实现，留给子类扩展使用。确定部分的代码不给予修改权限（如使用final修饰）。

### 1.3 设计模式
#### 1. 创建型设计模式
工厂、抽象工厂、单例、建造者、原型
#### 2. 结构设计模式
适配器、桥接、装饰、组合、外观、享元、代理
#### 3. 行为设计模式 
策略、模版方法、观察者、迭代、责任链、命令、备忘录、状态、访问者、中介者、解释器


## 多种类第三方账号登录---桥接模式与适配器模式
适配器模式：帮助不兼容的接口变得兼容，将自己的接口包裹在一个已存在的类中。分类：对象适配器模式（依赖注入） 和 类适配器模式（继承）










