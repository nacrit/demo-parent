package com.example.demo.design_pattern.a_head_first_design_patterns.flyweight;

//这个类仅仅是为了给 ExternalizedData 的静态属性赋值、取值
//这个充当享元工厂角色
public class FlyPoint {
    private FlyPoint() {}
    public static int getId(int obnum) {
        return ExternalizedData.id[obnum];
    }
    public static int getI(int obnum) {
        return ExternalizedData.i[obnum];
    }
    public static void setI(int obnum, int i) {
        ExternalizedData.i[obnum] = i;
    }
    public static float getF(int obnum) {
        return ExternalizedData.f[obnum];
    }
    public static void setF(int obnum, float f) {
        ExternalizedData.f[obnum] = f;
    }
    public static String str(int obnum) {
        return "id: " + getId(obnum) +
                ", i = " + getI(obnum) +
                ", f = " + getF(obnum);
    }
}