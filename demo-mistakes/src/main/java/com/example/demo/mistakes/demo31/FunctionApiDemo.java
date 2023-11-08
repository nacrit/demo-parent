package com.example.demo.mistakes.demo31;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author zhenghao
 * @description Java 8 类对于函数式 API 的增强
 * @date 2020/6/17 11:17
 */
public class FunctionApiDemo {
    /**
     * 除了 Stream 之外，Java 8 中有很多类也都实现了函数式的功能。
     */
    public static void main(String[] args) throws IOException {
//        notCoolCache();
        coolCache();
//        filesExampleJava7(new File("."));
//        filesExample();
    }
    private static Map<Long, Product> cache = new ConcurrentHashMap<>();

    private static Product getProductAndCache(Long id) {
        Product product = null;
        //Key存在，返回Value
        if (cache.containsKey(id)) {
            product = cache.get(id);
        } else {
            //不存在，则获取Value
            //需要遍历数据源查询获得Product
            for (Product p : Product.getData()) {
                if (p.getId().equals(id)) {
                    product = p;
                    break;
                }
            }
            //加入ConcurrentHashMap
            if (product != null)
                cache.put(id, product);
        }
        return product;
    }

    public static void notCoolCache() {
        System.out.println(getProductAndCache(1L));
        System.out.println(getProductAndCache(100L));
        System.out.println(cache);
        System.out.println(cache.size());
        System.out.println(cache.containsKey(1L));
    }


    // 使用computeIfAbsent实现
    private static Product getProductAndCacheCool(Long id) {
        return cache.computeIfAbsent(id, i -> //当Key不存在的时候提供一个Function来代表根据Key获取Value的过程
                Product.getData().stream()
                        .filter(p -> p.getId().equals(i)) //过滤
                        .findFirst() //找第一个，得到Optional<Product>
                        .orElse(null)); //如果找不到Product，则使用null
    }

    public static void coolCache() {
        System.out.println(getProductAndCacheCool(1L));
        System.out.println(getProductAndCacheCool(100L));

        System.out.println(cache);
        System.out.println(cache.size());
        System.out.println(cache.containsKey(1L));
    }


    /**
     * 利用 Files.walk 返回一个 Path 的流，通过两行代码就能实现递归搜索 +grep 的操作。
     * 整个逻辑是：递归搜索文件夹，查找所有的.java 文件；然后读取文件每一行内容，用正则表达式匹配 public class 关键字；最后输出文件名和这行内容。
     * @throws IOException
     */
    private static Pattern PUBLIC_CLASS = Pattern.compile("public class");
    public static void filesExample() throws IOException {
        //无限深度，递归遍历文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("."))) {  // 当前项目下的所有文件
            pathStream.filter(Files::isRegularFile) //只查普通文件
                    .filter(FileSystems.getDefault().getPathMatcher("glob:**/*.java")::matches) //搜索java源码文件
                    .flatMap(ThrowingFunction.unchecked(path ->
                            Files.readAllLines(path).stream() //读取文件内容，转换为Stream<List>
                                    .filter(line -> PUBLIC_CLASS.matcher(line).find()) //使用正则过滤带有public class的行
                                    .map(line -> path.getFileName() + " >> " + line))) //把这行文件内容转换为文件名+行
                    .forEach(System.out::println); //打印所有的行
        }
    }

    public static void filesExampleJava7(File dir) throws IOException {
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        final File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (Files.isRegularFile(file.toPath()) && file.getName().endsWith(".java")) {
                    final List<String> lines = Files.readAllLines(file.toPath());
                    for (String line : lines) {
                        if (PUBLIC_CLASS.matcher(line).find()) {
                            System.out.println(file.getName() + " --> " + line);
                        }
                    }
                } else if (file.isDirectory()) {
                    filesExampleJava7(file);
                }
            }
        }
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Throwable> {
        static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {
            return t -> {
                try {
                    return f.apply(t);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            };
        }
        R apply(T t) throws E;
    }
    /*
    computeIfAbsent 方法在逻辑上相当于：
        if (map.get(key) == null) {
          V newValue = mappingFunction.apply(key);
          if (newValue != null)
            map.put(key, newValue);
        }
     */

}
