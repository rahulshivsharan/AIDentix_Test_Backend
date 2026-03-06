package com.sp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sp.repo.UserRepository;
import com.sp.vo.UserVO;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserVO> fetchUsers() throws Exception{
		logger.info("fetching all users");
		return this.userRepository.fetchAllUsers();
	}
	
	public int createUser(UserVO userVO) throws Exception{
		logger.info("creating new user ");
		return this.userRepository.createUser(userVO);
	}
	
	public UserVO fetchUser(Integer userId) throws Exception{
		logger.info("get a user ");
		return this.userRepository.getUser(userId);
	}
	
	public void batchInsertUsers(List<UserVO> userList) throws Exception{
		logger.info("create mulitple users ");
		this.userRepository.batchInsertUsers(userList);
	}
}
