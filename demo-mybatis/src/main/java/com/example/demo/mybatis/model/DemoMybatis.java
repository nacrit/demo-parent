package com.example.demo.mybatis.model;

public class DemoMybatis {
    private Integer id;

    private String name;

    public DemoMybatis(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DemoMybatis() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "DemoMybatis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}