package com.bbs.bigmud.bbs.Controller;


import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.Service.QuestionService;
import com.bbs.bigmud.bbs.UserMapper.QuestionMapper;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){

        QuestionDTO questionDTO = questionService.getById(id);

        model.addAttribute("question",questionDTO);
        return "question";
    }
}
