package com.hdc.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdc.eduservice.entity.EduChapter;
import com.hdc.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface EduVideoService extends IService<EduVideo> {
    void deleteVideoByCourseId(String courseId);
}
