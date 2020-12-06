package com.lbvguli.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.JwtUtils;
import com.lbvguli.commonutils.R;
import com.lbvguli.commonutils.ordervo.CourseWebVoOrder;
import com.lbvguli.eduservice.client.OrderClient;
import com.lbvguli.eduservice.entity.EduCourse;
import com.lbvguli.eduservice.entity.chapter.ChapterVo;
import com.lbvguli.eduservice.entity.vo.CourseFrontVo;
import com.lbvguli.eduservice.entity.vo.CourseWebVo;
import com.lbvguli.eduservice.service.EduChapterService;
import com.lbvguli.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService  courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;
    //条件查询带分页
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R pageList(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String,Object> map = courseService.getTeacherFrontList(pageParam, courseFrontVo);
        return  R.ok().data(map);
    }
    //课程详情方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id 便携sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterByCourseId(courseId);
        //根据课程id和用户id查询当前课程是否已经支付了
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Boolean isBuyCourse = orderClient.isBuyCourse(courseId,memberId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList)
                .data("isBuy",isBuyCourse);
    }

    //根据课程id获得课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);

        return courseWebVoOrder;
    }

}
