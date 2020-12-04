package com.lbvguli.eduorder.service.impl;

import com.lbvguli.commonutils.ordervo.CourseWebVoOrder;
import com.lbvguli.commonutils.ordervo.MemberOrder;
import com.lbvguli.eduorder.client.EduClient;
import com.lbvguli.eduorder.client.UcenterClient;
import com.lbvguli.eduorder.entity.Order;
import com.lbvguli.eduorder.mapper.OrderMapper;
import com.lbvguli.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbvguli.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-12-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String createOrders(String courseId, String memberIdByJwtToken) {

        //通过远程调用获取用户id/课程信息
        MemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0); //支付状态 0 未支付 1 已支付
        order.setPayType(1);//支付方式 1 微信

        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
