package com.lbvguli.eduservice.service.impl;

import com.lbvguli.eduservice.entity.EduCourse;
import com.lbvguli.eduservice.entity.EduCourseDescription;
import com.lbvguli.eduservice.entity.vo.CourseInfoVo;
import com.lbvguli.eduservice.mapper.EduCourseMapper;
import com.lbvguli.eduservice.service.EduCourseDescriptionService;
import com.lbvguli.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbvguli.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        //id
        String cid = eduCourse.getId();
        //向简介表添加简介
        EduCourseDescription eduCourseDescription  = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        courseDescriptionService.save(eduCourseDescription);

        return cid;
    }
}
