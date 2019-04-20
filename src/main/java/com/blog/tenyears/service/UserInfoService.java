package com.blog.tenyears.service;

import java.util.List;

import com.blog.tenyears.bean.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:39:26
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:39:26
 */
public interface UserInfoService {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    List<UserInfo> selectAll();

    List<UserInfo> findByPage(String userName);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo doLogin(UserInfo userInfo);

    int countUser();
}
