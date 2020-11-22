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

import java.util.List;

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
        eduCourse.setIsDeleted(0);
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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }
}
