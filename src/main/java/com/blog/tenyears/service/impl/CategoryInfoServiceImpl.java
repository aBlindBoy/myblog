package com.blog.tenyears.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.tenyears.bean.CategoryInfo;
import com.blog.tenyears.mapper.ArticleInfoMapper;
import com.blog.tenyears.mapper.CategoryInfoMapper;
import com.blog.tenyears.service.ArticleInfoService;
import com.blog.tenyears.service.CategoryInfoService;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:42:36
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:42:36
 */
@Service
public class CategoryInfoServiceImpl implements CategoryInfoService{

	@Autowired
	private CategoryInfoMapper categoryInfoMapper;
	
	@Autowired
	private ArticleInfoService articleInfoService ;
	
	/**
	 * 抛出异常回滚事务
	 * @param categoryId
	 * @return 
	 * @throws Exception
	 */
	@Transactional
//	@CacheEvict(cacheNames="categoryInfo",allEntries=true)
	public int deleteByPrimaryKey(Integer categoryId) throws Exception {
		//跟据栏目编号删除多条文章记录
		articleInfoService.deleteByCategoryId(categoryId);
		//删除栏目
		return categoryInfoMapper.deleteByPrimaryKey(categoryId);
	}
	
	@Override
//	@CacheEvict(cacheNames="categoryInfo",allEntries=true)
	public int updateByPrimaryKey(CategoryInfo record) {
		// TODO Auto-generated method stub
		return categoryInfoMapper.updateByPrimaryKey(record);
	}

	@Override
//	@CacheEvict(cacheNames="categoryInfo",allEntries=true)
	public int insert(CategoryInfo record) {
		// TODO Auto-generated method stub
		return categoryInfoMapper.insert(record);
	}


	@Override
//	@Cacheable(cacheNames="categoryInfo",key="#p0")
	public CategoryInfo selectByPrimaryKey(Integer categoryId) {
		// TODO Auto-generated method stub
		return categoryInfoMapper.selectByPrimaryKey(categoryId);
	}
	
	@Override
//	@Cacheable(cacheNames="categoryInfo" ,key="#p0") 
	public List<CategoryInfo> selectAll(CategoryInfo categoryInfo) {
		return categoryInfoMapper.selectAll();
	}
	
	@Override
//	@Cacheable(cacheNames="categoryInfo",key="#p0")
	public  List<CategoryInfo> findCateCountByArticle(CategoryInfo categoryInfo) {
		
		return categoryInfoMapper.findCateCountByArticle();
	}


	
}
