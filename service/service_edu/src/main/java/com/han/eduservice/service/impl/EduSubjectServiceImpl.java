package com.han.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.han.eduservice.entity.EduSubject;
import com.han.eduservice.entity.excel.SubjectData;
import com.han.eduservice.entity.subject.OneSubject;
import com.han.eduservice.entity.subject.TwoSubject;
import com.han.eduservice.listener.SubjectExcellListener;
import com.han.eduservice.mapper.EduSubjectMapper;
import com.han.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcellListener(subjectService)).sheet().doRead();
        }catch (Exception e){

        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> listOne = this.list(wrapperOne);

        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> listTwo = this.list(wrapperTwo);

        List<OneSubject> finalSubjectList =new ArrayList<>();


        for (int i = 0; i < listOne.size(); i++) {
            EduSubject eduSubject=listOne.get(i);
            OneSubject oneSubject=new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            List<TwoSubject> twoFinalSubjects =new ArrayList<>();
            for (int m = 0; m < listTwo.size(); m++) {
                EduSubject  tSubject=listTwo.get(m);
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjects);
        }

        return finalSubjectList;
    }
}
