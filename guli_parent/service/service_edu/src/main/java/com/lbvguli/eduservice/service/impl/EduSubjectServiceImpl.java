package com.lbvguli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lbvguli.eduservice.entity.EduSubject;
import com.lbvguli.eduservice.entity.excel.SubjectData;
import com.lbvguli.eduservice.entity.subject.OneSubject;
import com.lbvguli.eduservice.entity.subject.TwoSubject;
import com.lbvguli.eduservice.listener.SubjectExcelListener;
import com.lbvguli.eduservice.mapper.EduSubjectMapper;
import com.lbvguli.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            //输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapperOne);
//        用一下这种方式也能求
//        this.list(wrapperOne);
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id",0);
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapperTwo);

        Map<String,List<TwoSubject>> map = new HashMap<>();
        for(EduSubject sub : twoSubjects){
            String pid = sub.getParentId();
            TwoSubject twoSubject = new TwoSubject();
            BeanUtils.copyProperties(sub,twoSubject);
            List<TwoSubject> list = new ArrayList<>();
            if(map.containsKey(pid)){
               list = map.get(pid);
            }
            list.add(twoSubject);
            map.put(pid,list);
        }

        List<OneSubject> finalSubjectList = new ArrayList<>();
        for (EduSubject sub : oneSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(sub,oneSubject);
            String id = oneSubject.getId();
            if(map.containsKey(id)){
                oneSubject.setChildren(map.get(id));
            }
            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }
}
