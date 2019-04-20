package com.blog.tenyears.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.tenyears.bean.UserInfo;
import com.blog.tenyears.mapper.UserInfoMapper;
import com.blog.tenyears.service.UserInfoService;
import com.blog.tenyears.utils.pagingPlugin.PageBean;


/**
 * 创建人: 小谢 创建时间: 2018年10月24日 下午1:41:48 修改人 : 小谢 修改时间 :2018年10月24日 下午1:41:48
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return userInfoMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfoMapper.insert(record);
	}

	@Override
	public UserInfo selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询全部用户信息
	 */
	@Override
	public List<UserInfo> selectAll() {

		return userInfoMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public List<UserInfo> findByPage(String userName) {
		List<UserInfo> userInfo=userInfoMapper.findByPage(userName);
		/*	int count=userInfoMapper.countUser();
		PageBean<UserInfo> pageData=new PageBean<>(1,2,count);
		pageData.setItems(userInfo);*/
		return userInfo;
	}

	/* (non-Javadoc)
	 * @see com.blog.tenyears.service.UserInfoService#doLogin(com.blog.tenyears.bean.UserInfo)
	 */
	@Override
	public UserInfo doLogin(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return this.userInfoMapper.doLogin(userInfo);
	}

	/* (non-Javadoc)
	 * @see com.blog.tenyears.service.UserInfoService#countUser()
	 */
	@Override
	public int countUser() {
		// TODO Auto-generated method stub
		return this.userInfoMapper.countUser();
	}

}
