package com.blog.tenyears.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建人: 小谢 创建时间: 2018年10月25日 下午5:19:23 修改人 : 小谢 修改时间 :2018年10月25日 下午5:19:23
 */
public class Explorer {

	
	
	/**
	 * 
	 * 判断是否是IE浏览器
	 * 
	 * 
	 * 
	 * @param userAgent
	 * 
	 * @return
	 * 
	 */

	public static boolean isIE(HttpServletRequest request) {

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();

		boolean isIe = true;

		int index = userAgent.indexOf("msie");

		if (index == -1) {

			isIe = false;

		}

		return isIe;

	}

	/**
	 * 
	 * 判断是否是Chrome浏览器
	 * 
	 * 
	 * 
	 * @param userAgent
	 * 
	 * @return
	 * 
	 */

	public static boolean isChrome(HttpServletRequest request) {

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();

		boolean isChrome = true;

		int index = userAgent.indexOf("chrome");

		if (index == -1) {

			isChrome = false;

		}

		return isChrome;

	}

	/**
	 * 
	 * 判断是否是Firefox浏览器
	 * 
	 * 
	 * 
	 * @param userAgent
	 * 
	 * @return
	 * 
	 */

	public static boolean isFirefox(HttpServletRequest request) {

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();

		boolean isFirefox = true;

		int index = userAgent.indexOf("firefox");

		if (index == -1) {

			isFirefox = false;

		}

		return isFirefox;

	}

	/**
	 * 
	 * 获取客户端类型
	 * 
	 * 
	 * 
	 * @param userAgent
	 * 
	 * @return
	 * 
	 */

	public static String getClientExplorerType(HttpServletRequest request) {

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();

		String explorer = "非主流浏览器";

		if (isIE(request)) {

			int index = userAgent.indexOf("msie");

			explorer = userAgent.substring(index, index + 8);

		} else if (isChrome(request)) {

			int index = userAgent.indexOf("chrome");

			explorer = userAgent.substring(index, index + 12);

		} else if (isFirefox(request)) {

			int index = userAgent.indexOf("firefox");

			explorer = userAgent.substring(index, index + 11);

		}

		return explorer.toUpperCase();

	}
}
