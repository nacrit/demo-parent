package com.example.demo.mistakes.demo21.reflection;

import com.example.demo.mistakes.demo21.reflection.ordinary.BankService;
import com.example.demo.mistakes.demo21.reflection.right.BetterBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * <p>
 * ReflectionController
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:38
 */
@Slf4j
@RestController
@RequestMapping("/reflection")
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

    @GetMapping("/wrong")
    public void wrong() throws IOException {
        String result1 = BankService.createUser("zhuye", "xxxxxxxxxxxxxxxxxx", "13612345678", 36);
        log.info("right result:{}", result1);
        String result2 = BankService.pay(1234L, new BigDecimal("100.5"));
        log.info("right result:{}", result2);
    }

    @GetMapping("/right")
    public void right() throws IOException {
        String result1 = BetterBankService.createUser("zhuye", "xxxxxxxxxxxxxxxxxx", "13612345678", 36);
        log.info("right result:{}", result1);
        String result2 = BetterBankService.pay(1234L, new BigDecimal("100.5"));
        log.info("right result:{}", result2);
    }
}
