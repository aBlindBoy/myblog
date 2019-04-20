package com.blog.tenyears.service;

import java.util.List;

import com.blog.tenyears.bean.MessageInfo;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:39:09
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:39:09
 */
public interface MessageService {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer messageId);

    List<MessageInfo> selectAll();
    List<MessageInfo> messageCan();

    int updateByPrimaryKey(MessageInfo record);
    
    int messageSum();

}
