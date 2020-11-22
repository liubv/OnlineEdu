package com.lbvguli.eduservice.controller;


import com.lbvguli.commonutils.R;
import com.lbvguli.eduservice.entity.chapter.ChapterVo;
import com.lbvguli.eduservice.service.EduChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("getChapterVideo/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.getChapterByCourseId(courseId);
        return R.ok().data("items", chapterVoList);
    }
}

