package com.lbvguli.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.EduTeacher;
import com.lbvguli.eduservice.entity.vo.TeacherQuery;
import com.lbvguli.eduservice.service.EduTeacherService;
import com.lbvguli.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R findAllPageCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required =  false) TeacherQuery teacherQuery){//false表示这个值不是必要的

        //page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //wrapper条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }

        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "添加老师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
}

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if(b){
            return  R.ok();
        }else{
            return  R.error();
        }
    }
}

