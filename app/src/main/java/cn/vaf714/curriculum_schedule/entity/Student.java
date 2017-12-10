package cn.vaf714.curriculum_schedule.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Acer on 2017/11/18.
 */

public class Student extends DataSupport {
    private int id;
    private String no;
    private String studentName;
    private String school;
    private String dept;
    private String major;
    private String atClass;//所在班级

    public List<Course> getCoursesFromDB() {
        return DataSupport.where("student_id=?", String.valueOf(id)).find(Course.class);
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", studentName='" + studentName + '\'' +
                ", school='" + school + '\'' +
                ", dept='" + dept + '\'' +
                ", major='" + major + '\'' +
                ", atClass='" + atClass + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAtClass() {
        return atClass;
    }

    public void setAtClass(String atClass) {
        this.atClass = atClass;
    }
}
