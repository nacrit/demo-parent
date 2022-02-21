package com.example.demo.mybatis.plus.auto.generator.test2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.plus.auto.generator.test2.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

    @Resource
    private IUserService iUserService;

    /*插入
    // 插入一条记录（选择字段，策略插入）
    boolean save(T entity);
    // 插入（批量）
    boolean saveBatch(Collection<T> entityList);
    // 插入（批量）
    boolean saveBatch(Collection<T> entityList, int batchSize);
     */
    @Test
    public void saveTest() {
        User user = new User();
        user.setAge(30);
        user.setName("anchorite");
        user.setEmail("anchorite@qq.com");
        iUserService.save(user);
    }

    /*
    // TableId 注解存在更新记录，否插入一条记录
    boolean saveOrUpdate(T entity);
    // 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
    boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
    // 批量修改插入
    boolean saveOrUpdateBatch(Collection<T> entityList);
    // 批量修改插入
    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
     */
    @Test
    public void saveOrUpdateTest() {
        User user = new User();
        user.setId(7L);
        user.setAge(30);
        user.setName("anchorite2");
        user.setEmail("anchorite2@qq.com");
        iUserService.saveOrUpdate(user);
    }

    /*
    // 根据 entity 条件，删除记录
    boolean remove(Wrapper<T> queryWrapper);
    // 根据 ID 删除
    boolean removeById(Serializable id);
    // 根据 columnMap 条件，删除记录
    boolean removeByMap(Map<String, Object> columnMap);
    // 删除（根据ID 批量删除）
    boolean removeByIds(Collection<? extends Serializable> idList);
     */
    @Test
    public void removeTest() {
        QueryWrapper<User> query = new QueryWrapper<>();
        User user = new User();
        user.setId(6L);
        query.setEntity(user);
        boolean count = iUserService.remove(query);
        Assert.assertNotEquals(count, 0);
    }

    /*
    // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
    boolean update(Wrapper<T> updateWrapper);
    // 根据 whereEntity 条件，更新记录
    boolean update(T entity, Wrapper<T> updateWrapper);
    // 根据 ID 选择修改
    boolean updateById(T entity);
    // 根据ID 批量更新
    boolean updateBatchById(Collection<T> entityList);
    // 根据ID 批量更新
    boolean updateBatchById(Collection<T> entityList, int batchSize);
     */
    @Test
    public void updateTest() {
        UpdateWrapper<User> query = new UpdateWrapper<User>();
        User user = new User();
        user.setId(7L);
//        user.setAge(23);
//        user.setName("anchorite3");
//        user.setEmail("anchorite3@qq.com");
        query.setEntity(user);
        query.set("age", "23");
        query.set("name", "anchorite3");
        query.set("email", "anchorite3@qq.com");
        boolean count = iUserService.update(query);
        Assert.assertNotEquals(count, 0);
    }

    /*
    // 根据 ID 查询
    T getById(Serializable id);
    // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
    T getOne(Wrapper<T> queryWrapper);
    // 根据 Wrapper，查询一条记录，如果多条是否抛出异常
    T getOne(Wrapper<T> queryWrapper, boolean throwEx);
    // 根据 Wrapper，查询一条记录
    Map<String, Object> getMap(Wrapper<T> queryWrapper);
    // 根据 Wrapper，查询一条记录
    <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
     */
    @Test
    public void getTest() {
        User user = iUserService.getById(7L);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    /*
    // 查询所有
    List<T> list();
    // 查询列表
    List<T> list(Wrapper<T> queryWrapper);
    // 查询（根据ID 批量查询）
    Collection<T> listByIds(Collection<? extends Serializable> idList);
    // 查询（根据 columnMap 条件）
    Collection<T> listByMap(Map<String, Object> columnMap);
    // 查询所有列表
    List<Map<String, Object>> listMaps();
    // 查询列表
    List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
    // 查询全部记录
    List<Object> listObjs();
    // 查询全部记录
    <V> List<V> listObjs(Function<? super Object, V> mapper);
    // 根据 Wrapper 条件，查询全部记录
    List<Object> listObjs(Wrapper<T> queryWrapper);
    // 根据 Wrapper 条件，查询全部记录
    <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
     */
    @Test
    public void listTest() {
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.gt("age", 20);
        List<User> list = iUserService.list(query);
        Assert.assertNotNull("查询结果为null", list);
        list.forEach(System.out::println);
        // 把每个对象的属性封装为key value形式
        List<Map<String, Object>> maps = iUserService.listMaps();
        Assert.assertNotNull(maps);
        System.out.println(maps);
    }

    /*
    // 无条件翻页查询
    IPage<T> page(IPage<T> page);
    // 翻页查询
    IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
    // 无条件翻页查询
    IPage<Map<String, Object>> pageMaps(IPage<T> page);
    // 翻页查询
    IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);

    // 查询总记录数
    int count();
    // 根据 Wrapper 条件，查询总记录数
    int count(Wrapper<T> queryWrapper);
     */
    @Test
    public void pageTest() {
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.gt("age", 20);
        int count = iUserService.count(query);  // 总条数
        System.out.println(count);
        IPage<User> page = iUserService.page(new Page<>(1, 3, count), query);
        Assert.assertNotNull(page);
        System.out.println("总条数:" + page.getTotal());    // 总条数
        System.out.println("每页大小:" + page.getSize());     // 每页大小
        System.out.println("共多少页:" + page.getPages());    // 共多少页
        System.out.println("当前第几页:" + page.getCurrent());  // 当前第几页
        // 结果集
        page.getRecords().forEach(System.out::println);
    }

    /*
    // 链式查询 普通
    QueryChainWrapper<T> query();
    // 链式查询 lambda 式。注意：不支持 Kotlin
    LambdaQueryChainWrapper<T> lambdaQuery();
    // 示例：
    query().eq("column", value).one();
    lambdaQuery().eq(Entity::getId, value).list();

    // 链式更改 普通
    UpdateChainWrapper<T> update();
    // 链式更改 lambda 式。注意：不支持 Kotlin
    LambdaUpdateChainWrapper<T> lambdaUpdate();
    // 示例：
    update().eq("column", value).remove();
    lambdaUpdate().eq(Entity::getId, value).update(entity);
     */
    @Test
    public void chainQueryAndUpdateTest() {
        User user = iUserService.query().eq("age", 18).one();
        Assert.assertNotNull(user);
        System.out.println(user);
        System.out.println("=====================================");
        List<User> list = iUserService.lambdaQuery().ge(User::getAge, 20).list();
        Assert.assertNotEquals(list.size(), 0);
        list.forEach(System.out::println);

        // 链式更新
        boolean removeCount = iUserService.update().eq("id", 1L).remove();
        Assert.assertNotEquals(removeCount, 0);
        User user1 = new User();
        user1.setName("lambadaUpdateName");
        boolean updateCount = iUserService.lambdaUpdate().eq(User::getId, 2L).update(user1);
        Assert.assertNotEquals(updateCount, 0);
    }

    /*
    AbstractWrapper：
        QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类
        用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件
        注意: entity 生成的 where 条件与 使用各个 api 生成的 where 条件没有任何关联行为
        allEq:  params : key为数据库字段名,value为字段值
                null2IsNull : 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
            allEq(Map<R, V> params)
            allEq(Map<R, V> params, boolean null2IsNull)
            allEq(boolean condition, Map<R, V> params, boolean null2IsNull)
         eq：等于 =
         nq：不等于 <>
         gt：大于  >
         lt：小于  <
         ge：大于等于 >=
         le：小于等于 <=
         like：例: like("name", "王")--->name like '%王%'
         not like：例: notLike("name", "王")--->name not like '%王%'
         likeLeft：例: likeLeft("name", "王")--->name like '%王'
         likeRight：例: likeLeft("name", "王")--->name like '王%'
         isNull：例: isNull("name")--->name is null
         isNotNull：例: isNotNull("name")--->name is not null
         in：例: in("age",{1,2,3})--->age in (1,2,3)
         notIn：例: notIn("age",{1,2,3})--->age not in (1,2,3)
         inSql：
            例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
            例: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)
         notInSql：同inSql 加not
         groupBy：例: groupBy("id", "name")--->group by id,name
         orderByAsc：例: orderByAsc("id", "name")--->order by id ASC,name ASC
         orderByDesc：例: orderByDesc("id", "name")--->order by id DESC,name DESC
         orderBy：orderBy(boolean condition, boolean isAsc, R... columns)
            例: orderBy(true, true, "id", "name")--->order by id ASC,name ASC
        having：HAVING (sql语句)
            例: having("sum(age) > 10")--->having sum(age) > 10
            例: having("sum(age) > {0}", 11)--->having sum(age) > 11
        or：主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
            例: or(i -> i.eq("name", "李白").ne("status", "活着"))--->or (name = '李白' and status <> '活着')
        and：例: and(i -> i.eq("name", "李白").ne("status", "活着"))--->and (name = '李白' and status <> '活着')
        apply：该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!
            例: apply("id = 1")--->id = 1
            例: apply("id = {0}", 1)--->id = 1
        last：无视优化规则直接拼接到 sql 的最后，只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
            例: last("limit 1")
        exists：exists(String existsSql)
            例: exists("select id from table where age = 1")--->exists (select id from table where age = 1)
        notExists：拼接 NOT EXISTS (sql语句)

    QueryWrapper：继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件及 LambdaQueryWrapper,
        可以通过 new QueryWrapper().lambda() 方法获取
        select：以下方分法为两类.第二类方法为:过滤查询字段(主键除外),
        入参不包含 class 的调用前需要wrapper内的entity属性有值! 这两类方法重复调用以最后一次为准
            select(String... sqlSelect)
            select(Predicate<TableFieldInfo> predicate)
            select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
            例: select("id", "name", "age")
            例: select(i -> i.getProperty().startsWith("test"))
    UpdateWrapper：继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件及 LambdaUpdateWrapper,
        可以通过 new UpdateWrapper().lambda() 方法获取!
        set：SQL SET 字段
            例: set("name", "老李头")
            例: set("name", "")--->数据库字段值变为空字符串
            例: set("name", null)--->数据库字段值变为null
        setSql：设置 SET 部分 SQL
            例: setSql("name = '老李头'")
        lambda：获取 LambdaWrapper
            在QueryWrapper中是获取LambdaQueryWrapper
            在UpdateWrapper中是获取LambdaUpdateWrapper


    Service.java
        mysqlMapper.getAll(Wrappers.<MysqlData>lambdaQuery().eq(MysqlData::getGroup, 1));
    方案一 注解方式 Mapper.java
        @Select("select * from mysql_data ${ew.customSqlSegment}")
        List<MysqlData> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);
    方案二 XML形式 Mapper.xml
        <select id="getAll" resultType="MysqlData">
            SELECT * FROM mysql_data ${ew.customSqlSegment}
        </select>
     */
    @Test
    public void queryAndUpdateWrapperTest() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.orderBy(true, true, "id", "name");
        query.apply("id=1");

    }

    /*
    分页插件
    1. <!-- spring xml 方式 -->
        <property name="plugins">
            <array>
                <bean class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor">
                    <property name="sqlParser" ref="自定义解析类、可以没有"/>
                    <property name="dialectClazz" value="自定义方言类、可以没有"/>
                    <!-- COUNT SQL 解析.可以没有 -->
                    <property name="countSqlParser" ref="countSqlParser"/>
                </bean>
            </array>
        </property>

        <bean id="countSqlParser" class="com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize">
            <!-- 设置为 true 可以优化部分 left join 的sql -->
            <property name="optimizeJoin" value="true"/>
        </bean>
    2. Spring boot方式
        @EnableTransactionManagement
        @Configuration
        @MapperScan("com.baomidou.cloud.service.*.mapper*")
        public class MybatisPlusConfig {

            @Bean
            public PaginationInterceptor paginationInterceptor() {
                PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
                // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
                // paginationInterceptor.setOverflow(false);
                // 设置最大单页限制数量，默认 500 条，-1 不受限制
                // paginationInterceptor.setLimit(500);
                // 开启 count 的 join 优化,只针对部分 left join
                paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
                return paginationInterceptor;
            }
        }


     XML 自定义分页
        UserMapper.java 方法内容
            public interface UserMapper {//可以继承或者不继承BaseMapper
                / **
                 * <p>
                 * 查询 : 根据state状态查询用户列表，分页显示
                 * </p>
                 *
                 * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
                 * @param state 状态
                 * @return 分页对象
                 * /
                IPage<User> selectPageVo(Page<?> page, Integer state);
            }
        UserMapper.xml 等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
            <select id="selectPageVo" resultType="com.baomidou.cloud.entity.UserVo">
                SELECT id,name FROM user WHERE state=#{state}
            </select>
        UserServiceImpl.java 调用分页方法
            public IPage<User> selectUserPage(Page<User> page, Integer state) {
                // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
                // page.setOptimizeCountSql(false);
                // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
                // 要点!! 分页返回的对象与传入的对象是同一个
                return userMapper.selectPageVo(page, state);
            }
     */

    /*
    Sequence主键
        主键生成策略必须使用INPUT
            支持父类定义@KeySequence子类继承使用
            支持主键类型指定(3.3.0开始自动识别主键类型)
            内置支持：
                DB2KeyGenerator
                H2KeyGenerator
                KingbaseKeyGenerator
                OracleKeyGenerator
                PostgreKeyGenerator
            如果内置支持不满足你的需求，可实现IKeyGenerator接口来进行扩展.

        举个栗子：
            @KeySequence(value = "SEQ_ORACLE_STRING_KEY", clazz = String.class)
            public class YourEntity {
                @TableId(value = "ID_STR", type = IdType.INPUT)
                private String idStr;

            }
        SpringBoot方式：
        方式一：使用配置类
            @Bean
            public IKeyGenerator keyGenerator() {
                return new H2KeyGenerator();
            }
        方式二：通过MybatisPlusPropertiesCustomizer自定义
            @Bean
            public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
                return plusProperties -> plusProperties.getGlobalConfig().getDbConfig().setKeyGenerator(new H2KeyGenerator());
            }
        Spring方式：
        方式一: XML配置
            <bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
               <property name="dbConfig" ref="dbConfig"/>
            </bean>
            <bean id="dbConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig">
               <property name="keyGenerator" ref="keyGenerator"/>
            </bean>
            <bean id="keyGenerator" class="com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator"/>
        方式二：注解配置
            @Bean
            public GlobalConfig globalConfig() {
                GlobalConfig conf = new GlobalConfig();
                conf.setDbConfig(new GlobalConfig.DbConfig().setKeyGenerator(new H2KeyGenerator()));
                return conf;
            }

     */

    /*
    自定义ID生成器
        自3.3.0开始,默认使用雪花算法+UUID(不含中划线)
        自定义示例工程 spring-boot示例 ：https://gitee.com/baomidou/mybatis-plus-samples/tree/master/mybatis-plus-sample-id-generator
     */


}