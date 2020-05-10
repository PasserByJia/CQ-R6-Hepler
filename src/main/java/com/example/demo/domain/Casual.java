package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 休闲
 */
@Data
public class Casual implements Serializable {
    private Long deaths;
    private Long kills;
    private Double kd;
    private Long losses;
    private Long wins;
    private Double wl;
}
