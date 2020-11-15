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
        return R.ok().data("roles",roles).data("name","lbw").data("avater","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605266891836&di=f744843a9d0d7e44d4c13fe8e7df2439&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F00%2F90%2F47%2F9256efcbf458c23.jpg");
    }
}
