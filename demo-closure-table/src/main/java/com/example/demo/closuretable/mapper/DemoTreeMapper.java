package com.example.demo.closuretable.mapper;

import com.example.demo.closuretable.model.DemoTree;
import com.example.demo.closuretable.model.DemoTreeChain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoTreeMapper {

    int insert(@Param("parentId") Integer parentId, @Param("newId") Integer newId);
    int batchInsert(@Param("list") List<DemoTree> list);

    /**
     * 删除某节点及其下级节点
     * @param id 节点id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    DemoTree selectByPrimaryKey(Integer descendant);

    /**
     * 查询某节点下节点的关系链
     * @param id 某节点
     * @return 关系链，逗号隔开
     */
    List<DemoTreeChain> selectChain(Integer id);

    /**
     * 某节点的链
     * @param descendant 子孙
     * @return 关系链
     */
    List<Integer> selectAllAncestorByDescendant(Integer descendant);

    /**
     * 将节点a移动到节点b下
     * @param fromId 节点a
     * @param toId 节点b
     * @return 影响行数
     */
    int move(@Param("fromId") Integer fromId, @Param("toId")Integer toId);
}