package com.lbvguli.eduservice.client;

import com.lbvguli.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//出错之后会执行
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错");
    }
}
