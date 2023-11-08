package com.example.demo.mistakes.demo31;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhenghao
 * @description java8简化代码Demo
 * @date 2020/6/17 10:41
 */
public class SimpleCodeDemo {

    public static void main(String[] args) {
        demo1();
    }

    /**
     * 使用 Stream 简化集合操作
     * 实现如下操作
     *  把整数列表转换为 Point2D 列表；
     *  遍历 Point2D 列表过滤出 Y 轴 >1 的对象；
     *  计算 Point2D 点到原点的距离；累加所有计算出的距离，并计算距离的平均值。
     */
    public static void demo1() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        ints = new ArrayList<>();
        // 普通方式实现
        double calc = calc(ints);
        System.out.println(calc);

        // 使用lambda表达式简化
        double avg = ints.stream()
                .map(x -> new Point2D.Double((double) x % 3, (double) x / 3))
                .filter(e -> e.getY() > 1)
                .mapToDouble(e -> e.distance(0, 0))
                .average()
                .orElse(0);
        System.out.println(avg);
    }


    // 使用普通逻辑实现
    private static double calc(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count > 0 ? total / count : 0;
    }

}
