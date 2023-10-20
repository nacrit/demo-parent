package com.example.demo.design_pattern.b_hbwxz.a_base.repo;

import com.example.demo.design_pattern.b_hbwxz.a_base.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mars
 * @description UserRepository
 * @date 2023/10/20 14:01
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUserName(String userName);
    UserInfo findByUserNameAndUserPassword(String account, String password);
}
