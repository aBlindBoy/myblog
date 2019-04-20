package com.blog.tenyears.controller.back;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.tenyears.bean.UserInfo;
import com.blog.tenyears.service.ArticleInfoService;
import com.blog.tenyears.service.CategoryInfoService;
import com.blog.tenyears.service.MessageService;
import com.blog.tenyears.service.UserInfoService;
import com.blog.tenyears.utils.Explorer;

/**
 * 创建人: 小谢 创建时间: 2018年10月23日 下午10:56:14 修改人 : 小谢 修改时间 :2018年10月23日 下午10:56:14
 * 登陆控制器
 */
@Controller
@RequestMapping("/back/")
public class BackIndexController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ArticleInfoService articleInfoService;

	
	
	@GetMapping("login.html")
	public String login() {
		return "back/login";
	}
	//强制刷新地址栏
	@GetMapping("isLogin.html")
	public String isLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		//session中不为空
		if(user != null){
			return this.index();
		}
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		session.setAttribute("user", user);
		session.setAttribute("logintime", simple.format(date));
		return this.index();
	}
	//提交登陆
	@PostMapping("isLogin.html")
	public String isLogin(UserInfo userinfo, HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		//session中不为空
		if(user != null){
			return this.index();
		}
		 user = userInfoService.doLogin(userinfo);
		if (user == null) {
			//session中不为空,用户和账号不为空
			if(StringUtils.isNotBlank(userinfo.getUserAccount()) && StringUtils.isNotBlank(userinfo.getUserPassword())){
				System.err.println(userinfo);
				req.setAttribute("flag", "用户名或密码错误");
			}
			return this.login();
		}
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		session.setAttribute("user", user);
		session.setAttribute("logintime", simple.format(date));
		return this.index();
	}
	
	@RequestMapping("index.html")
	public String index() {

		return "back/index";
	}

	@GetMapping("main.html")
	public String main(Model model, HttpServletRequest request) {
		
		int count = this.userInfoService.countUser();
		String explorer=Explorer.getClientExplorerType(request);
		int messageSum=messageService.messageSum();
		HttpSession session=request.getSession();
		UserInfo user=(UserInfo)session.getAttribute("user");
		int articleSum= articleInfoService.articleSum();
		InetAddress ia=null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("explorer", explorer);
		model.addAttribute("count", count);
		model.addAttribute("ip", ia.getHostAddress());
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("messageSum", messageSum);
		model.addAttribute("articleSum", articleSum);
		return "back/main";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		return "back/login";
	}
	


}
