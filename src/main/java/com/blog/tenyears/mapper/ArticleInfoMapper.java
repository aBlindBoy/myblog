package com.blog.tenyears.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.tenyears.bean.ArticleInfo;


@Mapper
public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer articleId);

    List<ArticleInfo> selectAll();
   
    List<ArticleInfo> ArticleCondition(ArticleInfo categoryId);

    int updateByPrimaryKey(ArticleInfo record);
    
    int articleSum();
    
    int updateByPageView(Integer articleId,Integer pageView);
    
    int deleteByCategoryId(int categoryId);
    
    List<ArticleInfo> articleNewList();
    
    List<ArticleInfo> recomNewList();

    List<ArticleInfo> findArticleByCategoryId(int categoryId);
    
    int insertLastId();
    
}