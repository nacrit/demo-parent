package com.acrt.example.demos.pring5.k_jdbc_template.service;

import com.acrt.example.demos.pring5.k_jdbc_template.dao.BookDao;
import com.acrt.example.demos.pring5.k_jdbc_template.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    @Override
    public boolean add(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public boolean modify(Book book) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Book find(int id) {
        return bookDao.selectById(id);
    }
}
