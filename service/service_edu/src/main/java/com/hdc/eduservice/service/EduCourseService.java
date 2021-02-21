package com.hdc.eduservice.service;

import com.hdc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hdc.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoForm);

    com.guli.edu.vo.CoursePublishVo publishCourseInfo(String id);

    void deleteCourse(String courseId);
}
