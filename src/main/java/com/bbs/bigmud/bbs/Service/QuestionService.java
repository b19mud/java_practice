package com.bbs.bigmud.bbs.Service;


import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.UserMapper.QuestionMapper;
import com.bbs.bigmud.bbs.UserMapper.UserMapper;
import com.bbs.bigmud.bbs.dto.PageDTO;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.count();
        pageDTO.setPages(totalCount,page,size);

        if(page<1){
            page =1;
        }
        if (page> pageDTO.getTotalPage()){
            page = pageDTO.getTotalPage();
        }

        Integer offset = size * (page-1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.findByID(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        pageDTO.setQuestions(questionDTOList);


        return pageDTO;

    }

    public PageDTO list(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.count();
        pageDTO.setPages(totalCount,page,size);

        if(page<1){
            page =1;
        }
        if (page> pageDTO.getTotalPage()){
            page = pageDTO.getTotalPage();
        }

        Integer offset = size * (page-1);

        List<Question> questions = questionMapper.list(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.findByID(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        pageDTO.setQuestions(questionDTOList);


        return pageDTO;

    }
}
