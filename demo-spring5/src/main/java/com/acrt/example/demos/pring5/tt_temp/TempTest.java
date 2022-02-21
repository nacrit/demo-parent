package com.acrt.example.demos.pring5.tt_temp;

import com.acrt.example.demos.pring5.aa_introductory.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 临时测试
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 16:46
 */
public class TempTest {

    public static void main(String[] args) {
        // 加载配置及，启动容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tt-bean-01.xml");
        // 获取容器中的bean
        Emp emp = applicationContext.getBean("emp", Emp.class);
        // 操作bean
        System.out.println(emp);
        
        // 获取容器中的bean
        Dept dept = applicationContext.getBean("dept2", Dept.class);
        // 操作bean
        System.out.println(dept);
    }
}
