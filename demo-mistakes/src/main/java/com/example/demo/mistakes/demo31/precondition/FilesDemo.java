package com.example.demo.mistakes.demo31.precondition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author zhenghao
 * @description Files Demo
 * @date 2020/6/18 15:10
 */
public class FilesDemo {

    public static void main(String[] args) throws IOException {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();
    }

    /**
     * 该程序将目录遍历两个级别。
     * 我们应用带有Files.isRegular()谓词的过滤器。
     */
    public static void demo1() {
        String path = ".";
        try (Stream<Path> pathStream = Files.walk(Paths.get(path), 2)){
            pathStream.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 显示指定目录中的目录
     */
    public static void demo2() {
        String path = ".";
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))){
            pathStream.filter(Files::isDirectory).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出了指定目录和其子目录中所有 .properties 文件。
     */
    public static void demo3() {
        String path = ".";
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))){
            pathStream.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(s -> s.endsWith(".properties"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示指定目录中的常规文件。
     * @throws IOException
     */
    public static void demo4() throws IOException {
        Files.list(Paths.get("."))
                .filter(Files::isRegularFile)
                .filter(p -> p.toFile().isFile())
                .forEach(System.out::println);
    }
    /**
     * 列出了用户主目录中的目录。。
     * @throws IOException
     */
    public static void demo5() throws IOException {
        Files.list(Paths.get("."))
//                .filter(Files::isDirectory)
                .filter(p -> p.toFile().isDirectory())
                .forEach(System.out::println);
    }

    public static void demo6() throws IOException {
        Files.list(Paths.get("."))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    }


}
