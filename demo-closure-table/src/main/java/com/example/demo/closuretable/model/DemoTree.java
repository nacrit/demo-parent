package com.example.demo.closuretable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoTree {
    private Integer id;
    private Integer ancestor;
    private Integer descendant;
    private Integer depth;

    public DemoTree(Integer ancestor, Integer descendant) {
        this.ancestor = ancestor;
        this.descendant = descendant;
    }
}