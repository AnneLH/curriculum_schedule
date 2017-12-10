package cn.vaf714.curriculum_schedule.bean;

import cn.vaf714.curriculum_schedule.entity.Course;
import cn.vaf714.curriculum_schedule.entity.Schedule;

/**
 * Created by Acer on 2017/11/19.
 */

public class CourseVo {
    private Course course;
    private Schedule schedule;

    public CourseVo(Course course, Schedule schedule) {
        this.course = course;
        this.schedule = schedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
