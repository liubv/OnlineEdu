package com.lbvguli.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvater(MultipartFile file);
}
