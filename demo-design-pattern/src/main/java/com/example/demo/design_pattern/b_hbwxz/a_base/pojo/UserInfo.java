package com.example.demo.design_pattern.b_hbwxz.a_base.pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author mars
 * @description UserInfo
 * @date 2023/10/20 13:59
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;

    @Column
    private String userEmail;
}
