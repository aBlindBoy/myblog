package com.blog.tenyears.utils.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;

import com.blog.tenyears.bean.UserInfo;

/**
 * 创建人: 小谢 创建时间: 2018年10月25日 下午3:37:35 修改人 : 小谢 修改时间 :2018年10月25日 下午3:37:35
 */
@WebFilter("/back/*")
@Configuration
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// 判断用户是否登录
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession();

		UserInfo user = (UserInfo) session.getAttribute("user");

		// 拿到用户请求的地址，如果执行的登录操作，应该允许用户登录
		String url = req.getRequestURI();
		System.out.println(url);
		if (user != null) {
			// 已经登录过
			arg2.doFilter(req, arg1);// 继续请求
		} else if (url.contains("/front")) {
			arg2.doFilter(req, arg1);// 继续请求
		} else if (url.contains("/back/isLogin.html")) {
			arg2.doFilter(req, arg1);// 继续请求
		} else if (url.contains("/admin")) {
			arg2.doFilter(req, arg1);// 继续请求
		} else if (url.contains("/back/login.html")) {
			arg2.doFilter(req, arg1);// 登录的时候包含登录页面，放行
		} else {
				if(!url.contains("/back")){
					//不包含back的返回index页面
					req.getRequestDispatcher("/front/index.html").forward(req, arg1);
				}else{
					// 未登录,返回登录界面
					req.getRequestDispatcher("/back/login.html").forward(req, arg1);
				}
		
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
			
	}

}
