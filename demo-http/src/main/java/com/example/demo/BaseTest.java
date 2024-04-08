package com.example.demo;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mars
 * @description BaseTest
 * @date 2024/4/8 17:48
 */
public class BaseTest {

    public static void main(String[] args) throws IOException {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS) // 连接超时10s
                        .readTimeout(300, TimeUnit.SECONDS) // 读取超时300s
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build());
        String baseUrl = "https://stampchain.io";
        String address = "bc1qndwhntf80jv90kkkgvs67vp48hhpxeetrk9f5m";
        DemoApi demoApi = builder.baseUrl(baseUrl).build().create(DemoApi.class);
        Src20Balance res = demoApi.getBalance(address).execute().body();
        System.out.println("res = " + res);

        List<Src20BtcBlock> blocks = demoApi.getLastBlocks(1).execute().body();
        System.out.println("blocks = " + blocks);
    }

}
