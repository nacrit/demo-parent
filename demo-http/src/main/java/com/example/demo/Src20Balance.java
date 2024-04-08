package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Src20Balance {
    private int page;
    private int limit;
    private int totalPages;
    private int total;
    private BtcBalance btc;
    private Src20Data data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BtcBalance {
        private String address;
        private BigDecimal balance;
        private int txCount;
        private int unconfirmedBalance;
        private int unconfirmedTxCount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Src20Data {
        //        private List<String> stamps;
        private List<Src20> src20;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Src20 {
        private String id;
        private String address;
        private String p;
        private String tick;
        private BigDecimal amt;
        private Date block_time;
        private Long last_update;
    }
}