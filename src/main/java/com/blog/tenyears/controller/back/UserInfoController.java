package com.blog.tenyears.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.tenyears.bean.UserInfo;
import com.blog.tenyears.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 创建人: 小谢 创建时间: 2018年10月24日 上午11:02:24 修改人 : 小谢 修改时间 :2018年10月24日 上午11:02:24
 * 用户控制器
 */

@Controller
@RequestMapping("/back/user/")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("list.html")
	public String list(Model model,String userName) {
	
		List<UserInfo> userInfo=userInfoService.findByPage(userName);
		for (UserInfo u : userInfo) {
			System.out.println(u);
		}
		model.addAttribute("userInfo",userInfo);
		return "back/userinfo/userinfo_list";
	}

	@GetMapping("add.html")
	public String add() {

		return "back/userinfo/userinfo_add";
	}

	@PostMapping("addEnd.html")
	public String addEnd(UserInfo userInfo,Model model) {
		int row=userInfoService.insert(userInfo);
		if(row>=0){
			model.addAttribute("flag","添加用戶成功");
		}else{
			model.addAttribute("flag","添加用戶失敗");
		}
		
		return "back/userinfo/userinfo_add";
	}

	@GetMapping("update")
	public String update() {

		return "back/userinfo/userinfo_add";
	}
	@GetMapping("delete.html/{userId}")
	public String delete(Model model,String userName,@PathVariable Integer userId) {
		this.userInfoService.deleteByPrimaryKey(userId);
		return this.list(model, userName);
	}

}
