package com.blog.tenyears.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.tenyears.bean.MessageInfo;
import com.blog.tenyears.mapper.MessageInfoMapper;
import com.blog.tenyears.service.MessageService;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:43:38
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:43:38
 */
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageInfoMapper messageInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer messageId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insert(MessageInfo record) {
		// TODO Auto-generated method stub
		return this.messageInfoMapper.insert(record);
	}

	@Override
	public MessageInfo selectByPrimaryKey(Integer messageId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MessageInfo> selectAll() {
		// TODO Auto-generated method stub
		return messageInfoMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(MessageInfo record) {
		// TODO Auto-generated method stub
		return messageInfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public int messageSum() {
		// TODO Auto-generated method stub
		return this.messageInfoMapper.messageSum();
	}
	@Override
	public List<MessageInfo> messageCan() {
		// TODO Auto-generated method stub
		return this.messageInfoMapper.messageCan();
	}

}
