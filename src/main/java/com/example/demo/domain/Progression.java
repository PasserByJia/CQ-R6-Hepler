package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Progression implements Serializable {
    private  Long level;

    private Long lootbox_probability;
}
