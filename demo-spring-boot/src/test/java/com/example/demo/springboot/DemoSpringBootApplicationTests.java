package com.example.demo.springboot;

import com.example.demo.springboot.regist_bean.ClassBImpl;
import com.example.demo.springboot.regist_bean.GenericBeanA;
import com.example.demo.springboot.regist_bean.GenericBeanB;
import com.example.demo.springboot.regist_bean.SpringBeanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoSpringBootApplicationTests {

    /**
     * registerSingleton 并不能对要加入容器的bean进行DI
     */
    @Test
    void testRegisterSingleton() {
        // 使用 registerSingleton 是无法进行依赖注入的，即 BeanA不能自动注入到GenericBean中
        GenericBeanA test1 = new GenericBeanA("test1");
        Assertions.assertNull(test1.getBeanA());

        // 可通过构造方法赋值进行注入
        GenericBeanB test2 = new GenericBeanB("test2");
        test2.say();

    }

    /**
     * 对抽象类依赖注入
     */
    @Test
    void testAbstractClassDI() {
        ClassBImpl classB = SpringBeanUtils.getBean(ClassBImpl.class);
        // 正常输出，说明抽象类的子类加了@Component注解，其父类（抽象）无论加没加@Component都可以注入其它bean
        classB.say();
    }

}
