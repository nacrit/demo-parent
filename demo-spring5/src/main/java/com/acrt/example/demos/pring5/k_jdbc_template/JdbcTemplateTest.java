package com.acrt.example.demos.pring5.k_jdbc_template;

import com.acrt.example.demos.pring5.k_jdbc_template.model.Book;
import com.acrt.example.demos.pring5.k_jdbc_template.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * <p>
 * JdbcTemplateTest
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/14 18:30
 */
public class JdbcTemplateTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("k-jdbc-template-01.xml");
        BookService bookService = applicationContext.getBean(BookService.class);
//        Book book = new Book(1, "西游记", "吴承恩");
//        Book book = new Book(2, "红楼梦", "曹雪芹");
//        Book book = new Book(3, "三国演义", "罗贯中");
        Book book = new Book(4, "水浒传", "施耐庵");
        bookService.add(book);
    }
}
