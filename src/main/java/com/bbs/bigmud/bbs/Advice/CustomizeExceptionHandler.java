package com.bbs.bigmud.bbs.Advice;


import com.alibaba.fastjson.JSON;
import com.bbs.bigmud.bbs.Exception.CustomizeErrorCode;
import com.bbs.bigmud.bbs.Exception.CustomizeException;
import com.bbs.bigmud.bbs.dto.ResultDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response){

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //json
            ResultDTO resultDTO =null;


            if(ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            }else{
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try{
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException ioe){

            }
            return null;
        }else{
            //redirect
            if(ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }

            return new ModelAndView("error");

        }


    }


}
