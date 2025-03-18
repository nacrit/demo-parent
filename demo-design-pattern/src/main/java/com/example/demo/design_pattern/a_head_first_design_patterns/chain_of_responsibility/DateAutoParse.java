package com.example.demo.design_pattern.a_head_first_design_patterns.chain_of_responsibility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//这个为处理日期使用的具体处理者
public class DateAutoParse {
    //获取当前时间
    private final Calendar currentDate = Calendar.getInstance();
    //这里用来注入下一个处理者，系统中采用 Spring Bean 管理
    private CodeAutoParse theNextParseOfDate = null;

    public void setTheNextParseOfDate(CodeAutoParse theNextParseOfDate) {
        this.theNextParseOfDate = theNextParseOfDate;
    }

    // 实现的处理请求的接口这个接口首先判断用户定义的格式是否有流水号,有则解析,没有则跳过下传到下一个处理者
    public String[] generateCode(String moduleCode, int number, String rule, String[] target) throws Exception {
        // 业务处理
        if (target != null) {
            for (int i = 0; i < target.length; i++) {
                target[i] = target[i] + "->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate.getTime());
            }
        }
        System.out.println("执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate.getTime()));
        if (theNextParseOfDate != null) {
            return theNextParseOfDate.generateCode(moduleCode, number, rule, target);
        } else {
            return target;
        }
    }
}
