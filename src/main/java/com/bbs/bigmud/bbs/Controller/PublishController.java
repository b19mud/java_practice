package com.bbs.bigmud.bbs.Controller;

import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.Service.QuestionService;
import com.bbs.bigmud.bbs.Mapper.QuestionMapper;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id",required = false) Long id,
                            HttpServletRequest request,
                            Model model
                            ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //演示使用
        if(title == null || title == ""){
                model.addAttribute("error","标题不能为空");
                return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);

        question.setCreater(user.getId());
        question.setId(id);

        questionService.createOrUpdate(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){

        QuestionDTO question = questionService.getById(id);

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());


        return "publish";

    }
}
