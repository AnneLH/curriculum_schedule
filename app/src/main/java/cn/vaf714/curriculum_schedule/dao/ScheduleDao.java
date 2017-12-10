package cn.vaf714.curriculum_schedule.dao;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.vaf714.curriculum_schedule.entity.Schedule;
import cn.vaf714.curriculum_schedule.entity.Student;

/**
 * Created by Acer on 2017/11/19.
 */

public class ScheduleDao {
    public List<Schedule> findScheduleByWeekAndSections(int week, int sections) {
        List<Schedule> schedulesFromDB = DataSupport
                .where("week=? and sections=?", String.valueOf(week), String.valueOf(sections))
                .find(Schedule.class, true);
        return schedulesFromDB;
    }

    public void clear(){
        DataSupport.deleteAll(Schedule.class, "1=1");
    }
}
