package com.bbs.bigmud.bbs.Service;


import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.UserMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {

        User dbUser = userMapper.findByAccountId(user.getAccount_id());

        if (dbUser == null) {
            //insert
            user.setGmt_Create(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Create());
            userMapper.insert(user);
        }else{
            //update
            dbUser.setGmt_Modified(System.currentTimeMillis());
            dbUser.setAvatar_Url(user.getAvatar_Url());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }

    }
}
