package com.blog.tenyears.bean;





public class ArticleInfoBean {

	private Integer articleId;
	
	//@Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String articleTitle;
	
	//@Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String articleContent;
	
	private String articleImg;
	
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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

	@Override
	public String toString() {
		return "ArticleInfoBean [articleId=" + articleId +", articleTitle=" + articleTitle
				+ ", articleContent=" + articleContent + ", articleImg=" + articleImg + "]";
	}
	
	
}
