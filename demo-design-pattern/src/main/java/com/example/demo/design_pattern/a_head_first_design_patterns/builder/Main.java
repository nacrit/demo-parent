package com.example.demo.design_pattern.a_head_first_design_patterns.builder;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import java.util.Arrays;
import java.util.List;

public class Main extends TestCase {
    // 媒体元素集合
    private List bookItemList = Arrays.asList(new Chapter[] {
            new Chapter("capter1"), new Chapter("capter2"),
            new Chapter("capter3"), new Chapter("capter4"),
    });
    private List magazineItemList = Arrays.asList(new Article[] {
            new Article("article1"), new Article("article2"),
            new Article("article3"), new Article("article4"),
    });
    private List webSiteItemList = Arrays.asList(new WebItem[] {
            new WebItem("web1"), new WebItem("web2"),
            new WebItem("web3"), new WebItem("web4"),
    });

    public void testBook() {
        // 通过构建这构建对象，实例化指导者对象
        MediaDirector buildBook = new MediaDirector(new BookBuilder());
        // 通过指导者组装对象的内容，并获取对象的内容
        Media book = buildBook.produceMedia(bookItemList);
        String result = "book: " + book;
        System.out.println(result);
        assertEquals(result, "book: [capter1, capter2, capter3, capter4]");
    }
    public void testMagazine() {
        MediaDirector buildMagazine = new MediaDirector(new MagazineBuilder());
        Media magazine = buildMagazine.produceMedia(magazineItemList);
        String result = "magazine: " + magazine;
        System.out.println(result);
        assertEquals(result, "magazine: [article1, article2, article3, article4]");
    }
    public void testWebSite() {
        MediaDirector buildWebSite = new MediaDirector(new WebSiteBuilder());
        Media webSite = buildWebSite.produceMedia(webSiteItemList);
        String result = "web site: " + webSite;
        System.out.println(result);
        assertEquals(result, "web site: [web1, web2, web3, web4]");
    }
    public static void main(String[] args) {
        TestRunner.run(Main.class);
    }
}
