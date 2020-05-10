package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 全部
 */
@Data
public class General implements Serializable {
    private Long deaths;
    private Long kills;
    private Double kd;
    private Long losses;
    private Long wins;
    private Double wl;
    //致盲击杀
    private Long blind_kills;
    //近战击杀
    private Long melee_kills;
    //穿透击杀
    private Long penetration_kills;

}
