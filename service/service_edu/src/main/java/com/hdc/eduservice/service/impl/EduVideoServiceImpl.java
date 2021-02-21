package com.hdc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdc.eduservice.entity.EduChapter;
import com.hdc.eduservice.entity.EduVideo;
import com.hdc.eduservice.mapper.EduVideoMapper;
import com.hdc.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    @Override
    public void deleteVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper =new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
