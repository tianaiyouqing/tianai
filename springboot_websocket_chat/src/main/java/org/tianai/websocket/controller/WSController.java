package org.tianai.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import org.tianai.websocket.pojo.Message;
import org.tianai.websocket.util.FastJsonUtil;


@ServerEndpoint("/chat")
@Component
public class WSController {
	
	private static List<String> usernames = new ArrayList<>();
	private String username;
	private Session session;
	private static Set<WSController> users = new HashSet<>();
	@OnOpen
	public void onOpen(Session session){
		System.out.println("初始链接sessionid=" + session.getId());
		String username = session.getQueryString().split("=")[1];
		this.session = session;
		this.username = username;
		usernames.add(username);
		users.add(this);
		System.out.println("初始链接用户名=" + username);
		
		Message message = new Message(usernames, username, null);
		
		radio(users, message);
	}
	
	@OnClose
	public void onClose(Session session){
		System.out.println("关闭链接sessionid=" + session.getId());
		usernames.remove(username);
		users.remove(this);
		Message message = new Message(usernames, username, null);
		radio(users, message);
	}
	
/*	@OnError
	public void onError(){
		System.out.println("出现异常");
		if(session != null){
			try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	@OnMessage
	public void onMessage(Session session , String msg , boolean last){
		System.out.println(session.getId() + "客户输出了：" + msg);
		Message message = new Message(usernames, username, msg);
		
		radio(users, message);
	}
	
	/*
	 * 群发
	 */
	private void radio(Set<WSController> users , Message message){
		
		for (WSController wsController : users) {
			try {
				wsController.session.getBasicRemote().sendText(FastJsonUtil.toJSONString(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
