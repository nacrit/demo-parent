package com.example.demo.design_pattern.a_head_first_design_patterns.d_prototype;

public class Main {

    public static void main(String[] args) {
        String className = ConcretePrototype.class.getName();
        Prototype p = PrototypeManager.getManager().getPrototype(className);
        System.out.println(p);
        System.out.println(PrototypeManager.getManager().getPrototype(className));
        // 解绑
        PrototypeManager.getManager().unregister(className);
        System.out.println(PrototypeManager.getManager().getPrototype(className));
        // 注册
        PrototypeManager.getManager().register(className, p);
        System.out.println(PrototypeManager.getManager().getPrototype(className));
        PrototypeManager.getManager().register(className, new ConcretePrototype());
        System.out.println(PrototypeManager.getManager().getPrototype(className));
    }
}
