package com.lmx.casemgr.entity;

import lombok.Data;

/**
 * 地址信息实体类
 */
@Data
public class AddrInfo {
    /**
     * 省-id
     */
    private String province;
    /**
     * 市-id
     */
    private String municipal;
    /**
     * 县-id
     */
    private String city;
    /**
     * 乡-id
     */
    private String area;
    /**
     * 村-id
     */
    private String village;
}
