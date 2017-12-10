package cn.vaf714.curriculum_schedule.dao;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.vaf714.curriculum_schedule.entity.Course;
import cn.vaf714.curriculum_schedule.entity.Schedule;

/**
 * Created by Acer on 2017/11/19.
 */

public class CourseDao {
    /**
     * 查找相同的课程
     * @param course
     * @return
     */
    public Course findTheSameCourseByCourse(Course course) {
        Course theSameCourseFromDB = DataSupport
                .where("coursename=? and startweek=? and endweek=? and teacher=?", course.getCourseName(), String.valueOf(course.getStartWeek()), String.valueOf(course.getEndWeek()), course.getTeacher())
                .findFirst(Course.class);
        return theSameCourseFromDB;
    }

    /**
     * 查询所有课程
     * @return
     */
    public List<Course> findAllCourse() {
        return DataSupport.findAll(Course.class);
    }

    public void deleteById(int id) {
        DataSupport.delete(Course.class, id);
    }

    public void clear(){
        DataSupport.deleteAll(Course.class, "1=1");
    }
}
