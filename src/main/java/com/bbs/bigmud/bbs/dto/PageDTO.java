package com.bbs.bigmud.bbs.dto;


import lombok.Data;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {

    private List<QuestionDTO> questions;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showPrePage;
    private boolean showNexPage;
    private Integer totalPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();


    public void setPages(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.currentPage = page;

        pages.add(page);
        for(int i=1;i<3;i++){
            if(page-i>0){
                pages.add(0, page -i);
            }

            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }



        //是否展示上一页与下一页
        if(page == 1) {
            showPrePage = false;
        }else{
            showPrePage = true;
        }

        if(page == totalPage)
        {
            showNexPage = false;
        }else{
            showNexPage = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

    }
}
