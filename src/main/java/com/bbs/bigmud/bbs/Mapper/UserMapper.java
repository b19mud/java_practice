package com.bbs.bigmud.bbs.Mapper;


import com.bbs.bigmud.bbs.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmt_Create},#{gmt_Modified},#{avatar_Url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{creater}")
    User findByID(@Param("creater") Integer creater);

    @Select("select * from user where account_id = #{account_id}")
    User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set name=#{name},token=#{token},GMT_MODIFIED=#{gmt_Modified},AVATAR_URL=#{avatar_Url}")
    void update(User user);

}
