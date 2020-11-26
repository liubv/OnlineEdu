package com.lbvguli.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lbvguli.commonutils.R;
import com.lbvguli.servicebase.exceptionhandler.GuliException;
import com.lbvguli.vod.service.VodService;
import com.lbvguli.vod.utils.ConstantUtils;
import com.lbvguli.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);

            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }

    //删除多个视频
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeAllAlyVideo(videoIdList);
        return R.ok();
    }
}
