package com.lbvguli.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbvguli.eduservice.entity.vo.CourseFrontVo;
import com.lbvguli.eduservice.entity.vo.CourseInfoVo;
import com.lbvguli.eduservice.entity.vo.CoursePublishVo;
import com.lbvguli.eduservice.entity.vo.CourseWebVo;

import java.util.List;
import java.util.Map;

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

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    List<EduCourse> selectByTeacherId(String teacherId);

    Map<String, Object> getTeacherFrontList(Page<EduCourse> pageParam, CourseFrontVo courseQuery);

    CourseWebVo getBaseCourseInfo(String courseId);
}
