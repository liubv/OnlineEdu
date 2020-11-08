package com.lbvguli.servicebase.exceptionhandler;

import com.lbvguli.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了特定异常处理");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody//为了返回数据
    public R error(GuliException e){
        log.error(e.getMsg());
        log.error("123");
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }


}
