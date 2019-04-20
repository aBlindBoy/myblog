package com.blog.tenyears.controller.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.tenyears.bean.CategoryInfo;
import com.blog.tenyears.bean.UserInfo;
import com.blog.tenyears.service.CategoryInfoService;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 上午11:22:49
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 上午11:22:49
 * 栏目控制器
 */

@Controller
@RequestMapping("/back/category")
public class CategoryController {

	@Autowired
	private CategoryInfoService CategoryInfoService;
	
	@GetMapping("list.html")
	public String list(Model model){
		CategoryInfo cate=new CategoryInfo();
		List<CategoryInfo> cateList=CategoryInfoService.findCateCountByArticle(cate);
		cateList.forEach(System.err::println);
		model.addAttribute("cateList", cateList);
		return "back/category/category";
	}
	@GetMapping("update.html/{id}")
	public String update(Model model,@PathVariable("id") Integer id){
		CategoryInfo cateInfo=CategoryInfoService.selectByPrimaryKey(id);	
		//System.err.println(cateInfo);
		model.addAttribute("cateInfo", cateInfo);
		return "back/category/category_update";
	}
	@GetMapping("delete.html/{id}")
	public String delete(Model model,@PathVariable("id") Integer id){
		try {
			CategoryInfoService.deleteByPrimaryKey(id);
			model.addAttribute("flag", "删除栏目成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "删除栏目失败");
		}
		return this.list(model);
	}
	@PostMapping("update.html")
	public String updateEnd(Model model,CategoryInfo cateInfo){
		int row=CategoryInfoService.updateByPrimaryKey(cateInfo);
		model.addAttribute("cateInfo", cateInfo);
		if(row>0){
			model.addAttribute("flag", "修改栏目信息成功");
		}else{
			model.addAttribute("flag", "修改栏目信息失败");
		}
		
		return "back/category/category_update";
	}
	
	
	@PostMapping("add.html")
	public String add(CategoryInfo record,Model model){
	
		int row=CategoryInfoService.insert(record);
		
		if(row > 0){
			model.addAttribute("flag", "添加栏目成功");
		}else{
			model.addAttribute("flag", "添加栏目失败");
		}
		return list(model);
	}
}
