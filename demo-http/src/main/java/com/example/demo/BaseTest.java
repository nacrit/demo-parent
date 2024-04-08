package com.example.demo;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * @author mars
 * @description BaseTest
 * @date 2024/4/8 17:48
 */
public class BaseTest {

    public static void main(String[] args) throws IOException {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create());
        String baseUrl = "https://stampchain.io";
        String address = "bc1qndwhntf80jv90kkkgvs67vp48hhpxeetrk9f5m";
        DemoApi demoApi = builder.baseUrl(baseUrl).build().create(DemoApi.class);
        Src20Balance res = demoApi.getBalance(address).execute().body();
        System.out.println("res = " + res);
    }

}
