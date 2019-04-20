package com.blog.tenyears.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * description: 创建TransportClient的类，esTemplate方法避免TransportClient每次使用创建和释放
 * </p>
 *
 * @author chenrui
 * @since 2018-08-28
 */
@Configuration
// @EnableElasticsearchRepositories(basePackages =
// "com.blog.tenyears.repository.ArticleRepository")
@Component
public class EsConfig {

//	@Value("${spring.data.elasticsearch.cluster-name}")
//	private String clusterName;

	private TransportClient esClient;

	@Value("${spring.data.elasticsearch.cluster-ip}")
	private String ip;
	
	@Bean
	public TransportClient transportClient() {
		// 指定ES集群
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

		// 创建访问es服务器的客户端
		@SuppressWarnings("resource")
		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(ip), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 指定节点
		return client;
	}

	// 避免TransportClient每次使用创建和释放
	public Client esTemplate() {
		if (StringUtils.isEmpty(esClient) || StringUtils.isEmpty(esClient.admin())) {
			esClient = transportClient();
			return esClient;
		}
		return esClient;
	}

}
