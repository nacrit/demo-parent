## 1. 关于maven依赖的优先级问题？

### 1.1 最短路径优先（Nearest Wins）
```
项目A → 依赖B → 依赖X(v1.0)
项目A → 依赖C → 依赖D → 依赖X(v2.0)
传递依赖中路径更短的版本优先。 最终会选择 依赖X(v1.0)
```
### 1.2 先声明优先（First Declaration Wins）
```xml
<dependencies>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>D</artifactId> <!-- D 依赖 X(v2.0) -->
        <version>1.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>B</artifactId> <!-- B 依赖 X(v1.0) -->
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
> 当路径长度相同，依赖声明顺序决定优先级。最终会选择 D 依赖 X(v2.0)

### 1.3  显式依赖优先级最高
> 在`<dependencies>`中直接指定的版本覆盖其他所有情况。例如：
> - 显式声明3.0，即使传递依赖或`dependencyManagement`指定其他版本，最终使用3.0。
```xml
<dependencies>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>X</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
> *** 显式声明 同一模块 后声明的会覆盖前声明，如下 4.0.0 会 覆盖 3.0.0
```xml
<dependencies>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>X</artifactId>
    <version>3.0.0</version>
  </dependency>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>X</artifactId>
    <version>4.0.0</version>
  </dependency>
</dependencies>
```

### 1.4 dependencyManagement的作用
> - **优先级顺序**：
    > 	1. 1. 当前POM的`<dependencies>`显式版本（显式依赖）。
> 	2. 当前POM的`<dependencyManagement>`指定版本。
> 	3. 父POM的`<dependencyManagement>`指定版本。
> * 若未显式声明版本，`dependencyManagement`中的版本覆盖传递依赖。
```xml
<!-- dependencyManagement示例 -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>X</artifactId>
            <version>2.0.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```
### 1.5 排除冲突依赖（Exclusion）​​
> 使用`<exclusions>`排除不需要的传递依赖
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>D</artifactId>
    <version>1.0.0</version>
    <exclusions>
        <exclusion>
            <groupId>com.example</groupId>
            <artifactId>X</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
###  总结
> Maven依赖优先级遵循：
**1. 显式声明**
**2. 当前dependencyManagement**
**3. 父dependencyManagement**
**4. 路径最近**
**5. 第一声明****。
> * 通过合理使用`dependencyManagement`、排除依赖和显式声明，可有效解决版本冲突。

## 2. 分析依赖关系
### 2.1 工具与命令
```bash
mvn dependency:tree
```

### 2.2 工具与命令（推荐）
> 使用idea插件  `Maven Helper` 分析依赖关系：
> 1. 安装插件
> 2. 打开 pom文件 --> Dependency Analyzer   查看依赖关系