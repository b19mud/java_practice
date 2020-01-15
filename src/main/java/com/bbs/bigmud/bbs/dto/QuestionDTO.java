package com.bbs.bigmud.bbs.dto;


import com.bbs.bigmud.bbs.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private Long gmt_Create;
    private Long gmt_Modified;
    private Long creater;
    private Integer Comment_Count;
    private Integer Like_Count;
    private Integer View_Count;
    private String tag;
    private User user;
}