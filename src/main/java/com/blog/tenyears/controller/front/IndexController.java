package com.blog.tenyears.controller.front;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.tenyears.bean.ArticleInfo;
import com.blog.tenyears.bean.CategoryInfo;
import com.blog.tenyears.bean.MessageInfo;
import com.blog.tenyears.config.EsConfig;
import com.blog.tenyears.service.ArticleInfoService;
import com.blog.tenyears.service.CategoryInfoService;
import com.blog.tenyears.service.MessageService;

@Controller
@RequestMapping("/front/")
public class IndexController {

	@Autowired
	private CategoryInfoService categoryInfoService;

	@Autowired
	private ArticleInfoService ArticleInfoService;
	
	private static MessageService messageService;
	
	@Autowired
	private EsConfig esConfig;
	
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
	
	@GetMapping("index.html")
	public String query(Model model) {
		// 查询最新文章
		List<ArticleInfo> artiList = ArticleInfoService.articleNewList();
		//artiList.forEach(System.err::println);
		// 查询站长推荐
		List<ArticleInfo> recomList = ArticleInfoService.recomNewList();
		// 查询全部栏目
		CategoryInfo info = new CategoryInfo();
		List<CategoryInfo> cateList = categoryInfoService.selectAll(info);
		model.addAttribute("cateList", cateList);
		model.addAttribute("recomList", recomList);
		model.addAttribute("artiList", artiList);
		return "index";
	}

	/**
	 * solr 搜索引擎解决方案
	 * @param model
	 * @param name
	 * @return
	 */
	/*@PostMapping("index.html")
	public String index(Model model,String name) {
		System.err.println(name);
		// 查询站长推荐Model model,String name
		List<ArticleInfo> recomList = ArticleInfoService.recomNewList();
		// 查询全部栏目
		CategoryInfo info = new CategoryInfo();
		List<CategoryInfo> cateList = categoryInfoService.selectAll(info);
		List<ArticleInfo> artiList=null;
		if(StringUtils.isNotBlank(name)){
			//查询索引
			artiList=ArticleInfoService.solr(name);
		}else{
			artiList = ArticleInfoService.articleNewList();
		}
		model.addAttribute("cateList", cateList);
		model.addAttribute("artiList", artiList);
		model.addAttribute("recomList", recomList);
		return "index";
	}*/
	/*public String searchSuggest(String name){
		Client client = esConfig.transportClient();
		SearchRequestBuilder prepareSearch = client.prepareSearch(name);
		prepareSearch.setQuery(QueryBuilders.matchAllQuery());
		CompletionSuggestionBuilder builder=new CompletionSuggestionBuilder("sug");
		String field = builder.field();
		System.out.println(field);
		
		
		return null;
	}*/
	
