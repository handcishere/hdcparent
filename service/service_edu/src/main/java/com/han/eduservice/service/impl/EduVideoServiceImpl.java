package com.han.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.han.eduservice.client.VodClient;
import com.han.eduservice.entity.EduVideo;
import com.han.eduservice.mapper.EduVideoMapper;
import com.han.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private VodClient vodClient;

    @Override
    public void deleteVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapperVideo =new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        List<String> videoIds=new ArrayList<>();
//        eduVideoList.forEach(a->{
//            videoIds.add(a.getVideoSourceId());
//        });
        for (int i = 0; i < eduVideoList.size(); i++) {
            String videoId=eduVideoList.get(i).getVideoSourceId();
            if(!StringUtils.isEmpty(videoId))
                videoIds.add(videoId);
        }
        if(videoIds.size()>0)
            vodClient.deleteBatch(videoIds);
        QueryWrapper<EduVideo> wrapper =new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
