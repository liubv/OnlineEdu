package com.lbvguli.ucenter.controller;


import com.lbvguli.commonutils.JwtUtils;
import com.lbvguli.commonutils.R;
import com.lbvguli.ucenter.entity.Member;

import com.lbvguli.ucenter.entity.vo.RegisterVo;
import com.lbvguli.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/eduucenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private MemberService memberService;

    //登录
    @PostMapping("login")
    public R login(@RequestBody Member member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }


    //根据token获得用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfoByToken(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }



}

