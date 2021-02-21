package com.hdc.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdc.eduservice.entity.EduSubject;
import com.hdc.eduservice.entity.excel.SubjectData;
import com.hdc.eduservice.service.EduSubjectService;
import com.hdc.servicebase.exception.GuliException;

import java.util.Map;

public class SubjectExcellListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService eduSubjectService;

    public SubjectExcellListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcellListener() {
    }

    @Override
    public void invoke(SubjectData user, AnalysisContext analysisContext) {
        if(user == null) {
            throw new GuliException(20001,"添加失败");
        }
        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService,user.getOneSubjectName());
        if(existOneSubject == null) {//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(user.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService,user.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(user.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

    //判断一级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
}
