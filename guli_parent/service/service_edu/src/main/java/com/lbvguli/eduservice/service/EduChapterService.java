package com.lbvguli.eduservice.service;

import com.lbvguli.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbvguli.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
