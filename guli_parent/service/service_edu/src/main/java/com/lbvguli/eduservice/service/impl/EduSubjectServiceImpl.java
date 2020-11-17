package com.lbvguli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.lbvguli.eduservice.entity.EduSubject;
import com.lbvguli.eduservice.entity.excel.SubjectData;
import com.lbvguli.eduservice.listener.SubjectExcelListener;
import com.lbvguli.eduservice.mapper.EduSubjectMapper;
import com.lbvguli.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
