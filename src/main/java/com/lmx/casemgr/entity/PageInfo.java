package com.lmx.casemgr.entity;

import lombok.Data;

@Data
public class PageInfo {
    private int total;
    private int pageSize;
    private int currentPage;
}
