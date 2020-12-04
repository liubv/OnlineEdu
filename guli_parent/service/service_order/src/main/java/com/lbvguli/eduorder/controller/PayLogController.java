package com.lbvguli.eduorder.controller;


import com.lbvguli.commonutils.R;
import com.lbvguli.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-04
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    //生成微信支付二维码接口
    @GetMapping("getNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        System.out.println("二维码输出:"+map);
        return R.ok().data(map);
    }
    //查询订单支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("****查询订单状态集合****:"+map);
        if(map == null){
            return R.error().message("支付出错");
        }
        if(map.get("trade_state").equals("SUCCESS")){
            //添加记录到支付表 更新订单表订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().message("支付中").code(25000);
    }
}

