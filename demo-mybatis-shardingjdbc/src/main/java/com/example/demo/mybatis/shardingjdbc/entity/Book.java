package com.example.demo.mybatis.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Macky
 * @Title class Book
 * @Description 书籍是实体类
 * @date 2019/7/13 15:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("book")
public class Book extends Model<Book> {
    private int id;
    private String name;
    private int count;
}
