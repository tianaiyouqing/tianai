package org.tianai.websocket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(HttpSession session , @RequestParam(value="username" , defaultValue="") String username , Model model){
		if(username.trim().length() == 0){
			model.addAttribute("errormsg" , "用户名不能为空");
			return "login";
		} 
		if(username.length() > 10){
			model.addAttribute("errormsg" , "用户名长度不能超过10");
			return "login";
		}
		session.setAttribute("username", username);
		return "chat";
	}
}
