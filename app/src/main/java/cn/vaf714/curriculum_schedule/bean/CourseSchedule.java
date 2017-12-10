package cn.vaf714.curriculum_schedule.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.vaf714.curriculum_schedule.dao.CourseDao;
import cn.vaf714.curriculum_schedule.dao.ScheduleDao;
import cn.vaf714.curriculum_schedule.entity.Course;
import cn.vaf714.curriculum_schedule.entity.Schedule;

/**
 * Created by Acer on 2017/11/18.
 */

public class CourseSchedule {

    /**
     * 内部维护一个课程map
     * k:周_始节次,eg:1-5 代表周一5、6节
     * v:课程
     */
    private Map<String, CourseVo> courseMap = new HashMap<>();
    private CourseDao courseDao = new CourseDao();
    private ScheduleDao scheduleDao = new ScheduleDao();

    /**
     * 重置
     */
    private void reset() {
        courseMap.clear();
    }

    /**
     * 添加课程
     *
     * @param course
     */
    public void addCourse(Course course) {
        if (course == null || course.getSchedules().size() == 0) {
            return;
        }
        List<Schedule> schedules = course.getSchedules();

        //先判断添加的课程是否已经添加过
        Course theSameCourseFromDB = courseDao.findTheSameCourseByCourse(course);
        if (theSameCourseFromDB != null) {
            //如果之前添加过，修改课程时间信息里的课程指向数据库中的课程记录
            for (Schedule s : schedules) {
                s.setCourse(theSameCourseFromDB);
            }
        } else {
            //如果之前没有添加，则存储课程信息
            course.save();
        }

        //存储课程时间
        for (Schedule s : schedules) {
            //如果重复添加某个时间段的课程则删除原来的
            distinctCourseTime(s);
            s.save();
        }
    }

    private void distinctCourseTime(Schedule schedule) {
        //查找相同时间段的已有课程
        List<Schedule> schedulesFromDB = scheduleDao.findScheduleByWeekAndSections(schedule.getWeek(), schedule.getSections());
        if (schedulesFromDB == null) {
            return;
        }
        for (Schedule s : schedulesFromDB) {
            if ((s.getCourse().getStartWeek() > schedule.getCourse().getEndWeek()) || (s.getCourse().getEndWeek() < schedule.getCourse().getStartWeek())) {
                //虽然时间段相同，但是起始周没有重叠，可以同时存在，不用做处理
                continue;
            }
            if (schedule.getWhichWeek() * s.getWhichWeek() == 2) {
                //虽然时间段相同，并且其实周也有重叠，但是两个课程单双周相乘为2，即没有任何一个是全周且一个单周，一个双周，那么他们可以同时存在，不用处理
                continue;
            }
            //其余情况都是有冲突的情况，需要删除原来的课程计划
            s.delete();
        }

    }

    /**
     * 更新课程表
     */
    public void updateCourse(int week) {
        reset();
        //查询所有课程
        List<Course> courses = courseDao.findAllCourse();
        if (courses.size() == 0) {
            return;
        }
        for (Course course : courses) {
            //得到该课程的授课计划
            List<Schedule> schedules = course.getSchedulesFromDB();
            if (schedules.size() == 0){
                //我i授课计划可删除该课程
                courseDao.deleteById(course.getId());
                continue;
            }
            for (Schedule schedule : schedules) {
                //过滤单双周和起始周范围
                if (week % 2 == 0 && schedule.getWhichWeek() == 1) {
                    continue;
                } else if (week % 2 == 1 && schedule.getWhichWeek() == 2) {
                    continue;
                } else if (week > course.getEndWeek() || week < course.getStartWeek()) {
                    continue;
                }
                String time = schedule.getWeek() + "_" + schedule.getSections();

                courseMap.put(time, new CourseVo(course, schedule));
            }
        }

    }

    public Map<String, CourseVo> getCourseMap() {
        return courseMap;
    }

    public void clearSchedule(){
        courseDao.clear();
        scheduleDao.clear();
    }

}
