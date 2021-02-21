package com.hdc.eduservice.service.impl;

import com.guli.edu.vo.CoursePublishVo;
import com.hdc.eduservice.entity.EduCourse;
import com.hdc.eduservice.entity.EduCourseDescription;
import com.hdc.eduservice.entity.vo.CourseInfoVo;
import com.hdc.eduservice.mapper.EduCourseMapper;
import com.hdc.eduservice.service.EduChapterService;
import com.hdc.eduservice.service.EduCourseDescriptionService;
import com.hdc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdc.eduservice.service.EduVideoService;
import com.hdc.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert<=0){
            throw new GuliException(20001,"添加课程失败！");
        }
        String cid =eduCourse.getId();

        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程信息
        EduCourse course = this.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        BeanUtils.copyProperties(course,courseInfoVo);

        //2.查询课程描述信息
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);
        int updateResult = baseMapper.updateById(course);
        if(updateResult==0)
            throw new GuliException(200001,"修改失败！");
        //2.修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        boolean updateDescResult = courseDescriptionService.updateById(description);

    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id 课程id
     * @return CoursePublishVo
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {

        CoursePublishVo publishCourseInfo = this.baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void deleteCourse(String courseId) {
        //1.根据课程id删除小节
        eduVideoService.deleteVideoByCourseId(courseId);

        //2.根据课程id删除章节
        eduChapterService.deleteChapterByCourseId(courseId);

        //3.根据课程id删除描述
        boolean removeDesc = courseDescriptionService.removeById(courseId);


        //4.根据课程id删除课程本身
        boolean removeCourseResult = this.removeById(courseId);

        if(!removeCourseResult){
            throw new GuliException(20001,"删除失败！");
        }
    }
}
