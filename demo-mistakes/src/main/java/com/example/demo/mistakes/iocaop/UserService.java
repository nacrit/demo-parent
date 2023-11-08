package com.example.demo.mistakes.iocaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author zhenghao
 * @date 2020/8/18 16:24
 */

@Service
@Slf4j
public class UserService {
//    @Autowired
//    private UserRepository userRepository;
    @Transactional
    @Metrics //启用方法监控
    public void createUser(UserEntity entity) {
//        userRepository.save(entity);
        if (entity.getName().contains("test"))
            throw new RuntimeException("invalid username!");
    }

    public int getUserCount(String name) {
//        return userRepository.findByName(name).size();
        return 0;
    }
}


