package com.lbvguli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeAllAlyVideo(List videoIdList);
}
