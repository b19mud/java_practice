package com.bbs.bigmud.bbs.Mapper;

import com.bbs.bigmud.bbs.Model.Question;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}