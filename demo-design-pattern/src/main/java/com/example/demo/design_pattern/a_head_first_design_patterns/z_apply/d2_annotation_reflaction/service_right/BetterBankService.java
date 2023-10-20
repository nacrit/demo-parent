package com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction.service_right;

import com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction.DigestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Slf4j
public class BetterBankService {

    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        createUserAPI.setMobile(mobile);
        return remoteCall(createUserAPI);
    }

    public static String pay(long userId, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setUserId(userId);
        payAPI.setAmount(amount);
        return remoteCall(payAPI);
    }

    /**
     * 所有处理参数排序、填充、加签、请求调用的核心逻辑，都汇聚在了 remoteCall 方法中
     */
    private static String remoteCall(AbstractAPI api) throws IOException {
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        String url = bankAPI.url();
        String param = Arrays.stream(api.getClass().getDeclaredFields())
                .sorted(Comparator.comparingInt(f -> f.getAnnotation(BankAPIField.class).order()))
                .map(f -> {
            try {
                f.setAccessible(true);
                BankAPIField bankAPIField = f.getAnnotation(BankAPIField.class);
                return bankAPIField.bankAPIFieldType().format(f.get(api), bankAPIField);
            } catch (IllegalAccessException e) {
                log.info("get {} 的 {} exception", api, f);
            }
            return "";
        }).collect(Collectors.joining());
        param = param + DigestUtils.md5(param);
//        return "http://localhost:45678/reflection" +
//                url + "?" + param;
        long begin = System.currentTimeMillis();
        //发请求
        String result = Request.Post("http://localhost:8080/reflection" + bankAPI.url())
                .bodyString(param, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms",
                bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return result;
    }

}
