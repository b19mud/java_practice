package com.bbs.bigmud.bbs.Mapper;

import com.bbs.bigmud.bbs.Model.Question;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);
}