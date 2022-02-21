package com.acrt.example.demos.pring5.k_jdbc_template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String bName;
    private String bVersion;
}
