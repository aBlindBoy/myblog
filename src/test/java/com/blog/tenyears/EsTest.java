package com.blog.tenyears;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.blog.tenyears.bean.ArticleInfo;

public class EsTest {

	@Test
	public void test1() throws UnknownHostException {
		// 指定ES集群
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

		// 创建访问es服务器的客户端
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.126.136"), 9300));// 指定节点
		GetResponse response = client.prepareGet("myblog", "articleinfo", "24").execute().actionGet();

		System.out.println(response.getSourceAsString());
		client.close();
	}

	@Test
	public void test2() throws UnknownHostException {
		System.out.println("==========================================--");
		// 指定ES集群
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

		// 创建访问es服务器的客户端
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.126.137"), 9300));// 指定节点

		HighlightBuilder builder = new HighlightBuilder();
		builder.preTags("<span style='color:red'>");
		builder.postTags("</span>");
		builder.field("articletitle");
		builder.field("articlecontent");
		String[] fields = { "articletitle", "articlecontent" };
		MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("spring", fields);
		SearchResponse searchResponse = client.prepareSearch("myblog").setQuery(multiMatchQuery).highlighter(builder)
				.addSort("pageview", SortOrder.DESC).setFrom(0).setSize(2).execute().actionGet();

		SearchHits searchHits = searchResponse.getHits();
		System.out.println("======================");
		System.err.println("总记录数==>>" + searchHits.totalHits);

		List<ArticleInfo> list = new ArrayList<>();
		System.err.println(searchHits);
		for (SearchHit hit : searchHits) {

			String sourceAsString = hit.getSourceAsString();
			System.err.println(sourceAsString);
			System.out.println("===================");
			ArticleInfo articleInfo = new ArticleInfo();

			Map<String, Object> map = hit.getSourceAsMap();

			if (!CollectionUtils.isEmpty(map)) {
				if (!StringUtils.isEmpty(map.get("articleid"))) {
					articleInfo.setId(Long.valueOf(String.valueOf(map.get("articleid"))));
				}
				if (!StringUtils.isEmpty(map.get("categoryalias"))) {
					articleInfo.setCategoryAlias(String.valueOf(map.get("categoryalias")));
				}
				if (!StringUtils.isEmpty(map.get("articletitle"))) {
					articleInfo.setArticleTitle(String.valueOf(map.get("articletitle")));
				}
				if (!StringUtils.isEmpty(map.get("articlecontent"))) {
					articleInfo.setArticleContent(String.valueOf(map.get("articlecontent")));
				}
				if (!StringUtils.isEmpty(map.get("pageview"))) {
					articleInfo.setPageView(Integer.valueOf(String.valueOf(map.get("pageview"))));
				}
				if (!StringUtils.isEmpty(map.get("articleimg"))) {
					articleInfo.setArticleImg(String.valueOf(map.get("articleimg")));
				}
				if (!StringUtils.isEmpty(map.get("articletime"))) {
					SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = simple.parse(String.valueOf(map.get("articletime")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					articleInfo.setArticleTime(date);
				}

				// 高亮字段
				if (!StringUtils.isEmpty(hit.getHighlightFields().get("articletitle"))) {
					Text[] fragments = hit.getHighlightFields().get("articletitle").getFragments();
					articleInfo.setArticleTitle(fragments[0].toString());
					articleInfo.setArticleContent(String.valueOf(map.get("articlecontent")));
				}
				if (!StringUtils.isEmpty(hit.getHighlightFields().get("articlecontent"))) {
					Text[] fragments = hit.getHighlightFields().get("articlecontent").getFragments();
					articleInfo.setArticleTitle(fragments[0].toString());
					articleInfo.setArticleContent(String.valueOf(map.get("articletitle")));
				}
			}
			list.add(articleInfo);
		}

		for (ArticleInfo articleInfo : list) {
			System.out.println(articleInfo);
		}
		client.close();
	}

/*	@Test
	public void searchSuggest() throws UnknownHostException {
		// 指定ES集群
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

		// 创建访问es服务器的客户端
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.126.137"), 9300));// 指定节点
		SearchRequestBuilder prepareSearch = client.prepareSearch("music");
		prepareSearch.setQuery(QueryBuilders.matchAllQuery());

		CompletionSuggestionBuilder builder = new CompletionSuggestionBuilder("input").text("lucene and elasticsearc rock")
				.size(100);//.analyzer("ik_max_word")
		String field = builder.field();
		System.out.println(field);
		// prepareSearch.
		// builder.exe
	}*/

	@Test
	public void getSuggestSearch() throws UnknownHostException {
		 
		 	//指定ES集群
			Settings settings=Settings.builder().put("cluster.name", "elasticsearch").build();
			
			//创建访问es服务器的客户端
			TransportClient client=new PreBuiltTransportClient(settings)
			.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.126.137"),9300));
	        //field的名字,前缀(输入的text),以及大小size
	        CompletionSuggestionBuilder suggestionBuilderDistrict = SuggestBuilders.completionSuggestion("suggest")
	                .prefix("Nevermin").size(100);
	        SuggestBuilder suggestBuilder = new SuggestBuilder();
	        suggestBuilder.addSuggestion("suggest", suggestionBuilderDistrict);//添加suggest

	        //设置查询builder的index,type,以及建议
	        SearchRequestBuilder requestBuilder = client.prepareSearch("music").suggest(suggestBuilder);

	        SearchResponse response = requestBuilder.get();
	        Suggest suggest = response.getSuggest();//suggest实体
	        System.out.println(suggest.toString());
	        Set<String> suggestSet = new HashSet<>();//set
	        int maxSuggest = 0;
	        if (suggest!=null){
	            @SuppressWarnings("unchecked")
				Suggest.Suggestion result = suggest.getSuggestion("body");//获取suggest,name任意string
	            for (Object term : result.getEntries()) {

	                if (term instanceof CompletionSuggestion.Entry){
	                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
	                    if (!item.getOptions().isEmpty()){
	                        //若item的option不为空,循环遍历
	                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
	                            String tip = option.getText().toString();
	                            if (!suggestSet.contains(tip)){
	                                suggestSet.add(tip);
	                                ++maxSuggest;
	                            }
	                        }
	                    }
	                }
	                if (maxSuggest>=5){
	                    break;
	                }
	            }
	        }

	        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));
	        for (String string : suggests) {
				System.out.println(string);
			}
	    }
	
		
	@Test
	public void getSuggestSearch1() throws UnknownHostException {
		 
		 	//指定ES集群
			Settings settings=Settings.builder().put("cluster.name", "elasticsearch").build();
			
			//创建访问es服务器的客户端
			TransportClient client=new PreBuiltTransportClient(settings)
			.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.126.138"),9300));
	        //field的名字,前缀(输入的text),以及大小size
	        CompletionSuggestionBuilder suggestionBuilderDistrict = SuggestBuilders.completionSuggestion("completion_suggest")
	                .prefix("mybati").size(100);
	        SuggestBuilder suggestBuilder = new SuggestBuilder();
	        suggestBuilder.addSuggestion("completion_suggest", suggestionBuilderDistrict);//添加suggest

	        //设置查询builder的index,type,以及建议
	        SearchRequestBuilder requestBuilder = client.prepareSearch("myblog").suggest(suggestBuilder);

	        SearchResponse response = requestBuilder.get();
	        Suggest suggest = response.getSuggest();//suggest实体
	        System.out.println(suggest.toString());
	        Set<String> suggestSet = new HashSet<>();//set
	        int maxSuggest = 0;
	        if (suggest!=null){
	            @SuppressWarnings("unchecked")
				Suggest.Suggestion result = suggest.getSuggestion("completion_suggest");//获取suggest,name任意string
	            for (Object term : result.getEntries()) {

	                if (term instanceof CompletionSuggestion.Entry){
	                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
	                    if (!item.getOptions().isEmpty()){
	                        //若item的option不为空,循环遍历
	                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
	                            String tip = option.getText().toString();
	                            if (!suggestSet.contains(tip)){
	                                suggestSet.add(tip);
	                                ++maxSuggest;
	                            }
	                        }
	                    }
	                }
	                if (maxSuggest>=5){
	                    break;
	                }
	            }
	        }

	        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));
	        for (String string : suggests) {
				System.err.println(string);
			}
	    }
}
