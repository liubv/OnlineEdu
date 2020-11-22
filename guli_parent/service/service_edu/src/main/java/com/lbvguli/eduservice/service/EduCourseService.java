package com.lbvguli.eduservice.service;

import com.lbvguli.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbvguli.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
