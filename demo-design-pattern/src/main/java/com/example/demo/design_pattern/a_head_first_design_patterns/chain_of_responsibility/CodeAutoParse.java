package com.example.demo.design_pattern.a_head_first_design_patterns.chain_of_responsibility;

//这是抽象处理者角色
public interface CodeAutoParse {
    //这里就是统一的处理请求使用的接口，代号自动生成器，参数：模块代码、代号、规则、目标
    String[] generateCode(String moduleCode, int number, String rule, String[] target) throws Exception;
}
