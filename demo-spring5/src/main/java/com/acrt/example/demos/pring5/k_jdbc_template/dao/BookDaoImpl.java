package com.acrt.example.demos.pring5.k_jdbc_template.dao;

import com.acrt.example.demos.pring5.k_jdbc_template.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * BookDaoImpl
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/14 19:27
 */
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Book book) {
        return jdbcTemplate.update("insert into book values(?, ?, ?)", book.getId(), book.getBName(), book.getBVersion());
    }

    @Override
    public Book selectById(int id) {
        return jdbcTemplate.queryForObject("select * from book", Book.class);
    }
}
