package com.lbvguli.oss.controller;

import com.lbvguli.commonutils.R;
import com.lbvguli.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvater(file);
        return R.ok().data("url",url);
    }
}
