package cn.vaf714.curriculum_schedule.entity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/11/17.
 */

public class Course extends DataSupport {
    private int id;
    private String no;//课程号
    private int term;
    private String courseName;
    private String teacher;
    private int startWeek;
    private int endWeek;
    private int courseColor;//课程颜色
    private List<Schedule> schedules = new ArrayList<>();

    public List<Schedule> getSchedulesFromDB() {
        return DataSupport.where("course_id=?", String.valueOf(id)).find(Schedule.class);
    }

    public Course() {

    }

    public Course(String courseName, String teacher, int startWeek, int endWeek, int courseColor) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.courseColor = courseColor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", term=" + term +
                ", courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", courseColor=" + courseColor +
                ", schedules=" + schedules +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getCourseColor() {
        return courseColor;
    }

    public void setCourseColor(int courseColor) {
        this.courseColor = courseColor;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
