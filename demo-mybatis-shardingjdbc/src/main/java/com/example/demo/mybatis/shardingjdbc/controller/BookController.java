package com.example.demo.mybatis.shardingjdbc.controller;

import com.example.demo.mybatis.shardingjdbc.entity.Book;
import com.example.demo.mybatis.shardingjdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Macky
 * @Title class BookController
 * @Description BookController
 * @date 2019/7/12 20:53
 */
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> getItems(){
        return bookService.getBookList();
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public Boolean saveItem(@RequestBody Book book){
        return bookService.save(book);
    }
}
