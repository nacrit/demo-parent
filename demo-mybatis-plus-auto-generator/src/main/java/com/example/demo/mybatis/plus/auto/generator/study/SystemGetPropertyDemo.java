package com.example.demo.mybatis.plus.auto.generator.study;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author zhenghao
 * @description 获取路径demo
 * @date 2020/6/11 10:20
 */
public class SystemGetPropertyDemo {
    public static void main(String[] args) throws URISyntaxException {
        /*
        java.version Java运行时环境版本
        java.vendor Java运行时环境供应商
        java.vendor.url Java供应商的 URL
        java.home Java安装目录
        java.vm.specification.version Java虚拟机规范版本
        java.vm.specification.vendor Java虚拟机规范供应商
        java.vm.specification.name Java虚拟机规范名称
        java.vm.version Java虚拟机实现版本
        java.vm.vendor Java虚拟机实现供应商
        java.vm.name Java 虚拟机实现名称
        java.specification.version Java运行时环境规范版本
        java.specification.vendor Java运行时环境规范供应商
        java.specification.name Java运行时环境规范名称
        java.class.version Java类格式版本号
        java.class.path Java类路径
        java.library.path 加载库时搜索的路径列表
        java.io.tmpdir 默认的临时文件路径
        java.compiler 要使用的 JIT 编译器的名称
        java.ext.dirs 一个或多个扩展目录的路径
        os.name 操作系统的名称
        os.arch 操作系统的架构
        os.version 操作系统的版本
        file.separator 文件分隔符（在 UNIX 系统中是“/”）
        path.separator 路径分隔符（在 UNIX 系统中是“:”）
        line.separator 行分隔符（在 UNIX 系统中是“/n”）
        user.name 用户的账户名称
        user.home 用户的主目录
        user.dir 用户的当前工作目录
         */
        System.out.println("java.home : " + System.getProperty("java.home"));
        System.out.println("java.class.version : " + System.getProperty("java.class.version"));
        System.out.println("java.class.path : " + System.getProperty("java.class.path"));
        System.out.println("java.library.path : " + System.getProperty("java.library.path"));
        System.out.println("java.io.tmpdir : " + System.getProperty("java.io.tmpdir"));
        System.out.println("java.compiler : " + System.getProperty("java.compiler"));
        System.out.println("java.ext.dirs : " + System.getProperty("java.ext.dirs"));
        System.out.println("user.name : " + System.getProperty("user.name"));
        System.out.println("user.home : " + System.getProperty("user.home"));
        System.out.println("user.dir : " + System.getProperty("user.dir"));
        System.out.println("os.name: " + System.getProperty("os.name"));  // 操作系统的名称
        System.out.println("os.arch: " + System.getProperty("os.arch"));  // 操作系统的架构

        System.out.println("===================获取包名");
        System.out.println("package: " + SystemGetPropertyDemo.class.getPackage().getName());
        System.out.println("package: " + SystemGetPropertyDemo.class.getPackage().toString());
        System.out.println("=========================获取包名");
        String packName = SystemGetPropertyDemo.class.getPackage().getName();
        URI packUri = new URI(packName);
        System.out.println("包名：" + packUri.getPath());
        // 转义正则特殊字符 （$()*+.[]?\^{} 需要加\\
        System.out.println("包名：" + packName.replaceAll("\\.", "\\\\"));
        System.out.println("当前包绝对路径：" + System.getProperty("user.dir") + File.separator + "模块名\\src\\java\\" + packName.replaceAll("\\.", "\\\\"));

    }

}