	@PostMapping("index.html")
	public String index(Model model,String name){
		Client client = esConfig.transportClient();
		HighlightBuilder builder=new HighlightBuilder();
		builder.preTags("<span style='color:red'>");
		builder.postTags("</span>");
		builder.field("articletitle");
		builder.field("articlecontent");
		String [] fields={"articletitle","articlecontent"};
		MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(name, fields);
		SearchResponse searchResponse=client.prepareSearch("myblog")
				.setQuery(multiMatchQuery)
				.highlighter(builder)
				.addSort("pageview", SortOrder.DESC)
				.setFrom(0)
				.setSize(2)
				.execute().actionGet();
		
		
		SearchHits searchHits=searchResponse.getHits();
		System.out.println("======================");
		System.err.println("总记录数==>>"+searchHits.totalHits);
		
		List<ArticleInfo> list=new ArrayList<>();
		System.err.println(searchHits);
		for (SearchHit hit : searchHits) {
			
			String sourceAsString = hit.getSourceAsString();
			System.err.println(sourceAsString);
			System.out.println("===================");
			ArticleInfo articleInfo=new ArticleInfo();
			
			Map<String, Object> map=hit.getSourceAsMap();
			
			
			if(!CollectionUtils.isEmpty(map)) {
                if(!StringUtils.isEmpty(map.get("articleid"))) {
                	articleInfo.setId(Long.valueOf(String.valueOf(map.get("articleid"))));
                }
                if(!StringUtils.isEmpty(map.get("categoryalias"))) {
                	articleInfo.setCategoryAlias(String.valueOf(map.get("categoryalias")));
                }
                if(!StringUtils.isEmpty(map.get("articletitle"))) {
                	articleInfo.setArticleTitle(String.valueOf(map.get("articletitle")));
                }
                if(!StringUtils.isEmpty(map.get("articlecontent"))) {
                	articleInfo.setArticleContent(String.valueOf(map.get("articlecontent")));
                }
                if(!StringUtils.isEmpty(map.get("pageview"))) {
                	articleInfo.setPageView(Integer.valueOf(String.valueOf(map.get("pageview"))));
                }
                if(!StringUtils.isEmpty(map.get("articleimg"))) {
                	articleInfo.setArticleImg(String.valueOf(map.get("articleimg")));
                }
                if(!StringUtils.isEmpty(map.get("articletime"))) {
                	SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",Locale.ENGLISH);
                	Date date = null;
					try {
						//
						date = simple.parse(String.valueOf(map.get("articletime")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	articleInfo.setArticleTime(date);
                }
                
              //高亮字段
    			if(!StringUtils.isEmpty(hit.getHighlightFields().get("articletitle"))){
    				Text[] fragments = hit.getHighlightFields().get("articletitle").getFragments();
    				articleInfo.setArticleTitle(fragments[0].toString());
//    				articleInfo.setArticleContent(String.valueOf(map.get("articlecontent")));
    			}
    			if(!StringUtils.isEmpty(hit.getHighlightFields().get("articlecontent"))){
    				Text[] fragments = hit.getHighlightFields().get("articlecontent").getFragments();
    				String join = org.apache.commons.lang3.StringUtils.join(fragments, ",");
    				System.err.println(join);
    				articleInfo.setArticleContent(join);
    			}
            }
            list.add(articleInfo);
		}
		list.forEach(System.out::print);
		
		model.addAttribute("artiList", list);
		return "index";
	}
	
	
	


	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("list.html/{categoryId}")
	public String list(Model model, @PathVariable(value = "categoryId") Integer categoryId) {
		// 查询全部栏目
		CategoryInfo info = new CategoryInfo();
		List<CategoryInfo> cateList = categoryInfoService.selectAll(info);
		// 跟据栏目查询文章
		List<ArticleInfo> artiList = ArticleInfoService.findArticleByCategoryId(categoryId);
		// 查询站长推荐
		List<ArticleInfo> recomList = ArticleInfoService.recomNewList();
		CategoryInfo categoryInfo=categoryInfoService.selectByPrimaryKey(categoryId);
		
		model.addAttribute("categoryInfo", categoryInfo);
		model.addAttribute("cateList", cateList);
		model.addAttribute("recomList", recomList);
		model.addAttribute("artiList", artiList);
		return "list";
	}

	/**
	 * 留言
	 * @param model
	 * @return
	 */
	@GetMapping("message.html")
	public String message(Model model) {
		// 查询全部栏目
		CategoryInfo info = new CategoryInfo();
		List<CategoryInfo> cateList = categoryInfoService.selectAll(info);
		model.addAttribute("cateList", cateList);
		//查看所有可以显示得留言
		List<MessageInfo> msgList=messageService.messageCan();
		model.addAttribute("msgList", msgList);
		return "message";
	}
	/**
	 * 留言
	 * @param model
	 * @return
	 */
	@PostMapping("message.html")
	public String message(Model model,MessageInfo messageInfo) {
		//添加留言
		int row=messageService.insert(messageInfo);
		return this.message(model);
	}

	/**
	 * 查看全文
	 * @param model
	 * @param articleId
	 * @return
	 */
	@GetMapping("readmore.html/{articleId}/{pageView}")
	public String readmore(Model model,@PathVariable("articleId")Integer articleId,@PathVariable("pageView")Integer pageView) {
		// 查询全部栏目
		CategoryInfo info = new CategoryInfo();
		List<CategoryInfo> cateList = categoryInfoService.selectAll(info);
		// 查询文章
		ArticleInfo article = ArticleInfoService.selectByPrimaryKey(articleId,pageView);
		// 查询站长推荐
		List<ArticleInfo> recomList = ArticleInfoService.recomNewList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("recomList", recomList);
		model.addAttribute("article", article);
		return "readmore";
	}

	
	@GetMapping("/searchSuggest")
	@ResponseBody
	public List<String> searchSuggest(String keyword) throws UnknownHostException {
		 System.out.println(keyword);
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

        List<String> json = Arrays.asList(suggestSet.toArray(new String[]{}));
        for (String string : json) {
			System.err.println(string);
		}
        return json;
    }
}
