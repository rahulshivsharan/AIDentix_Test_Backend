package com.sp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.repo.UserRepository;
import com.sp.vo.UserVO;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserVO> fetchUsers() throws Exception{
		return this.userRepository.fetchAllUsers();
	}
	
	public int createUser(UserVO userVO) throws Exception{
		return this.userRepository.createUser(userVO);
	}
	
	public UserVO fetchUser(Integer userId) throws Exception{
		return this.userRepository.getUser(userId);
	}
}
