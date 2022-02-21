package com.example.demo.closuretable.service;

import com.example.demo.closuretable.model.DemoTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTreeServiceImplTest {
    @Autowired
    private DemoTreeServiceImpl demoTreeService;

    @Test
    public void add() {
        boolean isInsert = demoTreeService.add(9, 3);
        System.out.println(isInsert);
    }

    @Test
    public void batchAdd() {
        // 父id
        List<Integer> pList = new ArrayList<>();
        pList.add(0);
        Random random = new Random();
        List<DemoTree> list = new ArrayList<>();
        for (int i = 60000; i < 100000; i++) {
//            demoTreeService.add(random.nextInt(i), i);
            Integer parentId = pList.get(random.nextInt(pList.size()));
            System.out.println("=====" + parentId + "--" + i);
            list.add(new DemoTree(parentId, i));
//            System.out.println("=====" + (i - 1) + "--" + i);
//            list.add(new DemoTree((i - 1), i));
            // 每200条批量添加一次
            if (i % 400 == 0 && list.size() > 0) {
                boolean flag = demoTreeService.batchAdd(list);
                System.out.println("---------------------------" + list.size() + " -- " + i);
                list = new ArrayList<>();
            }
            if (i % 800 == 0 && pList.size() > 0) {
                pList = new ArrayList<>();
            }
            pList.add(i);
        }
        if (list.size() > 0) {
            demoTreeService.batchAdd(list);
        }
    }

    @Test
    public void findByPrimaryKey() {
//        DemoTree demoTree = demoTreeService.selectByPrimaryKey(1);
//        System.out.println(demoTree);
//        List<DemoTreeChain> demoTreeChains = demoTreeService.selectChain(28960);
//        demoTreeChains.forEach(e -> System.out.println(e.getId() + "-->" + e.getIdChain()));
        List<Integer> list = demoTreeService.findAllAncestorByDescendant(4864);
        System.out.println(list);

    }

    @Test
    public void remove() {
        boolean flag = demoTreeService.deleteByPrimaryKey(2);
    }

    @Test
    public void move() {
        boolean move = demoTreeService.move(10, 8);
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        batchAdd();
//        move();
//        remove();
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "毫秒");
    }
}