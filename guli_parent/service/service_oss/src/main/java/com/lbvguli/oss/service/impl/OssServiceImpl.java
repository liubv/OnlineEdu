package com.lbvguli.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.lbvguli.oss.service.OssService;
import com.lbvguli.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    //上传文件到oss
    @Override
    public String uploadFileAvater(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件的输入流。
            InputStream inputStream = file.getInputStream();

            //1 在文件名里添加随机唯一的值
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid + fileName;
            //2 把文件按照日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+fileName;

            //调用oss方法实现上传
            //参数1 Bucket名称
            //参数2 上传到oss文件路径和文件名称 /aa/bb/cc/1.jpg
            //参数3 输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回上传oss后到路径
            //手动拼接
            //https://edu-lbv.oss-cn-shanghai.aliyuncs.com/unnamed.png
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;

        }catch (Exception e){

        }

        return null;
    }
}
