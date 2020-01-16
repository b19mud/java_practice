package com.bbs.bigmud.bbs.Exception;



public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"11111"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需登录，请登录后重试"),
    SYSTEM_ERROR(2004,"system error"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUNT(2006,"评论不存在"),
    COTENT_IS_EMPTY(2007,"评论不能为空");


    private String message;

    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode(){
        return null;
    }

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }

}

