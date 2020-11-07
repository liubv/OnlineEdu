package com.lbvguli.servicebase.exceptionhandler;

import com.lbvguli.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //指定异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }
}
