package com.lbvguli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbvguli.eduservice.client.VodClient;
import com.lbvguli.eduservice.entity.EduVideo;
import com.lbvguli.eduservice.mapper.EduVideoMapper;
import com.lbvguli.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeByCourseId(String courseId) {
        //根据课程id查询出课程里所有视频的id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        List<String> videoIds = new ArrayList<>();


        for (int i = 0; i < eduVideos.size(); i++) {
            String videoSourceId = eduVideos.get(i).getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        if(videoIds.size() > 0){
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_Id",courseId);
        baseMapper.delete(wrapper);
    }
}
