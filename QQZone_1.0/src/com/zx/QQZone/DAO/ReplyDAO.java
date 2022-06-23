package com.zx.QQZone.DAO;

import com.zx.QQZone.pojo.Reply;
import com.zx.QQZone.pojo.Topic;

import java.util.List;

/**
 * @ClassName ReplyDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 16:41
 * @Version 1.0
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReply(Topic topic);

    //添加回复
    public void addReply(Reply reply);

    //删除回复
    public void delReply(Integer id);

}
