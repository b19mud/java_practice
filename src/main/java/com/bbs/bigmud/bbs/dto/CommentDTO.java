package com.bbs.bigmud.bbs.dto;


import com.bbs.bigmud.bbs.Model.User;
import lombok.Data;

@Data
public class CommentDTO {


    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private User user;

    private Integer commentCount;
}
