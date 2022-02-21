package com.example.demo.mybatis.plus.auto.generator.study;

import java.io.*;

/**
 * @author zhenghao
 * @description try with resources用法
 * @date 2020/6/11 15:26
 */
public class TryCatchResourcesDemo {

    public static void main(String[] args) {
        /*
        这个try-with-resources的用法格式为：
        try(这里面的资源会自动关闭，前提是这些资源必须实现了Closeable接口或者AutoCloseable接口){
            //这里面是你的其他代码
        } catch(捕获的异常){
            //打印异常信息
        }
         */
        // 以前用法 -----------------------------------
        File file = null;
        FileReader fr = null;
        try {
            file = new File("D://abc.txt");
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // try catch resources
        try (FileReader fr2 = new FileReader("D://abc.txt");
             BufferedReader br2 = new BufferedReader(fr2)) {
            //对文件的操作
            String line = br2.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
