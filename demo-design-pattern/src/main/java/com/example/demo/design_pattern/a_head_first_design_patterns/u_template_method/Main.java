package com.example.demo.design_pattern.a_head_first_design_patterns.u_template_method;

public class Main {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
