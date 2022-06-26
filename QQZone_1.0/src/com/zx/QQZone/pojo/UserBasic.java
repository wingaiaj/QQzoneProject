package com.zx.QQZone.pojo;

import java.util.List;

/**
 * @ClassName UserBasic
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 15:36
 * @Version 1.0
 */
public class UserBasic {
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;

    private UserDetail userDetail;//1:1 一个用户有一个详细信息
    private List<Topic> topicList;//1:N 一个用户拥有多个日志
    private List<UserBasic> friendList;//M:N 多对多 一个人有多个好友  多个好友同时也可以有多个好友

    public UserBasic() {
    }
    public UserBasic(Integer id){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }
}
