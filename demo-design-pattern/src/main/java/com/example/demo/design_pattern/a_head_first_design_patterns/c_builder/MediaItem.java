package com.example.demo.design_pattern.a_head_first_design_patterns.c_builder;

// 进而包含不同的媒体组成元素
class MediaItem {
    private String s;
    public MediaItem(String s) {
        this.s = s;
    }
    public String toString() {
        return s;
    }
}
// 篇章
class Chapter extends MediaItem {
    public Chapter(String s) { super(s); }
}
// (报刊上的)文章
class Article extends MediaItem {
    public Article(String s) { super(s); }
}
// 网络项目
class WebItem extends MediaItem {
    public WebItem(String s) { super(s); }
}