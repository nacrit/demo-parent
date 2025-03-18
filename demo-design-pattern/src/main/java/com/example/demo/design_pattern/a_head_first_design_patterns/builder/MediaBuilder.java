package com.example.demo.design_pattern.a_head_first_design_patterns.builder;

// 抽象建造者角色，它规范了所有媒体建造的步骤:
public class MediaBuilder {
    // 基础构建
    public void buildBase() {}
    // 添加元素
    public void addMediaItem(MediaItem item) {}
    // 获取元素
    public Media getFinishedMedia() { return null; }
}

// 具体建造者角色：书建造者
class BookBuilder extends MediaBuilder {
    private Book b;
    public void buildBase() {
        System.out.println("Building book framework");
        b = new Book();
    }
    public void addMediaItem(MediaItem chapter) {
        System.out.println("Adding chapter " + chapter);
        b.add(chapter);
    }
    public Media getFinishedMedia() { return b; }
}
// 具体建造者角色：杂志建造者
class MagazineBuilder extends MediaBuilder {
    private Magazine m;
    public void buildBase() {
        System.out.println("Building magazine framework");
        m = new Magazine();
    }
    public void addMediaItem(MediaItem article) {
        System.out.println("Adding article " + article);
        m.add(article);
    }
    public Media getFinishedMedia() { return m; }
}
// 具体建造者角色：网站建造者
class WebSiteBuilder extends MediaBuilder {
    private WebSite w;
    public void buildBase() {
        System.out.println("Building web site framework");
        w = new WebSite();
    }
    public void addMediaItem(MediaItem webItem) {
        System.out.println("Adding web item " + webItem);
        w.add(webItem);
    }
    public Media getFinishedMedia() { return w; }
}