package com.example.demo.design_pattern.a_hfdp.l_chain_of_responsibility;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        DateAutoParse dap = new DateAutoParse();
//        dap.setTheNextParseOfDate((moduleCode, number, rule, target) -> {
//            System.out.println("CodeAutoParse：moduleCode=" + moduleCode
//                    + ",number=" + number
//                    + ",rule=" + rule
//                    + ",target=" + Arrays.toString(target));
//            return target;
//        });

        String[] targs = dap.generateCode("module001", 100, "rule22", new String[]{"aaa", "bbb"});
        System.out.println(Arrays.toString(targs));
    }
}
