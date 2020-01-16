package com.bbs.bigmud.bbs.Controller;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.bbs.bigmud.bbs.Exception.CustomizeErrorCode;
import com.bbs.bigmud.bbs.Mapper.CommentMapper;
import com.bbs.bigmud.bbs.Model.Comment;
import com.bbs.bigmud.bbs.Model.User;
import com.bbs.bigmud.bbs.Service.CommentService;
import com.bbs.bigmud.bbs.dto.CommentCreateDTO;
import com.bbs.bigmud.bbs.dto.CommentDTO;
import com.bbs.bigmud.bbs.dto.ResultDTO;
import com.bbs.bigmud.bbs.enums.CommentTypeEnum;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if(commentCreateDTO == null || StringUtils.isNullOrEmpty(commentCreateDTO.getContent())){
            return  ResultDTO.errorOf((CustomizeErrorCode.COMMENT_NOT_FOUNT));
        }


        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){

        List<CommentDTO> commentDTOs = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);

        return ResultDTO.okOf(commentDTOs);
    }
}
