package com.bbs.bigmud.bbs.UserMapper;


import com.bbs.bigmud.bbs.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface QuestionMapper {


    @Insert("INSERT INTO QUESTION(TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATER,COMMENT_COUNT,LIKE_COUNT,VIEW_COUNT,TAG) VALUES(#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creater},#{Comment_Count},#{Like_Count},#{View_Count},#{tag})")
    void createQuestion(Question question);

}
