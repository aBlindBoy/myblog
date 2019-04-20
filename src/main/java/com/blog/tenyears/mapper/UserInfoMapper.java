package com.blog.tenyears.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.tenyears.bean.UserInfo;

@Mapper
public interface UserInfoMapper {
	int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);
    /*
     * 查询全部用户
     */
    List<UserInfo> selectAll();
    
    /*
     * 分页查询
     */
    List<UserInfo> findByPage(@Param(value="userName") String userName);

    int countUser();
    
    int updateByPrimaryKey(UserInfo record);
    /*
     * 登陆
     */
    UserInfo doLogin(UserInfo userInfo);
    
}