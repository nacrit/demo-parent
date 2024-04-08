package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Src20BtcBlock {
    private Long block_index; // 区块高度
    private String block_hash; // 区块hash
    private Date block_time; // 区块时间
    private String previous_block_hash;
    private Long difficulty;
    private String ledger_hash;
    private String txlist_hash;
    private String messages_hash;
    private int indexed;
    private int tx_count;
}