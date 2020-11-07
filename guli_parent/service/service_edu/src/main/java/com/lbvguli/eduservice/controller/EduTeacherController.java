package com.lbvguli.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.EduTeacher;
import com.lbvguli.eduservice.entity.vo.TeacherQuery;
import com.lbvguli.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-07
 */
@Api(value = "讲师控制")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "找到所有讲师")
    @GetMapping("findAll")
    public R findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "按id删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师id",required = true)
                                  @PathVariable String id){

        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("findAllPage/{current}/{limit}")
    public R findAllPage(@PathVariable long current,
                           @PathVariable long limit){
        //1.创建page对象
        //当前页 和每页大小
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "条件查询带分页")
    @GetMapping("findAllPageCondition/{current}/{limit}")
    public R findAllPageCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @PathVariable TeacherQuery query){

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.page(pageTeacher,null);
    }
}

