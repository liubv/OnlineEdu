package com.lbvguli.eduservice.controller;


import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.subject.OneSubject;
import com.lbvguli.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-17
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public R addSublic(MultipartFile file){
        //上传过来的excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    //课程分类列表功能
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }


}

