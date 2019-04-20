package com.blog.tenyears.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.tenyears.bean.CategoryInfo;

@Mapper
public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer categoryId);

    List<CategoryInfo> selectAll();

    int updateByPrimaryKey(CategoryInfo record);
    /*
     * 跟据栏目查询下面有多少文章
     */
    List<CategoryInfo> findCateCountByArticle();
}