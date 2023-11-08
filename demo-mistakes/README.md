# demo-error
# Java业务开发常见错误100例 代码示例

GigHub：https://github.com/JosephZhu1983/java-common-mistakes

## 代码篇 (20讲)
	01 | 使用了并发工具类库，线程安全就高枕无忧了吗？
		ConcurrentHashMap 的文档：https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html
	02 | 代码加锁：不要让“锁”事成为烦心事
	03 | 线程池：业务代码最常用也最容易犯错的组件
	04 | 连接池：别让连接池帮了倒忙
		MongoDB官方文档：https://mongodb.github.io/mongo-java-driver/3.12/
	05 | HTTP调用：你考虑到超时、重试、并发了吗？：httpinvoke
		 HTTP 1.1 协议要求：https://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.1.4
		 遵从 HTTP 协议：https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html
	06 | 20%的业务代码的Spring声明式事务，可能都没处理正确：transaction
		Spring 的文档“Using @Transactional with AspectJ：https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#transaction-declarative-aspectj
	07 | 数据库索引：索引不是万能药：sqlindex
	08 | 判等问题：程序里如何确定你就是你？：equals
		@Data 注解：https://projectlombok.org/features/Data
		@Data其中@EqualsAndHashCode 注解：https://projectlombok.org/features/EqualsAndHashCode
		IDE 中安装阿里巴巴的 Java 规约插件：https://github.com/alibaba/p3c
	09 | 数值计算：注意精度、舍入和溢出问题：numeralcalculations
		IEEE 754 标准：https://en.wikipedia.org/wiki/IEEE_754
		这里查看数值转化为二进制的结果：http://www.binaryconvert.com/
		BigDecimal提供了 8 种舍入模式：https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
	10 | 集合类：坑满地的List列表操作：collection
		数组和链表大 O 时间复杂度的显著差异：https://www.bigocheatsheet.com/
	11 | 空值处理：分不清楚的null和恼人的空指针：nullvalue
		阿里开源的 Java 故障诊断神器Arthas：https://alibaba.github.io/arthas/
		Arthas 各种命令的详细使用方法：https://alibaba.github.io/arthas/commands.html
	12 | 异常处理：别让自己在出问题的时候变为瞎子：exception
	13 | 日志：日志记录真没你想象的那么简单：logging
		Logback官方文档：http://logback.qos.ch/manual/layouts.html#conversionWord
	14 | 文件IO：实现高效正确的文件读写并非易事：io
		Oracle 官网的介绍：https://docs.oracle.com/javase/tutorial/essential/io/
		JDK 文档：https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html
		FileChannel的transfreTo：https://developer.ibm.com/articles/j-zerocopy/
	15 | 序列化：一来一回，你还是原来的你吗？：serialization
	16 | 用好Java 8的日期时间类，少踩一些“老三样”的坑：datetime
	17 | 别以为“自动挡”就不可能出现OOM：oom
	18 | 当反射、注解和泛型遇到OOP时，会有哪些坑？：advancedfeatures
	19 | Spring框架：IoC和AOP是扩展的核心：springpart1
	20 | Spring框架：帮我们做了很多工作也带来了复杂度：springpart2

## 不定期加餐 (6讲)
	加餐1 | 带你吃透课程中Java 8的那些重要知识点（一）
		java.util.function包：https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

## 结束语 (2讲)
	Java业务开发常见错误100例
