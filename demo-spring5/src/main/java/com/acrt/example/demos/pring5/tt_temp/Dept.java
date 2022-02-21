package com.acrt.example.demos.pring5.tt_temp;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 16:47
 */
public class Dept {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
