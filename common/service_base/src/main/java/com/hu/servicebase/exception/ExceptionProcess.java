package com.hu.servicebase.exception;

import com.hu.message.ResponseMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @ControllerAdvice
 *  增强型Controller
 *  作用：1 全局异常处理； 2 全局数据绑定； 3 全局数据预处理
 */
@ControllerAdvice
public class ExceptionProcess {

    // 1 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody           // 为了能够返回数据给页面进行渲染
    public ResponseMessage deal(Exception e){
        e.printStackTrace();
        return ResponseMessage.error().message("执行全局异常处理.....");
    }
    // 2 特定异常处理
    @ExceptionHandler(IOException.class)
    @ResponseBody           // 为了能够返回数据给页面进行渲染
    public ResponseMessage deal(IOException e){
        e.printStackTrace();
        return ResponseMessage.error().message("执行全局异常处理.....");
    }
    // 3 自定义异常处理    需要自己在代码中手动抛出异常  try  catch  throw new MyException(111,"文件错误")
    @ExceptionHandler(MyException.class)
    @ResponseBody           // 为了能够返回数据给页面进行渲染
    public ResponseMessage deal(MyException e){
        e.printStackTrace();
        return ResponseMessage.error().code(e.getCode()).message(e.getMsg());

    }
}
