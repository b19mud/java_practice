package com.bbs.bigmud.bbs.UserMapper;


import com.bbs.bigmud.bbs.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmt_Create},#{gmt_Modified},#{avatar_Url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
