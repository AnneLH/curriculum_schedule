package cn.vaf714.curriculum_schedule.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Acer on 2017/11/18.
 */

public class Schedule extends DataSupport {
    private int id;
    private Course course;
    private int whichWeek;//单双周，0-全，1-单周，2-双周
    private int week;//周几
    private int sections;//节次
    private String classroom;

    public Schedule() {
    }

    public Schedule(String classroom, int whichWeek, int week, int sections) {
        this.classroom = classroom;
        this.whichWeek = whichWeek;
        this.week = week;
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", course=" + course +
                ", whichWeek=" + whichWeek +
                ", week=" + week +
                ", sections=" + sections +
                ", classroom='" + classroom + '\'' +
                '}';
    }

    public int getWhichWeek() {
        return whichWeek;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setWhichWeek(int whichWeek) {
        this.whichWeek = whichWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSections() {
        return sections;
    }

    public void setSections(int sections) {
        this.sections = sections;
    }
}
