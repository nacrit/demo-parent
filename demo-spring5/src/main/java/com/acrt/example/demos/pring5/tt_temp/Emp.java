package com.acrt.example.demos.pring5.tt_temp;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 16:47
 */
public class Emp {
    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }
}
