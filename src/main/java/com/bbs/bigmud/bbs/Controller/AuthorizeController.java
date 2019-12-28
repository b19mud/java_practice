package com.bbs.bigmud.bbs.Controller;

import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.UserMapper.UserMapper;
import com.bbs.bigmud.bbs.dto.AccessTokenDTO;
import com.bbs.bigmud.bbs.dto.GithubUser;
import com.bbs.bigmud.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
  *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secretId;
    @Value("${github.redirect.uri}")
    private String redirect_uri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secretId);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        //session ,cookie验证
        if(githubUser!=null && githubUser.getId() != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_Create(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Create());
            user.setAvatar_Url(githubUser.getAvatar_Url());
            userMapper.insert(user);

            response.addCookie(new Cookie("token",token));
            //sucess
            request.getSession().setAttribute("githubUser",githubUser);

            return "redirect:/";
        }
        else{
            //fail
            return "redirect:/";
        }

        //token 验证


    }
}
