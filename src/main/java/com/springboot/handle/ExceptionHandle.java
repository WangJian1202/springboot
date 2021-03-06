package com.springboot.handle;

import com.springboot.domain.Result;
import com.springboot.exception.GirlException;
import com.springboot.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GirlException){
            return ResultUtils.failure(((GirlException) e).getCode(),e.getMessage());
        }else{
            logger.error("[系统异常]",e);
            return ResultUtils.failure("-1","未知错误");
        }

    }
}
