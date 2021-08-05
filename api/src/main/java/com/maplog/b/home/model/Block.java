package com.maplog.b.home.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Block {
    Long id;
    int x;
    int y;
    String color;
    Timestamp creaTime;
    Timestamp updtTime;
    Long userId;
}
