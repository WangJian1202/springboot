package com.springboot.utils;

import com.springboot.domain.Girl;
import com.springboot.domain.Result;

public class ResultUtils {
    public static Result<Girl> success(String code,Girl girl) {
        Result result = new Result();
        result.setMsg("保存成功");
        result.setCode(code);
        result.setData(girl);
        return result;
    }

    public static Result<Girl> success() {
        Result result = new Result();
        result.setMsg("保存成功");
        result.setCode("1");
        return result;
    }
    public static Result<Girl> failure(String code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
