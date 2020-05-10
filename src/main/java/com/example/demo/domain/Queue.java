package com.example.demo.domain;

import lombok.Data;


import java.io.Serializable;
@Data
public class Queue implements Serializable {

    private Casual casual;

    private Ranked ranked;

}
