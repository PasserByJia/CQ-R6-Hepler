package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Stats implements Serializable {

    private General general;

    private Queue queue;
}
