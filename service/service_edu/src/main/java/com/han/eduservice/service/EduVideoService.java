package com.han.eduservice.service;

import com.han.eduservice.entity.EduVideo;
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
