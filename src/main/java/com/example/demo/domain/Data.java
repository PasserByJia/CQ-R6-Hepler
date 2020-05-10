package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@lombok.Data
public class Data implements Serializable {

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date last_updated;

    private Progression progression;

    private List<Stats> stats;



}
