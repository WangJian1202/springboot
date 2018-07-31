package com.springboot.enums;

public enum ResultEnum {
    UNKONW_ERROR("-1","未知错误"),
    SUCCESS("0","成功"),
    PRIMARY_SCHOOL("100","你还在上小学"),
    MIDDLE_SCHOOL("101","你可能上初中")
    ;
    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
