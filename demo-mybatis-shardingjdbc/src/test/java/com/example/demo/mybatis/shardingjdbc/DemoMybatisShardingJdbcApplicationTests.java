package com.example.demo.mybatis.shardingjdbc;

import com.example.demo.mybatis.shardingjdbc.entity.Book;
import com.example.demo.mybatis.shardingjdbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoMybatisShardingJdbcApplicationTests {
    @Autowired
    BookService bookService;

    @Test
    void contextLoads() {
        List<Book> list = new ArrayList<>();
        for (int i = 1; i < 201; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("" + i + i + i);
            book.setCount(i * 10 + i);
            list.add(book);
            bookService.save(book);
        }
//        bookService.saveBatch(list, 20);
    }

}
