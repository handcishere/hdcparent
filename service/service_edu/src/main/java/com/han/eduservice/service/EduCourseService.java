package com.han.eduservice.service;

import com.han.eduservice.entity.EduCourse;
import com.han.eduservice.entity.vo.CourseInfoVo;
import com.han.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

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

    CoursePublishVo publishCourseInfo(String id);

    void deleteCourse(String courseId);
}
