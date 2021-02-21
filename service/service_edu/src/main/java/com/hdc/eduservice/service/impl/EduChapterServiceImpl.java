package com.hdc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdc.eduservice.entity.EduChapter;
import com.hdc.eduservice.entity.EduVideo;
import com.hdc.eduservice.entity.chapter.ChapterVo;
import com.hdc.eduservice.entity.chapter.VideoVo;
import com.hdc.eduservice.mapper.EduChapterMapper;
import com.hdc.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdc.eduservice.service.EduVideoService;
import com.hdc.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Service
public class
EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
@Autowired
private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapper);

        QueryWrapper<EduVideo> wrapper2=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapper2);

        List<ChapterVo> finalList =new ArrayList<>();

        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter=eduChapterList.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);
            List<VideoVo> viedioList=new ArrayList<>();
            for (int m = 0; m < eduVideoList.size(); m++) {
                EduVideo eduVideo = eduVideoList.get(m);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    viedioList.add(videoVo);
                }
            }
            chapterVo.setChildren(viedioList);
        }
        return null;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据chapterId 查询小节表 ，如果查询有数据，不进行删除。
        LambdaQueryWrapper<EduVideo> chapterQuery = new LambdaQueryWrapper<>();
        chapterQuery.eq(EduVideo::getChapterId,chapterId);
        int count = eduVideoService.count(chapterQuery);
        if (count>0){
            throw new GuliException(20001,"bunengshanchu!");
        }
        int result = baseMapper.deleteById(chapterId);

        return result>0;
    }

    @Override
    public void deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
