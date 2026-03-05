package com.sp.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/upload")
	public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) throws Exception{
		try {
			Reader reader = new InputStreamReader(file.getInputStream());			
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
			List<UserVO> userlist = new ArrayList<UserVO>();
			for(CSVRecord record : records) {
				String username = record.get("username");
				String password = record.get("password");				
				
				userlist.add(new UserVO(username, password));				
			}
			userService.batchInsertUsers(userlist);
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
