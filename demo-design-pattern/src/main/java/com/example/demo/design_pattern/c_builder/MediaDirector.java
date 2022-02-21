package com.example.demo.design_pattern.c_builder;

import java.util.Iterator;
import java.util.List;

//指导者角色，也叫上下文
public class MediaDirector {
    private MediaBuilder mb;
    public MediaDirector(MediaBuilder mb) {
        this.mb = mb; //具有策略模式相似特征的
    }
    // 批量添加元素并返回所有元素
    public Media produceMedia(List input) {
        mb.buildBase();
        for(Iterator it = input.iterator(); it.hasNext();)
            mb.addMediaItem((MediaItem)it.next());
        return mb.getFinishedMedia();
    }
}
