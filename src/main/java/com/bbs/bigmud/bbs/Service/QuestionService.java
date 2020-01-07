package com.bbs.bigmud.bbs.Service;


import com.bbs.bigmud.bbs.Exception.CustomizeErrorCode;
import com.bbs.bigmud.bbs.Exception.CustomizeException;
import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.Model.QuestionExample;
import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.Mapper.QuestionMapper;
import com.bbs.bigmud.bbs.Mapper.UserMapper;
import com.bbs.bigmud.bbs.dto.PageDTO;
import com.bbs.bigmud.bbs.dto.QuestionDTO;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalPage;
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());

        if(totalCount%size == 0)
        {
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }

        if(page<1){
            page =1;
        }
        if (page> totalPage){
            page = totalPage;
        }

        pageDTO.setPages(totalPage,page);

        Integer offset = size * (page-1);

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreater());
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

        Integer totalPage;

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreaterEqualTo(userId);

        Integer totalCount = (int)questionMapper.countByExample(example);

        if(totalCount%size == 0)
        {
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }

        if(page<1){
            page =1;
        }
        if (page> totalPage){
            page = totalPage;
        }
        pageDTO.setPages(totalPage,page);

        Integer offset = size * (page-1);

        QuestionExample example1 = new QuestionExample();
        example1.createCriteria()
                .andCreaterEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example1,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        pageDTO.setQuestions(questionDTOList);


        return pageDTO;

    }

    public QuestionDTO getById(Integer id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreater());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {

        if(question.getId() == null){

            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if(update != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

    }
}
