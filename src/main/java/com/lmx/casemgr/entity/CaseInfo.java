package com.lmx.casemgr.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CaseInfo {

    /**
     * id
     */
    private int id;
    /**
     * 所属人id
     */
    private int  personId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 疼痛-正面 blob
     */
    private String frontImg;
    /**
     * 疼痛-背面 blob
     */
    private String backImg;
    /**
     * 疼痛-侧面 blob
     */
    private String sideImg;

    /**
     * 主诉
     */
    private String zhusu;

    /**
     * 现病史
     */
    private String xbs;

    /**
     * 查体
     */
    private String chati;

    /**
     * 诊断
     */
    private String zhenduan;

    /**
     * 治疗
     */
    private String treatLog;
}
