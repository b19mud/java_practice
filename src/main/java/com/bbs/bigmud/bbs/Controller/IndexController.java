package com.bbs.bigmud.bbs.Controller;

import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.Service.QuestionService;
import com.bbs.bigmud.bbs.UserMapper.QuestionMapper;
import com.bbs.bigmud.bbs.UserMapper.UserMapper;
import com.bbs.bigmud.bbs.dto.PageDTO;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size){

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null)
                        request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }

        //查询 问题列表
        PageDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
