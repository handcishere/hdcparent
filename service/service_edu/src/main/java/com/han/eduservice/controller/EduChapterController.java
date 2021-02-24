package com.han.eduservice.controller;


import com.han.commonutils.R;
import com.han.eduservice.entity.EduChapter;
import com.han.eduservice.entity.chapter.ChapterVo;
import com.han.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    @ApiOperation("添加章节")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
//        Chapter chapter = new Chapter();
//        BeanUtils.copyProperties(eduChapter, chapter);
//        boolean saveResult = chapterService.save(eduChapter);
//        if (!saveResult) {
//            throw GuliException.from(EduResultCode.SAVE_ERROR);
//        }
        boolean saveResult = chapterService.save(eduChapter);
        return R.ok();
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapterForm){
//        EduChapter chapter = new EduChapter();
//        BeanUtils.copyProperties(chapterForm,chapter);
//        boolean updateResult = chapterService.updateById(chapter);
//        if (!updateResult){
//            throw GuliException.from(EduResultCode.UPDATE_ERROR);
//        }
        chapterService.updateById(chapterForm);
        return R.ok();

    }
    @ApiOperation("根据id删除章节")
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean f=chapterService.deleteChapter(chapterId);
        if(f)return R.ok();
        return R.error();
    }
}

