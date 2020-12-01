package com.lbvguli.ucenter.service;

import com.lbvguli.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbvguli.ucenter.entity.vo.RegisterVo;


/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(Member ucenterMember);
}
