package com.lmx.casemgr.utils;

import java.util.HashMap;
import java.util.Map;

public class DicsUtil {
    public static Map<String, String> painClassMap = null;
    public static Map<String, String> painLongMap = null;
    public static Map<String, String> locationMap = null;

    static {
        painClassMap = new HashMap<>();
        painClassMap.put("1", "腰椎");
        painClassMap.put("2", "颈椎");
        painClassMap.put("3", "膝关节弯曲");
        painClassMap.put("4", "强直性脊柱炎");
        painClassMap.put("5", "类风湿");

        painLongMap = new HashMap<>();
        painLongMap.put("1", "1-3年");
        painLongMap.put("2", "3-5年");
        painLongMap.put("3", "5-10年");
        painLongMap.put("4", "10年以上");

        locationMap = new HashMap<>();
        locationMap.put("shannxi", "陕西省");
        locationMap.put("xian", "西安市");
        locationMap.put("weinan", "渭南市");
        locationMap.put("baoji", "宝鸡市");
        locationMap.put("xianyang", "咸阳市");
        locationMap.put("tongchuan", "铜川市");
        locationMap.put("yanan", "延安市");
        locationMap.put("yulin", "榆林市");
        locationMap.put("hanzhong", "汉中市");
        locationMap.put("ankang", "安康市");
        locationMap.put("linwei", "临渭区");
        locationMap.put("pucheng", "蒲城县");
        locationMap.put("huangling", "黄陵县");
        locationMap.put("baishui", "白水县");
        locationMap.put("chengguan", "城关街道");
        locationMap.put("leiya", "雷牙镇");
        locationMap.put("fenglei", "冯雷镇");
        locationMap.put("yaohe", "尧禾镇");
        locationMap.put("dukang", "杜康镇");
        locationMap.put("xigu", "西固镇");
        locationMap.put("lingao", "林皋镇");
        locationMap.put("shiguan", "史官镇");
        locationMap.put("yuntai", "云台镇");
        locationMap.put("beiyuan", "北塬镇");
        locationMap.put("huangling", "黄陵县");
        locationMap.put("huanglong", "黄龙县");
        locationMap.put("luochuan", "洛川县");
        locationMap.put("yichuan", "宜川县");
    }
}
