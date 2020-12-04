package com.lbvguli.eduorder.client;

import com.lbvguli.commonutils.ordervo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/eduucenter/member/getUserInfoOrder/{id}")
    public MemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
