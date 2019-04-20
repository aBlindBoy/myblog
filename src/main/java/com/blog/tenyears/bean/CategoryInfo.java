package com.blog.tenyears.bean;

import java.io.Serializable;

public class CategoryInfo implements Serializable{
  
	private static final long serialVersionUID = 7529176325893954958L;

	private Integer categoryId;

    private String categoryName;

    private String categoryAlias;

    private String categoryDesc;

    private String categoryMark;
    
    private Integer articleSum;//文章总数
    
    public Integer getArticleSum() {
		return articleSum;
	}

	public void setArticleSum(Integer articleSum) {
		this.articleSum = articleSum;
	}

	public String getCategoryMark() {
		return categoryMark;
	}

	public void setCategoryMark(String categoryMark) {
		this.categoryMark = categoryMark;
	}

	public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias == null ? null : categoryAlias.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

	@Override
	public String toString() {
		return "CategoryInfo [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryAlias="
				+ categoryAlias + ", categoryDesc=" + categoryDesc + ", categoryMark=" + categoryMark + ", articleSum="
				+ articleSum + "]";
	}
    
    
    
}