package com.lbvguli.eduservice.client;

import com.alibaba.excel.event.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDegradeFeignClient implements OrderClient {

    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
