package com.lbvguli.eduservice.controller;

import com.lbvguli.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Api(value = "登录控制")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public  R info(){
        List<String> roles = new ArrayList();
        roles.add("admin");
        return R.ok().data("roles",roles).data("name","lbw").data("avatar","https://edu-lbv.oss-cn-shanghai.aliyuncs.com/2020/11/16/416275131b164a6da5ca00c4b7fedd90file.png");
    }
}
