package com.blog.tenyears.bean;

import java.io.Serializable;
import java.util.Date;

//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Mapping;

//@Mapping(mappingPath = "articlesearch_mapping.json")
//@Document(indexName = "myblog",type="articleinfo",shards = 5,replicas = 1 ,refreshInterval="1s") 
public class ArticleInfo implements Serializable{
 
	private static final long serialVersionUID = 5031174415342231520L;

	private Long id;

    private Integer userId;

    private Integer categoryId;

    private String articleTitle;

    private String articleContent;

    private String articleImg;

    private String articleRecom;

    private Date articleTime;

    private String articleMark;
    
    private String categoryAlias;

    private Integer pageView;//浏览量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}

	public String getArticleRecom() {
		return articleRecom;
	}

	public void setArticleRecom(String articleRecom) {
		this.articleRecom = articleRecom;
	}

	public Date getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}

	public String getArticleMark() {
		return articleMark;
	}

	public void setArticleMark(String articleMark) {
		this.articleMark = articleMark;
	}

	public String getCategoryAlias() {
		return categoryAlias;
	}

	public void setCategoryAlias(String categoryAlias) {
		this.categoryAlias = categoryAlias;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	@Override
	public String toString() {
		return "ArticleInfo [ userId=" + userId + ", categoryId=" + categoryId
				+ ", articleTitle=" + articleTitle + ", articleContent=" + articleContent + ", articleImg=" + articleImg
				+ ", articleRecom=" + articleRecom + ", articleTime=" + articleTime + ", articleMark=" + articleMark
				+ ", categoryAlias=" + categoryAlias + ", pageView=" + pageView + "]";
	}
    
    
}