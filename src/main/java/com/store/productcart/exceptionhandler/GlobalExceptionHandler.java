package com.store.productcart.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity exception(Exception e){
        System.out.println("发生异常:" + e.getMessage());
        //http响应码设置为500
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
