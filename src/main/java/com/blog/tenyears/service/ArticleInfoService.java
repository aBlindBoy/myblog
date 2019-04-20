package com.blog.tenyears.service;

import java.util.List;


import com.blog.tenyears.bean.ArticleInfo;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:16:45
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:16:45
 */

public interface ArticleInfoService {
    int deleteByPrimaryKey(Integer articleId);
    
    int insert(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer pageView,Integer articleId);

    List<ArticleInfo> selectAll();
    
    List<ArticleInfo> ArticleCondition(ArticleInfo categoryId);
    
    int updateByPrimaryKey(ArticleInfo record);
    
    int deleteByCategoryId(int categoryId);
    
    int articleSum();
    
    List<ArticleInfo> articleNewList();
    
    List<ArticleInfo> recomNewList();
    

	List<ArticleInfo> findArticleByCategoryId(int categoryId);
	
	public List<ArticleInfo> solr(String name);

}
