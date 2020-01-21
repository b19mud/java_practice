package com.bbs.bigmud.bbs.Controller;


import com.bbs.bigmud.bbs.Service.CommentService;
import com.bbs.bigmud.bbs.Service.QuestionService;
import com.bbs.bigmud.bbs.dto.CommentDTO;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import com.bbs.bigmud.bbs.enums.CommentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @Autowired
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION.getType());
        //累加阅读数


        questionService.incView(id);
        model.addAttribute("question",questionDTO);

        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }



}
