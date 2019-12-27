package com.bbs.bigmud.bbs.UserMapper;


import com.bbs.bigmud.bbs.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {


    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmt_Create},#{gmt_Modified})")
    void insert(User user);


}
