package com.lbvguli.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.EduCourse;
import com.lbvguli.eduservice.entity.vo.CourseInfoVo;
import com.lbvguli.eduservice.entity.vo.CoursePublishVo;
import com.lbvguli.eduservice.entity.vo.CourseQuery;
import com.lbvguli.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);

        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseInfoVo.getId();
        courseService.updateCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);

    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(courseId);

        return R.ok().data("publishCourse",coursePublishVo);
    }

    //修改课程发布状态
    @GetMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();

    }

    //课程列表
    //TODO 条件查询带分页
    @PostMapping("getCourseCondition/{current}/{limit}")
    public R getCourse(@PathVariable long current,
                       @PathVariable long limit,
                       @RequestBody(required = false)CourseQuery courseQuery){
        //page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);

        //wrapper
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        wrapper.orderByDesc("gmt_create");

        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal();

        List<EduCourse> list = pageCourse.getRecords();
        return R.ok().data("list",list).data("total",total);
    }

    @GetMapping("getAllCourse")
    public R getAllCourse(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }
}

