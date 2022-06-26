package com.zx.QQZone.pojo;


import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName Topic
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 15:41
 * @Version 1.0
 */
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Timestamp topicDate;
    private UserBasic author;  //M:1

    private List<Reply> replyList; //1:N


    public Topic() {

    }

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

    public Timestamp getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(Timestamp topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
