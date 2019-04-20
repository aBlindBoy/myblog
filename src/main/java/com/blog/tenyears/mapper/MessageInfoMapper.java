package com.blog.tenyears.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.tenyears.bean.MessageInfo;

@Mapper
public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer messageId);

    List<MessageInfo> selectAll();

    List<MessageInfo> messageCan();

    int updateByPrimaryKey(MessageInfo record);
    
    int messageSum();
}