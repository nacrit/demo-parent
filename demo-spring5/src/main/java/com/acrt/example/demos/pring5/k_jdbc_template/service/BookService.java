package com.acrt.example.demos.pring5.k_jdbc_template.service;


import com.acrt.example.demos.pring5.k_jdbc_template.model.Book;

/**
 * <p>
 * BookService
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/14 18:30
 */
public interface BookService {
    boolean add(Book book);
    boolean modify(Book book);
    boolean delete(int id);
    Book find(int id);
}
