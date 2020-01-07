package com.bbs.bigmud.bbs.Exception;



public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("11111");


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message){
        this.message = message;
    }
}

