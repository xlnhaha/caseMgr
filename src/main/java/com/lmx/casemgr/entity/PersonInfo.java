package com.lmx.casemgr.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 患者基本信息
 */
@Data
public class PersonInfo {
    /**
     * id
     */
    private int id;
    /**
     * 患者姓名
     */
    private String name;
    /**
     * 年龄
     */
    private String age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 所属地
     */
    private AddrInfo addr;
    /**
     * 详细地址
     */
    private String paddr;
    /**
     * 患病类别
     */
    private String painClass;
    /**
     * 患病时间
     */
    private String painLong;
    /**
     * 治疗次数
     */
    private int treatTimes;
    /**
     * 治疗状态
     */
    private int treatStatus;

    /**
     * 治疗病例
     */
    private CaseInfo caseInfo;

    /**
     * 最近一次治疗时间
     */
    private Timestamp lastTreat;

}
