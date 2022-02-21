package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.dao.DemoMybatisMapper;
import com.example.demo.mybatis.dao.TempMapper;
import com.example.demo.mybatis.model.DemoMybatis;
import com.example.demo.mybatis.service.DemoMybatisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author zhenghao
 * @description 实现
 * @date 2020/7/9 9:31
 */
@Service
public class DemoMybatisServiceImpl implements DemoMybatisService {

    @Resource
    private DemoMybatisMapper demoMybatisMapper;
    @Resource
    private TempMapper tempMapper;

    @Override
    public DemoMybatis find(Integer id) {
        return demoMybatisMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean modify(DemoMybatis demoMybatis) {
        return demoMybatisMapper.updateByPrimaryKey(demoMybatis) > 0;
    }

//    @Override
//    public boolean tempUpdate(Integer id, String name) {
//        return demoMybatisMapper.tempUpdate(id, name) > 0;
//    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public void testTra() {
        System.out.println("testTra....");
//        boolean b = tempMapper.tempUpdate(1, "tempUpdate:" + new Random().nextInt(100)) > 0;
//        System.out.println("tempUpdate result：" + b);

//        boolean b = modify(new DemoMybatis(1, "modify:" + new Random().nextInt(100)));
//        System.out.println("modify result：" + b);
//        int i = 20;
//        do {
////            DemoMybatis demoMybatis = find(1);
//            DemoMybatis demoMybatis = demoMybatisMapper.selectByPrimaryKey(1);
//            System.out.println("demoMybatis：" + demoMybatis);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (i-- > 0);
        retryMethod(1);
    }


    @Override
    // maxAttempts最大尝试次数，delay间隔时间，multiplier间隔倍数（间隔时间根据倍数依次递增）
    @Retryable(value = RetryException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    @Transactional
    public void retryMethod(Integer id) {
        Long currentTimeMillis = System.currentTimeMillis();
        DemoMybatis demoMybatis = demoMybatisMapper.selectByPrimaryKey(id);
        if (!demoMybatis.getName().equals("0000")) {
            System.err.println("尝试...." + currentTimeMillis);
            throw new RetryException("重试失败！！");
        }
    }

    @Recover
    public void recover1(Exception e){
        System.out.println("回调方法执行！！！！111" + e.toString());
        //记日志到数据库 或者调用其余的方法
    }
}
