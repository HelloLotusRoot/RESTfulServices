package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {

	@GetMapping(value = "/logintest")
	public String logintest() {
		return "logintest";
	}
	
	@GetMapping(value = "/board")
	public String borad() {
		return "board";
	}
	
	@GetMapping(value = "/deletePostAction")
	public String deletePostAction() {
		return "deletePostAction";
	}
	
	@GetMapping(value = "/insertPost")
	public String insertPost() {
		return "insertPost";
	}
	
	@GetMapping(value = "/insertPostAction")
	public String insertPostAction() {
		return "insertPostAction";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@GetMapping(value = "/joinAction")
	public String joinAction() {
		return "joinAction";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "/loginAction")
	public String loginAction() {
		return "loginAction";
	}
	
	@GetMapping(value = "/logoutAction")
	public String logoutAction() {
		return "logoutAction";
	}
	
	@GetMapping(value = "/main")
	public String main() {
		return "main";
	}
	
	@GetMapping(value = "/post/{boardId}")
	public String post(@PathVariable int boardId) {
		return "post";
	}
	
	@GetMapping(value = "/updatePostAction")
	public String updatePostAction() {
		return "updatePostAction";
	}
	
	@GetMapping(value = "/home")
	public String index() {
		return "thymeleaf/index";
	}
}
