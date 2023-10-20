package com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction;

import com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction.service_right.BetterBankService;
import com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction.service_wrong.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("reflection")
public class ReflectionController {

    @PostMapping("/bank/createUser")
    public String createUser(@RequestBody String data) {
        log.info("createUser called with argument {}", data);
        return "1";
    }

    @PostMapping("/bank/pay")
    public String pay(@RequestBody String data) {
        log.info("pay called with argument {}", data);
        return "OK";
    }

    @GetMapping("wrong")
    public void wrong() throws IOException {
        String res = BankService.createUser("张三", "xxxxxxxxxxxxxxxxxx", "18812312345", 36);
        String res2 = BankService.pay(1234L, new BigDecimal("100.5"));
        log.info("res:{}", res);
        log.info("res2:{}", res2);
    }

    @GetMapping("right")
    public void right() throws IOException {
        String res = BetterBankService.createUser("张三", "xxxxxxxxxxxxxxxxxx", "18812312345", 36);
        String res2 = BetterBankService.pay(1234L, new BigDecimal("100.5"));
        log.info("res:{}", res);
        log.info("res2:{}", res2);
    }
}
