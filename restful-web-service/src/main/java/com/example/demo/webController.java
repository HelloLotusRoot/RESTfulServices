package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.user.Board;
import com.example.demo.user.User;

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
	
	@PostMapping(value = "/board")
	public String postBorad() {
		return "board";
	}
	
	@GetMapping(value = "/deletePostAction")
	public String deletePostAction() {
		return "deletePostAction";
	}
	
	@DeleteMapping(value = "/deletePostAction")
	public String deleteDeletePostAction() {
		return "deletePostAction";
	}
	
	@GetMapping(value = "/insertPost")
	public String insertPost() {
		return "insertPost";
	}
	
	@PostMapping(value = "/insertPost")
	public String postInsertPost() {
		return "insertPost";
	}
	
	@GetMapping(value = "/insertPostAction")
	public String insertPostAction() {
		return "insertPostAction";
	}
	
	@PostMapping(value = "/insertPostAction")
	public String postInsertPostAction(Board board) {
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
	
	@PostMapping(value = "/joinAction")
	public String postJoinAction(User user) {
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
	
	@PostMapping(value = "/loginAction")
	public String postLoginAction() {
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
	
	@GetMapping(value = "/post/{board_Id}")
	public String post(int boardId, Board board) {
		return "post";
	}
	
	@PostMapping(value = "/post/{board_Id}")
	public String postPost(@PathVariable int boardId, Board board) {
		return "post";
	}

	
	@GetMapping(value = "/updatePostAction")
	public String updatePostAction() {
		return "updatePostAction";
	}
	
	@PostMapping(value = "/updatePostAction")
	public String postUpdatePostAction() {
		return "updatePostAction";
	}
	
	@PutMapping(value = "/updatePostAction")
	public String putUpdatePostAction() {
		return "updatePostAction";
	}
	
	@GetMapping(value = "/home")
	public String index() {
		return "thymeleaf/index";
	}
}
