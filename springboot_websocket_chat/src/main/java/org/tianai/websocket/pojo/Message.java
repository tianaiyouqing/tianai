package org.tianai.websocket.pojo;

import java.util.Date;
import java.util.List;

public class Message {

	private List<String> names;
	private String username;
	private String msg;
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Message(List<String> names, String username, String msg) {
		this.names = names;
		this.username = username;
		this.msg = msg;
		formatMsg();
	}
	
	public void formatMsg(){
		if(msg != null && username != null){
			msg = username + "\t" + new Date().toLocaleString() + "\n" + msg +"\n";
		}
	}


}
