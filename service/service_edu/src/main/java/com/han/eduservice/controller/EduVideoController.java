package com.han.eduservice.controller;


import com.han.commonutils.R;
import com.han.eduservice.entity.EduVideo;
import com.han.eduservice.client.VodClient;
import com.han.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加课程视频")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo videoForm){
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoForm,video);
        boolean saveResult = videoService.save(video);
        return  R.ok();
    }
    @ApiOperation("根据id删除视频")
    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable  String id){

//        //1.根据小节id获取视频id，调用方法实现删除
//        EduVideo video = videoService.getById(id);
//        String videoSourceId = video.getVideoSourceId();
//
//        if(StringUtils.isNotBlank(videoSourceId)){
//            //根据视频id  远程调用实现视频删除
//            ResultVo resultVo = vodClient.removeAliyunVideo(videoSourceId);
//            if(resultVo.getCode().equals(ResultCode.ERROR.getCode())){
//                throw GuliException.from(ResultCode.ERROR);
//            }
//        }
        EduVideo video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId))
        vodClient.removeAlyVideo(videoSourceId);
        //2.删除小节
        videoService.removeById(id);
        return R.ok();
    }
}

