package com.lbvguli.eduservice.controller;


import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.client.VodClient;
import com.lbvguli.eduservice.entity.EduVideo;
import com.lbvguli.eduservice.service.EduVideoService;
import com.lbvguli.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;


    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();

    }

    //修改小节


    //删除小节
    //TODO 删除小节同时删除视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if(!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }

        videoService.removeById(id);
        return  R.ok();
    }


}

