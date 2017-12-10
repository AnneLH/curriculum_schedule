package cn.vaf714.curriculum_schedule.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.vaf714.curriculum_schedule.bean.Setting;

/**
 * Created by Acer on 2017/11/18.
 */

public class DateUtils {
    /**
     * 计算当前周
     *
     * @return
     */
    public static int getCurrWeek() {
        try {
            //TODO 不选择周一会计算错误
            long now = new Date().getTime();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Setting.getStartDate());
            long start = date.getTime();
            int count = Integer.parseInt(String.valueOf(((now - start) / (1000 * 3600 * 24 * 7))));
            int remain = Integer.parseInt(String.valueOf(((now - start) % (1000 * 3600 * 24 * 7))));
            if (remain > 0) {
                count++;
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
