package com.hdc.eduservice.controller;


import com.hdc.eduservice.entity.EduTeacher;
import com.hdc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;
    @GetMapping("findAll")
    public List<EduTeacher> finndAllTeacher(){
        List<EduTeacher> res= teacherService.list(null);
        return res;
    }
}

