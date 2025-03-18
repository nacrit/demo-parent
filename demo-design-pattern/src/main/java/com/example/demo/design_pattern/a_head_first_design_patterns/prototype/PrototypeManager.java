package com.example.demo.design_pattern.a_head_first_design_patterns.prototype;


import java.util.HashMap;
import java.util.Map;

// 原型管理器
public class PrototypeManager {
    private static PrototypeManager pm;
    private Map prototypes;

    private PrototypeManager() {
        prototypes = new HashMap();
    }

    //使用单例模式来得到原型管理器的唯一实例
    public static PrototypeManager getManager() {
        if (pm == null) {
            pm = new PrototypeManager();
        }
        return pm;
    }

    public void register(String name, Object prototype) {
        prototypes.put(name, prototype);
    }

    public void unregister(String name) {
        prototypes.remove(name);
    }

    public Prototype getPrototype(String name) {
        if (prototypes.containsKey(name)) {
            //将清单中对应原型的复制品返回给客户
            return (Prototype) ((Prototype) prototypes.get(name)).clone();
        } else {
            Prototype object = null;
            try {
                object = (Prototype) Class.forName(name).newInstance();
                register(name, object);
            } catch (Exception e) {
                System.err.println("Class " + name + "没有定义!");
            }
            return object;
        }
    }
}
