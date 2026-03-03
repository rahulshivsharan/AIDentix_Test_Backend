package com.sp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.service.UserService;
import com.sp.vo.UserVO;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/hi")
	public Map<String, String> sayHi(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Hi How are you");
		return map;
	}
	
	@GetMapping
	public ResponseEntity fetchAllUsers() {
		try {
			List<UserVO> userList = userService.fetchUsers();
			return new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			HashMap<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity fetchUserById(@PathVariable(name = "id") Integer userId) {
		try {
			UserVO user = userService.fetchUser(userId);
			return new ResponseEntity<UserVO>(user, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			HashMap<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	
	@PostMapping
	public ResponseEntity createUser(@RequestBody UserVO vo) throws Exception{
		try {
			int count = this.userService.createUser(vo);			
			List<UserVO> userList = userService.fetchUsers();
			return new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			HashMap<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
