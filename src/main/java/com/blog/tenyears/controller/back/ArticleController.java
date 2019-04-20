package com.blog.tenyears.controller.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.tenyears.bean.ArticleInfo;
import com.blog.tenyears.bean.CategoryInfo;
import com.blog.tenyears.bean.UserInfo;
import com.blog.tenyears.service.ArticleInfoService;
import com.blog.tenyears.service.CategoryInfoService;
import com.blog.tenyears.utils.UpLoad;

/**
 * 创建人: 小谢 创建时间: 2018年10月24日 上午11:21:14 修改人 : 小谢 修改时间 :2018年10月24日 上午11:21:14
 * 文章控制器
 */

@Controller
@RequestMapping("/back/article/")
public class ArticleController {

	@Autowired
	private CategoryInfoService CategoryInfoService;

	@Autowired
	private ArticleInfoService artiService;


	
	
	@GetMapping("list.html")
	public String list(Model model, ArticleInfo arti) {
		List<ArticleInfo> artiList = artiService.ArticleCondition(arti);
		CategoryInfo cate=new CategoryInfo();
				
		List<CategoryInfo> cateList = CategoryInfoService.selectAll(cate);
		model.addAttribute("artiList", artiList);
		model.addAttribute("cateList", cateList);
		return "back/article/article_list";
	}

	@GetMapping("add.html")
	public String add(Model model) {
		CategoryInfo cate=new CategoryInfo();
		List<CategoryInfo> cateList = CategoryInfoService.findCateCountByArticle(cate);
		model.addAttribute("cateList", cateList);
		return "back/article/article_add";
	}
	@GetMapping("delete.html/{articleId}")
	public String delete(Model model,@PathVariable Integer articleId, ArticleInfo arti) {
		artiService.deleteByPrimaryKey(articleId);
		List<ArticleInfo> artiList = artiService.ArticleCondition(arti);
		CategoryInfo cate=new CategoryInfo();
		List<CategoryInfo> cateList = CategoryInfoService.selectAll(cate);
		model.addAttribute("artiList", artiList);
		model.addAttribute("cateList", cateList);
		return "back/article/article_list";
	}

	@GetMapping("update.html/{articleId}")
	public String update(@PathVariable Integer articleId,Model model) {
		//ArticleInfo articlInfo=artiService.selectByPrimaryKey(articleId);
		CategoryInfo cate=new CategoryInfo();
		List<CategoryInfo> cateList = CategoryInfoService.findCateCountByArticle(cate);
		model.addAttribute("cateList", cateList);
		//model.addAttribute("articleInfo", articlInfo);
		return "back/article/article_update";
	}

	@PostMapping("add.html")
	public String addEnd(Model model, ArticleInfo articleInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		articleInfo.setUserId(user.getUserId());
		int row = this.artiService.insert(articleInfo);
		model.addAttribute("articleInfo", articleInfo);
		if (row > 0) {
			model.addAttribute("flag", "发布文章成功");
		} else {
			model.addAttribute("flag", "发布文章失败");
		}
		return this.add(model);
	}

	/*
	 * 上传标题图片
	 */
	@PostMapping("upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload) {
		String url = UpLoad.doPutFile(upload);
		return url;
	}

	/**
	 * 富文本编辑器上传
	 * 
	 * @param upload
	 * @param request
	 * @param response
	 */
	@PostMapping("uploadFile")
	public void uploadFile(@RequestParam MultipartFile upload,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = UpLoad.doPutFile(upload);
			PrintWriter out = response.getWriter();
			String callBack = request.getParameter("CKEditorFuncNum");
			String langCode	 = request.getParameter("langCode");
			System.out.println(callBack+"  "+langCode);
			out.println("<script>"
					+ "window.parent.CKEDITOR.tools.callFunction(" + 1 + ","
					+ url + ")</script>");
			
		} catch (IOException e) {
			e.printStackTrace();
			//HttpServletRequest request,
		}//action="/back/article/uploadFile?CKEditor=editor&CKEditorFuncNum=1&langCode=zh-cn"
	}

}
