package com.qy25.sm.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {

    private List<T> list;
    private long total;

}
