package cn.vaf714.curriculum_schedule.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具：将控件中的字符规范化为系统需要的数字
 * Created by Acer on 2017/11/18.
 */
public class ConvertUtils {
    public static Map<String, Integer> convertEnum = new HashMap<>();
    static {
        convertEnum.put("周一", 1);
        convertEnum.put("周二", 2);
        convertEnum.put("周三", 3);
        convertEnum.put("周四", 4);
        convertEnum.put("周五", 5);
        convertEnum.put("周六", 6);
        convertEnum.put("周日", 7);
        convertEnum.put("1-2节", 1);
        convertEnum.put("3-4节", 3);
        convertEnum.put("5-6节", 5);
        convertEnum.put("7-8节", 7);
        convertEnum.put("9-10节", 9);
        convertEnum.put("单周", 1);
        convertEnum.put("双周", 2);
        convertEnum.put("全周", 0);
    }

    /**
     * 获取对应的数字
     * @param key
     * @return
     */
    public static int convert(String key) {
        return convertEnum.get(key);
    }
}
