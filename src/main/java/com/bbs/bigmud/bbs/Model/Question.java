package com.bbs.bigmud.bbs.Model;

public class Question {

    private Integer id;
    private String title;
    private String description;
    private Long gmt_Create;
    private Long gmt_Modified;
    private Integer creater;
    private Integer Comment_Count;
    private Integer Like_Count;
    private Integer View_Count;
    private String tag;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGmt_Create() {
        return gmt_Create;
    }

    public void setGmt_Create(Long gmt_Create) {
        this.gmt_Create = gmt_Create;
    }

    public Long getGmt_Modified() {
        return gmt_Modified;
    }

    public void setGmt_Modified(Long gmt_Modified) {
        this.gmt_Modified = gmt_Modified;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
    }

    public int getComment_Count() {
        return Comment_Count;
    }

    public void setComment_Count(int comment_Count) {
        Comment_Count = comment_Count;
    }

    public int getLike_Count() {
        return Like_Count;
    }

    public void setLike_Count(int like_Count) {
        Like_Count = like_Count;
    }

    public int getView_Count() {
        return View_Count;
    }

    public void setView_Count(int view_Count) {
        View_Count = view_Count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
