package cn.vaf714.curriculum_schedule.dao;

import org.litepal.crud.DataSupport;

import cn.vaf714.curriculum_schedule.entity.Student;

/**
 * Created by Acer on 2017/11/20.
 */

public class StudentDao {
    public void clear(){
        DataSupport.deleteAll(Student.class, "1=1");
    }
    public Student getStudent(){
        return DataSupport.findFirst(Student.class);
    }
}
