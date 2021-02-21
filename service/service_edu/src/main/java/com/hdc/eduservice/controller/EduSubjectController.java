package com.hdc.eduservice.controller;


import com.hdc.commonutils.R;
import com.hdc.eduservice.entity.subject.OneSubject;
import com.hdc.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    //课程分类（tree）
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> subjectList=subjectService.getAllOneTwoSubject();
        return R.ok().data("list",subjectList);
    }
}

