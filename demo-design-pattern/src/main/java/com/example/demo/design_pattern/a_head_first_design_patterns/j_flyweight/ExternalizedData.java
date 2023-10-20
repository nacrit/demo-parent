package com.example.demo.design_pattern.a_head_first_design_patterns.j_flyweight;

//这便是使用了静态属性来达到共享
//它使用了数组来存放不同客户对象要求的属性值
//它相当于享元角色（抽象角色被省略了）
public class ExternalizedData {
    static final int size = 5000000;
    static int[] id = new int[size];
    static int[] i = new int[size];
    static float[] f = new float[size];
    static {
        for (int i = 0; i < size; i++)
            id[i] = i;
    }
}
