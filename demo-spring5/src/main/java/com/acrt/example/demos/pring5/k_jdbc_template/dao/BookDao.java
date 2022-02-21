package com.acrt.example.demos.pring5.k_jdbc_template.dao;


import com.acrt.example.demos.pring5.k_jdbc_template.model.Book;

/**
 * <p>
 * BookDao
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/14 18:58
 */
public interface BookDao {

    int insert(Book book);

    Book selectById(int id);
}
