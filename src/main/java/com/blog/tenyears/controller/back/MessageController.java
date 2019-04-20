package com.blog.tenyears.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.tenyears.bean.MessageInfo;
import com.blog.tenyears.service.MessageService;

/**
 * 创建人: 小谢
 * 创建时间: 2018年10月24日 上午11:23:26
 * 修改人 : 小谢
 * 修改时间 :2018年10月24日 上午11:23:26
 * 留言控制器
 */
@Controller
@RequestMapping("/back/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("list.html")
	public String list(Model model){
		List<MessageInfo> msgList=messageService.selectAll();
		model.addAttribute("msgList",msgList);
		return "back/message/message_list";
	}
	
	@GetMapping("update.html")
	public String update(Integer messageId,String messageMark,Model model){
		MessageInfo msg=new MessageInfo();
		msg.setMessageId(messageId);
		msg.setMessageMark(messageMark);
		messageService.updateByPrimaryKey(msg);
		return this.list(model);
	}
}
