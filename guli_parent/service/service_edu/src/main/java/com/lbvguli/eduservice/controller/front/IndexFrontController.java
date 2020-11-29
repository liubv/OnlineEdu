package com.lbvguli.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.EduCourse;
import com.lbvguli.eduservice.entity.EduTeacher;
import com.lbvguli.eduservice.service.EduCourseService;
import com.lbvguli.eduservice.service.EduTeacherService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    //查询前8个课程和前四条讲师
    @Cacheable(value = "list",key = "'index'")
    @GetMapping("index")
    public R index(){
        //查询课程
        QueryWrapper<EduCourse> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("view_count");
        wrapper1.last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapper1);
        //查询老师
        QueryWrapper<EduTeacher> wrapper2 = new QueryWrapper<>();
        wrapper2.orderByDesc("id");
        wrapper2.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapper2);

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }


}
