package com.lbvguli.eduservice.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.EduCourse;
import com.lbvguli.eduservice.entity.EduTeacher;
import com.lbvguli.eduservice.service.EduCourseService;
import com.lbvguli.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit){
         Page<EduTeacher> pageTeacher = new Page(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return R.ok().data("teacherList",map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "getTeacherFrontInfo/{teacherId}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String teacherId){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(teacherId);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = courseService.selectByTeacherId(teacherId);

        return R.ok().data("teacherInfo", teacher).data("courseList", courseList);
    }
}
