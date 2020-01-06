package com.bbs.bigmud.bbs.Mapper;


import com.bbs.bigmud.bbs.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("INSERT INTO QUESTION(TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATER,COMMENT_COUNT,LIKE_COUNT,VIEW_COUNT,TAG) VALUES(#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creater},#{Comment_Count},#{Like_Count},#{View_Count},#{tag})")
    void createQuestion(Question question);


    @Select("SELECT * FROM QUESTION LIMIT #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value="size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION")
    Integer count();

    @Select("SELECT * FROM QUESTION  WHERE CREATER = #{userId} LIMIT #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset,@Param(value = "size") Integer size);


    @Select("SELECT COUNT(1) FROM QUESTION WHERE CREATER = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);


    @Select("SELECT * FROM QUESTION  WHERE ID = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set TITLE=#{title},DESCRIPTION=#{description},GMT_MODIFIED=#{gmt_Modified},TAG=#{tag} where id=#{id}")
    void update(Question question);
}
