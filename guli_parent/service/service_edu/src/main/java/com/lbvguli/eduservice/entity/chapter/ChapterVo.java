package com.lbvguli.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "章节信息")
@Data
public class ChapterVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
