package com.lbvguli.eduservice.controller;


import com.lbvguli.eduservice.entity.EduTeacher;
import com.lbvguli.eduservice.service.EduTeacherService;
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
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }
}

