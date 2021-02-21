package com.hdc.eduservice.controller;


import com.hdc.commonutils.R;
import com.hdc.eduservice.entity.EduCourse;
import com.hdc.eduservice.entity.vo.CourseInfoVo;
import com.hdc.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @GetMapping
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id=courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoForm){
        courseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }
    @ApiOperation("根据课程id查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        com.guli.edu.vo.CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);

        return R.ok().data("publishCourse",coursePublishVo);
    }

    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }
    @ApiOperation("删除课程信息")
    @DeleteMapping("/{courseId}")
    public R deleteCourse(@PathVariable String courseId){

        //TODO 前端实现
        courseService.deleteCourse(courseId);
        return R.ok();
    }
}

