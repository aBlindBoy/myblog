package com.blog.tenyears.service;

import java.util.List;

import com.blog.tenyears.bean.CategoryInfo;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 下午1:17:22
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 下午1:17:22
 */
public interface CategoryInfoService {
    int deleteByPrimaryKey(Integer categoryId) throws Exception;

    int insert(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer categoryId);

    List<CategoryInfo> selectAll(CategoryInfo cate);

    int updateByPrimaryKey(CategoryInfo record);
    /*
     * 跟据栏目查询下面有多少文章
     */
    List<CategoryInfo> findCateCountByArticle(CategoryInfo cate);
}
