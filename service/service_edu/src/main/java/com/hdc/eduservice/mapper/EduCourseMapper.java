package com.hdc.eduservice.mapper;

import com.hdc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public com.guli.edu.vo.CoursePublishVo getPublishCourseInfo(String courseId);
}
