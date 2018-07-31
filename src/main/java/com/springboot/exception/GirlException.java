package com.springboot.exception;

import com.springboot.enums.ResultEnum;

public class GirlException extends RuntimeException {
    private String code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
