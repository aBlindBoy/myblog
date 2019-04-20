package com.blog.tenyears.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.blog.tenyears.bean.ArticleInfo;
import com.blog.tenyears.bean.ArticleInfoBean;
import com.blog.tenyears.bean.CategoryInfo;
import com.blog.tenyears.mapper.ArticleInfoMapper;
import com.blog.tenyears.mapper.CategoryInfoMapper;
import com.blog.tenyears.service.ArticleInfoService;
import com.blog.tenyears.service.CategoryInfoService;

/**
 * 创建人: 小谢 创建时间: 2018年10月24日 下午1:44:26 修改人 : 小谢 修改时间 :2018年10月24日 下午1:44:26
 */
@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

	@Autowired
	private ArticleInfoMapper articleInfoMapper;

	
	@Override
	public int deleteByPrimaryKey(Integer articleId) {
		/*// 根据文章id删除索引
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		try {
			solrServer.deleteById(articleId+"");
			solrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return articleInfoMapper.deleteByPrimaryKey(articleId);
	}

	@Override
	public int insert(ArticleInfo record) {
		//添加数据
		int row=articleInfoMapper.insert(record);
		
		return row;

	}

	@Override
	public ArticleInfo selectByPrimaryKey(Integer articleId, Integer pageView) {
		//修改访问量
		articleInfoMapper.updateByPageView(pageView + 1, articleId);
		
		return this.articleInfoMapper.selectByPrimaryKey(articleId);
	}

	@Override
	public List<ArticleInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ArticleInfo record) {
		//SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		return this.articleInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByCategoryId(int categoryId) {
		
		return articleInfoMapper.deleteByCategoryId(categoryId);
	}

	@Override
	public List<ArticleInfo> ArticleCondition(ArticleInfo categoryId) {
		return articleInfoMapper.ArticleCondition(categoryId);
	}

	@Override
	public int articleSum() {
		return this.articleInfoMapper.articleSum();
	}

	@Override
	public List<ArticleInfo> articleNewList() {

		return this.articleInfoMapper.articleNewList();
	}

	@Override
	public List<ArticleInfo> recomNewList() {
		return articleInfoMapper.recomNewList();
	}

	@Override
	public List<ArticleInfo> findArticleByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return articleInfoMapper.findArticleByCategoryId(categoryId);
	}

	@Override
	public List<ArticleInfo> solr(String name) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<ArticleInfo> solr(String name) {
//	
//
//		// 设置查找的参数
//
//
//		QueryResponse response = null;
//			SolrDocumentList documentList = response.getResults();
//			long l = documentList.getNumFound();
//			System.err.println(l);
//			List<ArticleInfo> artiList = new ArrayList<>();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			
//			for (SolrDocument solrDocument : documentList) {
//				ArticleInfo arti = new ArticleInfo();
//				String articleId = (String) solrDocument.getFieldValue("article_id");
//				String articleImg = (String) solrDocument.getFieldValue("article_img");
//			
//				String categoryAlias = (String) solrDocument.getFieldValue("category_alias");
//				String articleTitle = (String) solrDocument.getFieldValue("article_title");
//				//替换字符串
//				String articleTitleRed =StringUtils.replace(articleTitle,name,"<font color='red'>"+name+"</font>");
//				String articleContent = (String) solrDocument.getFieldValue("article_content");
//				String articleTime = (String) solrDocument.getFieldValue("article_time");
//
//				//arti.setId(Integer.parseInt(articleId));
//				arti.setArticleImg(articleImg);
//				arti.setArticleTitle(articleTitleRed);
//				arti.setCategoryAlias(categoryAlias);
//				try {
//					arti.setArticleTime(formatter.parse(articleTime));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				arti.setArticleContent(articleContent);
//				System.err.println(arti);
//				artiList.add(arti);
//			}
//
//			return artiList;
//	}

}
