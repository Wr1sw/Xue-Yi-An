package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Announcement {
    /**
    * 公告表主键
    */
    private Integer id;

    /**
    * 公告标题
    */
    private String title;

    /**
    * 公告内容
    */
    private String content;

    /**
    * 公告发布者
    */
    private String reporter;

    /**
    * 公告发布时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date time;

    /**
    * 公告是否紧急
    */
    private Integer isUrge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIsUrge() {
        return isUrge;
    }

    public void setIsUrge(Integer isUrge) {
        this.isUrge = isUrge;
    }
}