package com.example.demo.closuretable.service;

import com.example.demo.closuretable.mapper.DemoTreeMapper;
import com.example.demo.closuretable.model.DemoTree;
import com.example.demo.closuretable.model.DemoTreeChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 实现类
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/11 14:11
 */
@Service
public class DemoTreeServiceImpl {

    @Resource
    private DemoTreeMapper demoTreeMapper;

    @Transactional
    public boolean add(Integer parentId, Integer newId) {
        DemoTree demoTree = demoTreeMapper.selectByPrimaryKey(newId);
        if (demoTree != null) {
            throw new RuntimeException("new node already exists!");
        }
        return demoTreeMapper.insert(parentId, newId) > 0;
    }

    @Transactional
    public boolean batchAdd(List<DemoTree> list) {
        return demoTreeMapper.batchInsert(list) > 0;
    }

    public DemoTree selectByPrimaryKey(Integer descendant) {
        return demoTreeMapper.selectByPrimaryKey(descendant);
    }

    public List<DemoTreeChain> selectChain(Integer id) {
        return demoTreeMapper.selectChain(id);
    }

    public List<Integer> findAllAncestorByDescendant(Integer id) {
        return demoTreeMapper.selectAllAncestorByDescendant(id);
    }

    @Transactional
    public boolean deleteByPrimaryKey(Integer id) {
        return demoTreeMapper.deleteByPrimaryKey(id) > 0;
    }

    @Transactional
    public boolean move(Integer fromId, Integer toId) {
        List<Integer> ancestorList = demoTreeMapper.selectAllAncestorByDescendant(toId);
        if (!CollectionUtils.isEmpty(ancestorList)) {
            if (ancestorList.contains(fromId)) {
                throw new RuntimeException("You can't move to your subordinates!");
            }
        }
        return demoTreeMapper.move(fromId, toId) > 0;
    }
}
