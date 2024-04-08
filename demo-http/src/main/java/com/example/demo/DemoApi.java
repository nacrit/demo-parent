package com.example.demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;


/**
 * @author mars
 * @description StampChainApi
 * @date 2024/1/26 11:53
 */
public interface DemoApi {
    // 查询余额，包括btc、代币余额
    @GET("/api/v2/balance/{address}")
    Call<Src20Balance> getBalance(@Path("address") String address);


    // 最新的number个区块
    @GET("/api/v2/block/block_count/{number}")
    Call<List<Src20BtcBlock>> getLastBlocks(@Path("number") int number);
}
