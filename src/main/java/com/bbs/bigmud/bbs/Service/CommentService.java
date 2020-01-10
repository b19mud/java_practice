package com.bbs.bigmud.bbs.Service;


import com.bbs.bigmud.bbs.Exception.CustomizeErrorCode;
import com.bbs.bigmud.bbs.Exception.CustomizeException;
import com.bbs.bigmud.bbs.Mapper.CommentMapper;
import com.bbs.bigmud.bbs.Mapper.QuestionExtMapper;
import com.bbs.bigmud.bbs.Mapper.QuestionMapper;
import com.bbs.bigmud.bbs.Model.Comment;
import com.bbs.bigmud.bbs.Model.Question;
import com.bbs.bigmud.bbs.enums.CommentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {

        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }


        if(comment.getType() == null || CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){

            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUNT);
            }

            commentMapper.insert(comment);
        }else{
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }




    }
}
