package com.lbvguli.eduservice.controller;


import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.vo.CourseInfoVo;
import com.lbvguli.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourese")
    public R addCourese(@RequestBody CourseInfoVo courseInfoVo){
        String id = "";
        id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
}

