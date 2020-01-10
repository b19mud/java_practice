package com.bbs.bigmud.bbs.Exception;


import org.springframework.cache.annotation.SpringCacheAnnotationParser;

public class CustomizeException extends RuntimeException{

    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage(){
        return message;
    }


    public Integer getCode() {
        return code;
    }
}
