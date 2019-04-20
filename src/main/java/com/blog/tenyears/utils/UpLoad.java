package com.blog.tenyears.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月26日 下午8:49:31
 * 修改人 : 小谢
 * 修改时间 :2018年10月26日 下午8:49:31
 * 文件上传工具类
 */
public class UpLoad {

	//文件服务器地址
	public final static String FILE_URL="http://139.159.192.231:8081/fileserver/file/";
	
	public static String doPutFile(MultipartFile file) {
		
		try {
			//图片名称
			String fileName=file.getOriginalFilename();
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String format=sdf.format(new Date());
			
			//构建url
			String url=UpLoad.FILE_URL+format+fileName;
			
			//jersey客户端
			Client client =new Client();
			
			WebResource resource=client.resource(url);
			
			//将文件转为byte
			byte[] buf=file.getBytes();
			resource.put(String.class,buf);
			System.out.println("上传成功");
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
