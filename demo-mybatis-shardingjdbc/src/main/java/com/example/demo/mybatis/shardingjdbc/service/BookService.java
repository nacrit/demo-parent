package com.example.demo.mybatis.shardingjdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.mybatis.shardingjdbc.entity.Book;

import java.util.List;

/**
 * @author Macky
 * @Title interface BookService
 * @Description BookService
 * @date 2019/7/12 20:47
 */
public interface BookService extends IService<Book> {

    /**
     * 保存书籍信息
     *
     * @param book
     * @return
     */
    @Override
    boolean save(Book book);

    /**
     * 查询全部书籍信息
     *
     * @return
     */
    List<Book> getBookList();
}
