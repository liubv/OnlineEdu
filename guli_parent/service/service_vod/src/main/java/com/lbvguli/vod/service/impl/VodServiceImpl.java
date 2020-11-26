package com.lbvguli.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lbvguli.commonutils.R;
import com.lbvguli.servicebase.exceptionhandler.GuliException;
import com.lbvguli.vod.service.VodService;
import com.lbvguli.vod.utils.ConstantUtils;
import com.lbvguli.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        //filename 原始名称
        //title 上传后名称
        //inputSteam 上传文件输入流

        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0,fileName.lastIndexOf("."));
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadStreamRequest request = new UploadStreamRequest(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = null;
        if (response.isSuccess()) {
            videoId = response.getVideoId();
        } else {
            videoId = response.getVideoId();
        }

        return videoId;
    }

    @Override
    public void removeAllAlyVideo(List videoIdList) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            String videoIds = StringUtils.join(videoIdList,",");

            request.setVideoIds(videoIds);

            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }
}
